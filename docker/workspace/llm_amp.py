import os
import openai
import json
import re
import time
import sys
import difflib
import csv
import tiktoken
import signal

### have to be changed with private key !!!! ###
openai.api_key = "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"
### have to be changed with private key !!!! ###    

JAVA_ANALYZER="java_analyzer/target/java-analyzer-1.0-SNAPSHOT-shaded.jar"

class JavaFileParsingError(Exception):
    pass

class TimeOutException(Exception):
    pass
def alarm_handler(signum, frame):
    raise TimeOutException()

class D4JEnv:
    def __init__(self, pid, vid):
        self.pid, self.vid = pid, vid
        # self.fixed_dir = f"/tmp/{pid}-{vid}f"
        self.buggy_dir = f"/tmp/{pid}-{vid}b"
        # self.mod_dir = f"/tmp/{pid}-{vid}m"
        self.output_dir = f"./analyzed_files/{self.pid}-{self.vid}/"

        # if not os.path.exists(self.fixed_dir):
        #     os.system(f"defects4j checkout -p {pid} -v {vid}f -w {self.fixed_dir}")
        # if not os.path.exists(self.buggy_dir):
        os.system(f"rm -rf {self.buggy_dir}")
        os.system(f"defects4j checkout -p {pid} -v {vid}b -w {self.buggy_dir}")
        if not os.path.exists(os.path.join(self.buggy_dir, "dir.src.classes")):
            os.system(f"cd {self.buggy_dir} && defects4j export -p dir.src.classes -o dir.src.classes")
        if not os.path.exists(os.path.join(self.buggy_dir, "dir.src.tests")):
            os.system(f"cd {self.buggy_dir} && defects4j export -p dir.src.tests -o dir.src.tests")
        if not os.path.exists(os.path.join(self.buggy_dir, "dir.bin.classes")):
            os.system(f"cd {self.buggy_dir} && defects4j export -p dir.bin.classes -o dir.bin.classes")
        if not os.path.exists(os.path.join(self.buggy_dir, "tests.trigger")):
            os.system(f"cd {self.buggy_dir} && defects4j export -p tests.trigger -o tests.trigger")
        if not os.path.exists(os.path.join(self.buggy_dir, "cp.compile")):
            os.system(f"cd {self.buggy_dir} && defects4j export -p cp.compile -o cp.compile")
        if not os.path.exists(os.path.join(self.buggy_dir, "cp.test")):
            os.system(f"cd {self.buggy_dir} && defects4j export -p cp.test -o cp.test")
        if not os.path.exists(os.path.join(self.buggy_dir, "classes.modified")):
            os.system(f"cd {self.buggy_dir} && defects4j export -p classes.modified -o classes.modified")
        # if not os.path.exists(os.path.join(self.buggy_dir, "failing_tests")):
        #     os.system(f"cd {self.buggy_dir} && defects4j test")


        with open(os.path.join(self.buggy_dir, "dir.src.classes"), 'r') as f:
            self.dir_src_classes = f.read()
        with open(os.path.join(self.buggy_dir, "dir.src.tests"), 'r') as f:
            self.dir_src_tests = f.read()
        with open(os.path.join(self.buggy_dir, "dir.bin.classes"), 'r') as f:
            self.dir_bin_classes = f.read()
        with open(os.path.join(self.buggy_dir, "tests.trigger"), 'r') as f:
            self.tests_trigger = f.read().split('\n')
        with open(os.path.join(self.buggy_dir, "classes.modified"), 'r') as f:
            self.classes_modified = f.read().split('\n')
        with open(os.path.join(self.buggy_dir, "cp.compile"), 'r') as f:
            self.cp_compile = f.read()
        with open(os.path.join(self.buggy_dir, "cp.test"), 'r') as f:
            self.cp_test = f.read()

        if not os.path.exists(self.output_dir):
            os.mkdir(self.output_dir)
            os.mkdir(os.path.join(self.output_dir, "range"))

        self.new_range_src = {}
        self.new_range_test = set()

        # self.files_to_analyze = set()
        # patch_path = f"/defects4j/framework/projects/{pid}/patches/{vid}.src.patch"
        # with open(patch_path, "r", errors='ignore') as f:
        #     buggy_file = None
        #     lines = f.readlines()
        #     for l in lines:
        #         if l.startswith("+++"):
        #             b = re.match("(\+*\s*)(\S*)", l)
        #             if b is None:
        #                 continue
        #             buggy_file = b.group(2).split("b/")[-1]
        #             self.files_to_analyze.add(buggy_file)
        #             continue

        for mod_class in self.classes_modified:
            path_to_src = os.path.join(self.dir_src_classes, mod_class.replace('.', '/') + '.java')
            self.analyze_src_file(path_to_src, mod_class, True)

        for triggered_test in self.tests_trigger:
            path_to_test = os.path.join(self.dir_src_tests, triggered_test.replace('.', '/').split('::')[0] + '.java')
            self.analyze_test_file(path_to_test, triggered_test, False)
        
        # sample
        sample_list = os.listdir(f"./data/{self.pid}-{self.vid}b/tests/")
        sample_list = [int(i) for i in sample_list]
        self.sample_num = 1
        if len(sample_list) != 0:
            self.sample_num = max(sample_list) + 1

        for mod_class in self.classes_modified:
            path = mod_class.split('.')[:-1]
            path = "/".join(path)
            if not os.path.exists(f"./data/{self.pid}-{self.vid}b/tests/{self.sample_num}/{path}"):
                os.system(f"mkdir -p ./data/{self.pid}-{self.vid}b/tests/{self.sample_num}/{path}")
                os.system(f"mkdir -p ./data/{self.pid}-{self.vid}b/mut_results/{self.sample_num}")
    


    # amplify new testcases based on the provided code/testcase sources
    # write output for each method in prompt.txt
    def amplify_test(self):
        src_methods = self.collect_src_method()
        # dev_tests = self.collect_dev_test()
        prompt = open("prompt.txt", "w")

        # single class & single failing test
        test_classes = set()
        for test in self.tests_trigger:
            test_classes.add(test.replace('.', '/').split('::')[0])

        if len(self.classes_modified) != 1 or len(test_classes) != 1:
            return None

        # using test coverage to match method
        for mod_class, methods in src_methods.items():

            # open file for wirting temporary regression tests(result)
            r = open("./result.java", "w", errors='ignore')

            # open file for writing actual regression test suite
            path_to_reg_test = f"./data/{self.pid}-{self.vid}b/tests/{self.sample_num}/{mod_class.replace('.', '/')}" + '_LLMTest.java'
            q = open(path_to_reg_test, "w", errors='ignore')


            # open file for reading source
            path_to_src = os.path.join(self.dir_src_classes, mod_class.replace('.', '/') + '.java')
            s = open(os.path.join(self.buggy_dir, path_to_src), "r", errors='ignore')
            src_under_test = s.readlines()




            # open file for reading testcase
            for triggered_test in test_classes:
                path_to_test = os.path.join(self.dir_src_tests, triggered_test + '.java')
                # path_to_test = os.path.join(self.dir_src_tests, mod_class.replace('.', '/') + 'Test.java')
                t = open(os.path.join(self.buggy_dir, path_to_test), "r", errors='ignore')
                test_src = t.readlines()
                
                for line in test_src:
                    if line.startswith("package") or line.startswith("import"):
                        q.write(line)
            q.write("\npublic class " + mod_class.split('.')[-1] + "_LLMTest {\n")

            test_cnt = 0

            for method in methods:
                method_name = method["signature"].split('(')[0].split('.')[-1]
                if "private" in src_under_test[method["begin_line"]].lower():
                    continue
                method_src = "".join(src_under_test[method["begin_line"]-1:method["end_line"]])
                covering_tests = self.collect_covering_test(method_name)
                if len(covering_tests) == 0:
                    continue
                
                ### LLM part ###

                # STEP 1 : identify whether the object is public or private
                first_prompt = f'''
The method looks like: 
```java
{method_src}
```

The test cases covering given method looks like: 
```java
{covering_tests}
```

Provide regression test cases that can kill more mutants of the given method by changing the input of the covering tests.
Only the input value of the method in each test should be changed.
You should insert all regression tests into the below format.
I have to parse your response, therefore the format below should appear only once in your response.

```java

```
'''
                messages = [
                    {
                        "role": "user",
                        "content": first_prompt
                    }
                ]
                prompt.write(first_prompt)
                response = openai.ChatCompletion.create(
                    model="gpt-3.5-turbo-16k",
                    messages=messages,
                )

                response_message = response["choices"][0]["message"]
                prompt.write("\n" + response_message["content"] + "\n")
                
                # messages.append(response_message)
                # messages.append(
                #     {
                #         "role": "user",
                #         "content": second_prompt
                #     }
                # )
                
                # prompt.write("\n" + second_prompt + "\n")

                # response = openai.ChatCompletion.create(
                #     model="gpt-3.5-turbo-16k",
                #     messages=messages,
                # )
                # response_message = response["choices"][0]["message"]
                # prompt.write("\n" + response_message["content"] + "\n")

            
                p = re.compile("```java(.*)```", re.DOTALL)
                # pattern = r'(@Test.*?}\n)(?:.*?)(?=@Test|$)'

                regression_tests = p.search(response_message["content"])
                if regression_tests != None:
                    regression_tests = regression_tests.group(1).lstrip("\n").rstrip("\n").replace("```", "")

                    # regression_tests = re.sub(pattern, r'\1\n\n\n', regression_tests, flags=re.DOTALL)

                    r.write(regression_tests + "\n\n")
                
                ### LLM part end ###
            # r.write('}')
            r.close()

            r = open("./result.java", "r", errors='ignore')
            # assign sequential function names for each identified testcases

            acc = 0
            p = re.compile("public void (.*)\(\)", re.DOTALL)
            count = 0
            for line in r.readlines():
                if line.lstrip().startswith("@Test"):
                    # counting parenthesis
                    count += line.count("{")
                    count -= line.count("}")

                    # renaming the test
                    m = p.search(line)
                    if m == None:
                        q.write(line)
                        continue
                    name = m.group(1)
                    line=line.replace(name, f"test{acc}")
                    q.write(line)
                    acc += 1
                    continue

                # counting parenthesis
                prev_count = count
                count += line.count("{")
                count -= line.count("}")

                if count == 0 and prev_count == 0:
                    continue
                
                # renaming the test
                m = p.search(line)
                if m == None:
                    q.write(line)
                    continue
                name = m.group(1)
                line=line.replace(name, f"test{acc}")
                q.write(line)
                acc += 1
            q.write('}')
            q.close()
            r.close()
            s.close()
            t.close()

            os.system(f"cd ./data/{pid}-{vid}b/tests/{self.sample_num} && tar -cjvf {pid}-{vid}b-llm.{self.sample_num}.tar.bz2 ./org")

            # time out
            # signal.signal(signal.SIGALRM, alarm_handler)
            # signal.alarm(300)
            # fix_test_suite
            os.system(f"cd /defects4j/framework/util && ./fix_test_suite.pl -p {pid} -v {vid}b -d ~/workspace/data/{pid}-{vid}b/tests/{self.sample_num}")
            # try:
            #     os.system(f"cd /defects4j/framework/util && ./fix_test_suite.pl -p {pid} -v {vid}b -d ~/workspace/data/{pid}-{vid}b/tests/{self.sample_num}")
            # except TimeOutException as e:
            #     os.system(f"rm -rf ~/workspace/data/{pid}-{vid}b/tests/{self.sample_num}")
            #     return 1

            

            # run_mutation
            os.system(f"cd /defects4j/framework/bin && ./run_mutation.pl -p {pid} -v {vid}b -d ~/workspace/data/{pid}-{vid}b/tests/{self.sample_num} -o ~/workspace/data/{pid}-{vid}b/mut_results/{self.sample_num}")

            if not os.path.exists(f"./data/{pid}-{vid}b/mut_results/{self.sample_num}/mutation_log/{pid}/run_mutation.pl.log"):
                os.system(f"rm -rf ./data/{pid}-{vid}b/tests/{self.sample_num}")
                os.system(f"rm -rf ./data/{pid}-{vid}b/mut_results/{self.sample_num}")


        prompt.close()
                
        return 1

        # using test name to match method
        for mod_class, methods in src_methods.items():
            test_suite = dev_tests[mod_class]
            
            # open file for wirting regression(result)
            path_to_reg_test = f"./data/{self.pid}-{self.vid}b/1/{mod_class.replace('.', '/')}" + '_LLMTest.java'
            r = open(path_to_reg_test, "a", errors='ignore')

            # open file for reading source
            path_to_src = os.path.join(self.dir_src_classes,  mod_class.replace('.', '/') + '.java')
            s = open(os.path.join(self.buggy_dir, path_to_src), "r", errors='ignore')
            src_under_test = s.readlines()

            # open file for reading testcase
            path_to_test = os.path.join(self.dir_src_tests, mod_class.replace('.', '/') + 'Test.java')
            t = open(os.path.join(self.buggy_dir, path_to_test), "r", errors='ignore')
            test_src = t.readlines()

            for method in methods:
                # extracting method name from signature
                method_name = method["signature"].split('(')[0].split('.')[-1]
                method_src = "".join(src_under_test[method["begin_line"]-1:method["end_line"]])
                matching_test = ''

                for test in test_suite:
                    # extracting test name from signature
                    test_name = test["signature"].split('(')[0].split('.')[-1]
                    if method_name.lower() in test_name.lower():
                        matching_test += "".join(test_src[test["begin_line"]-1:test["end_line"]])
                # print(method_src)
                # print(matching_test)
                
                ## LLM part ##
                # 2. You don't need to declare test class.
                # 3. 
                system_prompt = f'''
You are a test generating assistant. 
You will be provided with method and relevant tests (test cases that includes the method's name in its name) written by developer.
Your task is providing regression tests. 
'''
                # STEP 1 : find the number of argument for each given methods
                first_prompt = f'''
The method looks like: 
```java
{method_src}
```
Find the number of arguments for the given method. Distinguish whether this is the public or private method.
'''
                # STEP 2 : find the given testcases that matches with the number of argument found before 
                second_prompt = f'''
The test cases written by developer looks like: 
```java
{matching_test}
```
Find a test that matches the given method. At this time, use the number of arguments of the method you found.
'''
                # STEP 3 : make a new testcases
                third_prompt = '''
Provide regression test cases of the given method by referring to tests that match the given method. You can make up to 3 regression test cases.
The modules imported by the developer test are as follows.
```java
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
```

Make a test assuming that the above import statement has already been declared. 
Therefore, when you make tests, you must not write any import statement. 
The tests you make will be used within the test class, so you should not declare the class.
Also, you should not make regression test of private method.
The format below should only appear once in your answer.

```java
<insert regression tests into here (do not separate)>
```
'''
                messages = [
                    # {
                    #     "role": "system",
                    #     "content": system_prompt
                    # },
                    {
                        "role": "user",
                        "content": first_prompt
                    }
                ]
                prompt.write(first_prompt)
                response = openai.ChatCompletion.create(
                    model="gpt-3.5-turbo-16k",
                    messages=messages,
                )

                response_message = response["choices"][0]["message"]
                prompt.write("\n" + response_message["content"] + "\n")
                
                messages.append(response_message)
                messages.append(
                    {
                        "role": "user",
                        "content": second_prompt
                    }
                )
                
                prompt.write("\n" + second_prompt + "\n")

                response = openai.ChatCompletion.create(
                    model="gpt-3.5-turbo-16k",
                    messages=messages,
                )
                response_message = response["choices"][0]["message"]
                prompt.write("\n" + response_message["content"] + "\n")

                messages.append(response_message)
                messages.append(
                    {
                        "role": "user",
                        "content": third_prompt
                    }
                )
                
                prompt.write("\n" + third_prompt + "\n")

                response = openai.ChatCompletion.create(
                    model="gpt-3.5-turbo-16k",
                    messages=messages,
                )
                response_message = response["choices"][0]["message"]
                prompt.write("\n" + response_message["content"] + "\n")

            
                p = re.compile("```java(.*)```", re.DOTALL)
                regression_tests = p.search(response_message["content"])
                if regression_tests != None:
                    regression_tests = regression_tests.group(1).lstrip("\n").rstrip("\n").replace("```", "")
                    r.write(regression_tests + "\n\n")


                # regression_tests = p.finditer(response_message["content"])
                # if regression_tests != None:
                #     for reg_test in regression_tests:
                #         refined = reg_test.group(1).lstrip("\n").rstrip("\n")
                #         r.write(refined + "\n\n")
                ## LLM part end ##
            
            r.close()
            s.close()
            t.close()
        prompt.close()

        # for i in range(len(self.classes_modified)):
        #     messages = [
        #         {
        #             "role": "system",
        #             "content": ("You are a test generating assistant. You will be provided with source code and corresponding tests written by developer. "
        #                         "Your task is providing regression tests based on the developer tests. "
        #                         )
        #         },
        #         {
        #             "role": "user",
        #             "content": src_under_test[i] + dev_test[i]
        #         }
        #     ]
        #     num_tokens = self.num_tokens_from_messages(messages)
        #     # print(messages[1]["content"])
        #     if num_tokens > 4097:
        #         print(num_tokens)
        return

    def num_tokens_from_messages(self, messages, model="gpt-3.5-turbo-16k"):
        try:
            encoding = tiktoken.encoding_for_model(model)
        except KeyError:
            encoding = tiktoken.get_encoding("cl100k_base")
        if model == "gpt-3.5-turbo-16k":  # note: future models may deviate from this
            num_tokens = 0
            for message in messages:
                num_tokens += 4  # every message follows <im_start>{role/name}\n{content}<im_end>\n
                for key, value in message.items():
                    num_tokens += len(encoding.encode(value))
                    if key == "name":  # if there's a name, the role is omitted
                        num_tokens += -1  # role is always required and always 1 token
            num_tokens += 2  # every reply is primed with <im_start>assistant
            return num_tokens
        else:
            raise NotImplementedError(f"""num_tokens_from_messages() is not presently implemented for model {model}.
        See https://github.com/openai/openai-python/blob/main/chatml.md for information on how messages are converted to tokens.""")
    
    # get code source from original source file
    def get_src(self):
        src = []
        for mod_class in self.classes_modified:
            path_to_src = os.path.join(self.dir_src_classes,  mod_class.replace('.', '/') + '.java')
            f = open(os.path.join(self.buggy_dir, path_to_src), "r", errors='ignore')
            src.append(f.read())
            f.close()
        return src
    
    # node dictionary # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
    # {
    #   "type": "method",
    #   "signature": "org.apache.commons.lang3.math.NumberUtils.toInt(java.lang.String)",
    #   "begin_line": 99,
    #   "end_line": 101,
    #   "comment": /* comment */
    # }
    # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

    # get method from code source
    # output : Dict {class : node dictionary}
    def collect_src_method(self):
        src_methods = {}
        for mod_class, path in self.new_range_src.items():
            with open(path, 'r') as f:
                file_range = json.load(f)
                src_methods[mod_class] = []
                for node in file_range["nodes"]:
                    if node["type"] == "method":
                        src_methods[mod_class].append(node)
        return src_methods

    # get testcase from testcase source
    # output : Dict {class : node dictionary}
    def collect_dev_test(self):
        dev_tests = {}
        for mod_class, path in self.new_range_test.items():
            with open(path, 'r') as f:
                file_range = json.load(f)
                dev_tests[mod_class] = []
                for node in file_range["nodes"]:
                    if node["type"] == "method":
                        dev_tests[mod_class].append(node)
        return dev_tests

    # get testcase source from origianl source file
    def get_dev_test(self):
        dev_test = []
        for mod_class in self.classes_modified:
            path_to_test = os.path.join(self.dir_src_tests, mod_class.replace('.', '/') + 'Test.java')
            f = open(os.path.join(self.buggy_dir, path_to_test), "r", errors='ignore')
            dev_test.append(f.read())
            f.close()
        return dev_test

    def get_range_path(self, src_path):
        return os.path.join(os.path.join(self.output_dir, "range"), src_path.replace("/", "+"))

    def analyze_src_file(self, src_path, mod_class, is_src, overwrite=False, verbose=True):
        if not os.path.exists(os.path.join(self.buggy_dir, src_path)):
            return None
        # src_path = r"{}".format(src_path).replace(r'$', r'\$')\
        src_path = src_path.replace('$', '\$')
        output_path = self.get_range_path(src_path)
        
        
        if not os.path.exists(output_path.replace('\$', '$')) or (overwrite and (mod_class not in self.new_range_src or mod_class not in self.new_range_test)):
            command = f'java -jar {JAVA_ANALYZER} {os.path.join(self.buggy_dir, src_path)} {output_path} {os.path.join(self.buggy_dir, self.dir_src_classes)} {self.cp_compile.replace(":", " ")}'
            if self.pid == "Mockito":
                command += " /defects4j/framework/projects/lib/junit-4.11.jar"
            if verbose:
                print(command)
            os.system(command)
        
        # output_path = r"{}".format(output_path).replace(r'\$', r'$')
        # store output path of each source classes to the dictionaries(code, testcase)
        output_path = output_path.replace('\$', '$')
        if os.path.exists(output_path):
            if is_src:
                self.new_range_src[mod_class] = output_path
                return output_path
            else:
                self.new_range_test[mod_class] = output_path
                return output_path

    def analyze_test_file(self, test_path, overwrite=False, verbose=True):
        if not os.path.exists(os.path.join(self.buggy_dir, test_path)):
            return None
        test_path = test_path.replace('$', '\$')
        output_path = self.get_range_path(test_path)

        if not os.path.exists(output_path.replace('\$', '$')) or (overwrite and (test_path, output_path) not in self.new_range_test):
            command = f'java -jar {JAVA_ANALYZER} {os.path.join(self.buggy_dir, test_path)} {output_path} {os.path.join(self.buggy_dir, self.dir_src_tests)} {self.cp_compile.replace(":", " ")}'
            if self.pid == "Mockito":
                command += " /defects4j/framework/projects/lib/junit-4.11.jar"
            if verbose:
                print(command)
            os.system(command)

        output_path = output_path.replace('\$', '$')
        if os.path.exists(output_path):
            self.new_range_test.add((test_path, output_path))
            return output_path


    # do coverage test for finding proper testcase
    def collect_covering_test(self, method_name):
        gzoltar_dir = f"/tmp/{pid}-{vid}g"

        if not os.path.exists(f"{gzoltar_dir}/sfl"):
            os.system(f"sh run_gzoltar.sh {pid} {vid}")
        os.system(f"cd {gzoltar_dir}/sfl/txt")
        # binary matrix
        m = open(f'{gzoltar_dir}/sfl/txt/matrix.txt', 'r')
        lines = m.readlines()
        
        # row
        t = open(f'{gzoltar_dir}/sfl/txt/tests.csv', 'r')
        tests = csv.reader(t)
        tests = [test for test in tests][1:]
        # column
        s = open(f'{gzoltar_dir}/sfl/txt/spectra.csv', 'r')
        spectra = csv.reader(s)
        spectra = [stmt for stmt in spectra][1:]
        
        cov_test_names = set()

        for stmt_index, stmt in enumerate(spectra):
            # find the column of desired method
            if stmt[0].split('(')[0].split('#')[-1] == method_name:
                for test_index, line in enumerate(lines):
                    # find the testcase that is related with the method
                    if line.split(' ')[:-1][stmt_index] == '1':
                        cov_test_names.add(tests[test_index][0].replace('#', '.'))

        m.close()
        t.close()
        s.close()
        # print(cov_test_names)
        cov_tests = ''

        # path = self.new_range_test[mod_class]
        for test_path, range_path in self.new_range_test:
            t = open(os.path.join(self.buggy_dir, test_path), 'r', errors='ignore')
            test_src = t.readlines()
            with open(range_path, 'r') as f:
                file_range = json.load(f)
                for test_name in cov_test_names:
                    for node in file_range["nodes"]:
                        if node["type"] != "method":
                            continue
                        if node["signature"].split('(')[0] == test_name:
                            cov_tests += "".join(test_src[node["begin_line"]-1:node["end_line"]])

        return cov_tests


if __name__ == "__main__":
    mode = sys.argv[1]

    if mode == "test":
        pid, vid = "Csv", "1"
        if not os.path.exists(f"./data/{pid}-{vid}b/tests"):
            os.system(f"mkdir -p ./data/{pid}-{vid}b/tests")
            os.system(f"mkdir -p ./data/{pid}-{vid}b/mut_results")
        
        iter_num = 1
        for i in range(iter_num):
            env = D4JEnv(pid, vid)
            env.amplify_test()


        # if not os.path.exists(f"./data/{pid}-{vid}b"):
        #     os.system(f"mkdir -p ./data/{pid}-{vid}b")
        # with open(f"./data/{pid}-{vid}b/dev_fixed_code.txt", "w") as f:
        #     f.write(dev_fixed_code)
        # with open(f"./data/{pid}-{vid}b/llm_refactored_code.txt", "w") as f:
        #     f.write(llm_refactored_code)
            
    if mode == "exec":
        f = open("data.txt", "r")
        versions = f.readlines()

        for version in versions:
            pid, vid = version.split(",")
            pid = pid.strip()
            vid = vid.strip()
            if pid != 'Csv' or vid != '1':
                continue
            if not os.path.exists(f"./data/{pid}-{vid}b/tests"):
                os.system(f"mkdir -p ./data/{pid}-{vid}b/tests")
                os.system(f"mkdir -p ./data/{pid}-{vid}b/mut_results")
            

            while len(os.listdir(f"./data/{pid}-{vid}b/tests")) < 30:
                env = D4JEnv(pid, vid)
                result = env.amplify_test()
                if result == None:
                    # os.system(f"rm -rf ./data/{pid}-{vid}b")
                    break

