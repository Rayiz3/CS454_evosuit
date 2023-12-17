import os
import csv
import numpy

if __name__ == "__main__":
    fieldnames = ['fault_version', 'mean_killed', 'med_killed', 'std', 'unique_mut_killed', 'original_TS_mut_score', 'increased_killed', 'kloc']
    if not os.path.exists("./eval_result.csv"):
        r = open('./eval_result.csv', 'w', newline='')
        writer = csv.DictWriter(r, fieldnames=fieldnames)
        writer.writeheader()
        r.close()

    for dname in sorted(os.listdir("./data")):
        try:
            pid, vid = dname.split("-")
            vid = vid[:-1]
        except:
            continue
        
        f = open(f"./data/{pid}-{vid}b/dev/kill.csv", "r")
        m = open(f"./data/{pid}-{vid}b/dev/mutants.log", "r")
        mut_num = len(m.readlines())
        # print(mut_num)
        dev = csv.reader(f)
        sample_list = os.listdir(f"./data/{pid}-{vid}b/mut_results/")
        sample_list = [int(i) for i in sample_list]
        
        dev_killed = set()
        for i, line in enumerate(dev):
            if i == 0:
                continue
            if line[1] == 'FAIL' or line[1] == 'EXC' or line[1] == 'TIME':
                dev_killed.add(int(line[0]))
        
        killed_mutants = []
        unique_mutants = set()
        kloc = 0
        for sample_num in sample_list:
            t = open(f"./data/{pid}-{vid}b/mut_results/{sample_num}/mutation_log/{pid}/llm/{vid}b.{sample_num}.kill.csv", "r")
            llm = csv.reader(t)
            
            paths = []
            for x in os.walk(f"./data/{pid}-{vid}b/tests/{sample_num}"):
                paths.append(x)
            # print(paths[-1][0] + '/' + paths[-1][-1][0])
            test_path = paths[-1][0] + '/' + paths[-1][-1][0]
            l = open(test_path, "r")
            kloc += len(l.readlines()) * (1/1000)
            l.close()

            llm_killed = set()
            for i, line in enumerate(llm):
                if i == 0:
                    continue
                if line[1] == 'FAIL' or line[1] == 'EXC' or line[1] == 'TIME':
                    llm_killed.add(int(line[0]))
            # print(len(llm_killed - dev_killed))
            llm_exc_dev = llm_killed - dev_killed
            killed_mutants.append(len(llm_exc_dev))
            unique_mutants = unique_mutants.union(llm_exc_dev)
            t.close()


        # print(numpy.mean(killed_mutants))
        # print(numpy.median(killed_mutants))
        # print(numpy.std(killed_mutants))
        # print(len(unique_mutants))
        # print((len(dev_killed) / mut_num) * 100)
        # print((numpy.mean(killed_mutants) / mut_num) * 100)
        # print(kloc / len(sample_list))

        row = {'fault_version': f"{pid}-{vid}b", 
            'mean_killed': round(numpy.mean(killed_mutants), 1), 
            'med_killed': round(numpy.median(killed_mutants), 1), 
            'std': round(numpy.std(killed_mutants), 1), 
            'unique_mut_killed': len(unique_mutants),
            'original_TS_mut_score': round((len(dev_killed) / mut_num) * 100, 1), 
            'increased_killed': round((numpy.mean(killed_mutants) / mut_num) * 100, 1), 
            'kloc': round(kloc / len(sample_list), 1)
            }
        
        with open("./eval_result.csv", "a", errors='ignore') as r:
            writer = csv.DictWriter(r, fieldnames=fieldnames)
            writer.writerow(row)

        f.close()