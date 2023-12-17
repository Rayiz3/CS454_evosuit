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

f = open("result.java", "r")
t = open("test.txt", "w")

acc = 0
p = re.compile("public void (.*)\(\)", re.DOTALL)
count = 0
for line in f.readlines():
    if line.lstrip().startswith("@Test"):
        # counting parenthesis
        count += line.count("{")
        count -= line.count("}")

        # renaming the test
        m = p.search(line)
        if m == None:
            t.write(line)
            continue
        name = m.group(1)
        line=line.replace(name, f"test{acc}")
        t.write(line)
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
        t.write(line)
        continue
    name = m.group(1)
    line=line.replace(name, f"test{acc}")
    t.write(line)
    acc += 1