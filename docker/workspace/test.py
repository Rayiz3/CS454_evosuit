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

f = open("data.txt", "r")
versions = f.readlines()

rates = [0.6, 0.7, 0.55, 0.65, 0.75]
for version in versions:
    pid, vid = version.split(",")
    pid = pid.strip()
    vid = vid.strip()

    # for rate in rates:
    #     os.system(f"cd ./test_suites/{pid}/{vid}/mutation/{rate}/ && tar -jxvf {pid}-{vid}b-evosuite.1.tar.bz2")
    #     os.system(f"cd ./test_suites/{pid}/{vid}/mutation/{rate}/collected_tests/{pid}/{vid}/mutation/{rate}/evosuite-tests && tar -jcvf {pid}-{vid}b-evosuite.1.tar.bz2 ./org")
    #     os.system(f"cp ./test_suites/{pid}/{vid}/mutation/{rate}/collected_tests/{pid}/{vid}/mutation/{rate}/evosuite-tests/{pid}-{vid}b-evosuite.1.tar.bz2 ./evosuite_amp_data/{pid}-{vid}b/mutation/{rate}/")

    # for rate in rates:
    #     os.system(f"cd /defects4j/framework/util && ./fix_test_suite.pl -p {pid} -v {vid}b -d ~/workspace/evosuite_amp_data/{pid}-{vid}b/crossover/{rate}")
    #     os.system(f"cd /defects4j/framework/util && ./fix_test_suite.pl -p {pid} -v {vid}b -d ~/workspace/evosuite_amp_data/{pid}-{vid}b/mutation/{rate}")

    # for rate in rates:
        # os.system(f"cd /defects4j/framework/bin && ./run_mutation.pl -p {pid} -v {vid}b -d ~/workspace/evosuite_amp_data/{pid}-{vid}b/crossover/{rate} -o ~/workspace/evosuite_amp_data/{pid}-{vid}b/crossover/{rate}/")

        # os.system(f"cd /defects4j/framework/bin && ./run_mutation.pl -p {pid} -v {vid}b -d ~/workspace/evosuite_amp_data/{pid}-{vid}b/mutation/{rate} -o ~/workspace/evosuite_amp_data/{pid}-{vid}b/mutation/{rate}/")