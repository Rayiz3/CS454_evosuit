import os
import openai
import json
import re
import time
import sys
import difflib
import tiktoken

### have to be changed with private key !!!! ###
openai.api_key = "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"
# openai.organization = "org-mSMx268bkMcTa5gXwsDGN8Af"
### have to be changed with private key !!!! ###

JAVA_ANALYZER="java_analyzer/target/java-analyzer-1.0-SNAPSHOT-shaded.jar"

class JavaFileParsingError(Exception):
    pass

class D4JEnv:
    def __init__(self, pid, vid):
        self.pid, self.vid = pid, vid
        # self.fixed_dir = f"/tmp/{pid}-{vid}f"
        self.buggy_dir = f"/tmp/{pid}-{vid}b"
        # self.mod_dir = f"/tmp/{pid}-{vid}m"
        self.output_dir = f"./analyzed_files/{self.pid}-{self.vid}/"

        # if not os.path.exists(self.fixed_dir):
        #     os.system(f"defects4j checkout -p {pid} -v {vid}f -w {self.fixed_dir}")
        if not os.path.exists(self.buggy_dir):
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

        if not os.path.exists(self.output_dir):
            os.mkdir(self.output_dir)
            os.mkdir(os.path.join(self.output_dir, "range"))

        self.new_range_src = {}
        self.new_range_test = {}

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
            path_to_test = os.path.join(self.dir_src_tests, mod_class.replace('.', '/') + 'Test.java')
            self.analyze_src_file(path_to_test, mod_class, False)
            
    def amplify_test(self):
        # src_under_test = self.get_src()
        # dev_test = self.get_dev_test()

        src_methods = self.collect_src_method()
        dev_tests = self.collect_dev_test()
        prompt = open("prompt.txt", "w")

        for mod_class, methods in src_methods.items():
            test_suite = dev_tests[mod_class]

            # read code source from file
            path_to_src = os.path.join(self.dir_src_classes,  mod_class.replace('.', '/') + '.java')
            s = open(os.path.join(self.buggy_dir, path_to_src), "r", errors='ignore')
            src_under_test = s.readlines()

            # read testcase source from file
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
                llm_prompt = "The method looks like: \n" + "```java\n" + method_src + "```\nThe corresponding tests written by developer looks like: \n" + "```java\n" + matching_test + "```\nProvide regression tests of method based on developer tests. Maximum number of regression tests is 5. \n"

                messages = [
                    {
                        "role": "system",
                        "content": ("You are a test generating assistant. You will be provided with source code and corresponding tests written by developer. "
                                    "Your task is providing regression tests based on the developer tests. "
                                    )
                    },
                    {
                        "role": "user",
                        "content": llm_prompt
                    }
                ]
                prompt.write(llm_prompt)
                response = openai.ChatCompletion.create(
                    model="gpt-3.5-turbo-0613",
                    messages=messages,
                )

                response_message = response["choices"][0]["message"]
                prompt.write("\n" + response_message["content"])
                print(response_message)



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

    def num_tokens_from_messages(self, messages, model="gpt-3.5-turbo-0613"):
        try:
            encoding = tiktoken.encoding_for_model(model)
        except KeyError:
            encoding = tiktoken.get_encoding("cl100k_base")
        if model == "gpt-3.5-turbo-0613":  # note: future models may deviate from this
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

if __name__ == "__main__":
    mode = sys.argv[1]

    if mode == "test":
        pid, vid = "Lang", "1"
        env = D4JEnv(pid, vid)
        env.amplify_test()


        # if not os.path.exists(f"./data/{pid}-{vid}b"):
        #     os.system(f"mkdir -p ./data/{pid}-{vid}b")
        # with open(f"./data/{pid}-{vid}b/dev_fixed_code.txt", "w") as f:
        #     f.write(dev_fixed_code)
        # with open(f"./data/{pid}-{vid}b/llm_refactored_code.txt", "w") as f:
        #     f.write(llm_refactored_code)