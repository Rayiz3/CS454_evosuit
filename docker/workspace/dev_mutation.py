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

if __name__ == "__main__":
    mode = sys.argv[1]
    if mode == "debug":
        pid, vid = "Compress", "34"
        sample_list = os.listdir(f"./data/{pid}-{vid}b/tests/")
        sample_list = [int(i) for i in sample_list]
        for sample_num in sample_list:
            if not os.path.exists(f"./data/{pid}-{vid}b/mut_results/{sample_num}/mutation_log/{pid}/run_mutation.pl.log"):
                print(sample_num)
                os.system(f"rm -rf ./data/{pid}-{vid}b/tests/{sample_num}")
                os.system(f"rm -rf ./data/{pid}-{vid}b/mut_results/{sample_num}")

        # buggy_dir = f"/tmp/{pid}-{vid}b"
        # if not os.path.exists(f"./data/{pid}-{vid}b/dev"):
        #     os.system(f"mkdir ./data/{pid}-{vid}b/dev")
        
        # os.system(f"rm -rf {buggy_dir}")
        # os.system(f"defects4j checkout -p {pid} -v {vid}b -w {buggy_dir}")
        # if not os.path.exists(os.path.join(buggy_dir, "tests.trigger")):
        #     os.system(f"cd {buggy_dir} && defects4j export -p tests.trigger -o tests.trigger")
        # if not os.path.exists(os.path.join(buggy_dir, "dir.src.tests")):
        #     os.system(f"cd {buggy_dir} && defects4j export -p dir.src.tests -o dir.src.tests")

        # with open(os.path.join(buggy_dir, "dir.src.tests"), 'r') as f:
        #     dir_src_tests = f.read()
        # with open(os.path.join(buggy_dir, "tests.trigger"), 'r') as f:
        #     tests_trigger = f.read().split('\n')

        # for triggered_test in tests_trigger:
        #     path_to_test = os.path.join(dir_src_tests, triggered_test.replace('.', '/').split('::')[0] + '.java')
        #     print(path_to_test)
        #     os.system(f"cd {buggy_dir} && rm -rf {path_to_test}")

        # # run_mutation
        # os.system(f"cd {buggy_dir} && defects4j mutation")
        # os.system(f"cd {buggy_dir} && cp kill.csv mutants.log .mutation.log summary.csv testMap.csv ~/workspace/data/{pid}-{vid}b/dev")


    if mode == "exec":
        for dname in sorted(os.listdir("./data")):
            try:
                pid, vid = dname.split("-")
                vid = vid[:-1]
            except:
                continue
            
            buggy_dir = f"/tmp/{pid}-{vid}b"
            if not os.path.exists(f"./data/{pid}-{vid}b/dev"):
                os.system(f"mkdir ./data/{pid}-{vid}b/dev")
            
            os.system(f"rm -rf {buggy_dir}")
            os.system(f"defects4j checkout -p {pid} -v {vid}b -w {buggy_dir}")
            if not os.path.exists(os.path.join(buggy_dir, "tests.trigger")):
                os.system(f"cd {buggy_dir} && defects4j export -p tests.trigger -o tests.trigger")
            if not os.path.exists(os.path.join(buggy_dir, "dir.src.tests")):
                os.system(f"cd {buggy_dir} && defects4j export -p dir.src.tests -o dir.src.tests")

            with open(os.path.join(buggy_dir, "dir.src.tests"), 'r') as f:
                dir_src_tests = f.read()
            with open(os.path.join(buggy_dir, "tests.trigger"), 'r') as f:
                tests_trigger = f.read().split('\n')

            for triggered_test in tests_trigger:
                path_to_test = os.path.join(dir_src_tests, triggered_test.replace('.', '/').split('::')[0] + '.java')
                os.system(f"rm -rf {os.path.join(buggy_dir, path_to_test)}/")

                # run_mutation
                os.system(f"cd {buggy_dir} && defects4j mutation")
                os.system(f"cd {buggy_dir} && cp kill.csv mutants.log .mutants.log summary.csv testMap.csv ~/workspace/data/{pid}-{vid}b/dev")