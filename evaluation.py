import os
import csv
import numpy
import sys

if __name__ == "__main__":
    mode = sys.argv[1]
    if mode == "llm":
        fieldnames = ['fault_version', 'mean_killed', 'med_killed', 'std', 'unique_mut_killed', 'original_TS_mut_score', 'increased_killed', 'kloc']
        if not os.path.exists("./eval_result.csv"):
            r = open('./eval_result.csv', 'w', newline='')
            writer = csv.DictWriter(r, fieldnames=fieldnames)
            writer.writeheader()
            r.close()

        for dname in sorted(os.listdir("./llm_data")):
            try:
                pid, vid = dname.split("-")
                vid = vid[:-1]
            except:
                continue
            
            f = open(f"./llm_data/{pid}-{vid}b/dev/kill.csv", "r")
            m = open(f"./llm_data/{pid}-{vid}b/dev/mutants.log", "r")
            mut_num = len(m.readlines())
            # print(mut_num)
            dev = csv.reader(f)
            sample_list = os.listdir(f"./llm_data/{pid}-{vid}b/mut_results/")
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
                t = open(f"./llm_data/{pid}-{vid}b/mut_results/{sample_num}/mutation_log/{pid}/llm/{vid}b.{sample_num}.kill.csv", "r")
                llm = csv.reader(t)
                
                paths = []
                for x in os.walk(f"./llm_data/{pid}-{vid}b/tests/{sample_num}"):
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
    
    if mode == "evosuite":
        fieldnames = ['fault_version', 'type', 'rate', 'killed', 'unique_mut_killed', 'original_TS_mut_score', 'increased_killed', 'kloc']
        rates = [0.55, 0.6, 0.65, 0.7, 0.75]

        if not os.path.exists("./evosuite_result.csv"):
            r = open('./evosuite_result.csv', 'w', newline='')
            writer = csv.DictWriter(r, fieldnames=fieldnames)
            writer.writeheader()
            r.close()

        for dname in sorted(os.listdir("./evosuite_amp_data")):
            try:
                pid, vid = dname.split("-")
                vid = vid[:-1]
            except:
                continue
            
            if not os.path.exists(f"./evosuite_amp_data/{pid}-{vid}b/dev/kill.csv"):
                continue
            f = open(f"./evosuite_amp_data/{pid}-{vid}b/dev/kill.csv", "r")
            m = open(f"./evosuite_amp_data/{pid}-{vid}b/dev/mutants.log", "r")
            mut_num = len(m.readlines())
            # print(mut_num)
            dev = csv.reader(f)
            
            dev_killed = set()
            for i, line in enumerate(dev):
                if i == 0:
                    continue
                if line[1] == 'FAIL' or line[1] == 'EXC' or line[1] == 'TIME':
                    dev_killed.add(int(line[0]))
            

            for rate in rates:
                killed_mutants = 0
                kloc = 0

                # crossover rate change
                if not os.path.exists(f"./evosuite_amp_data/{pid}-{vid}b/crossover/{rate}/mutation_log"):
                    continue
                if not os.path.exists(f"./evosuite_amp_data/{pid}-{vid}b/crossover/{rate}/mutation_log/{pid}/evosuite/{vid}b.1.kill.csv"):
                    continue
                t = open(f"./evosuite_amp_data/{pid}-{vid}b/crossover/{rate}/mutation_log/{pid}/evosuite/{vid}b.1.kill.csv", "r")
                evo = csv.reader(t)
                
                paths = []
                os.system(f"mkdir ./evosuite_amp_data/{pid}-{vid}b/crossover/{rate}/test")
                os.system(f"cd ./evosuite_amp_data/{pid}-{vid}b/crossover/{rate} && tar -jxvf {pid}-{vid}b-evosuite.1.tar.bz2 -C ./test")
                for x in os.walk(f"./evosuite_amp_data/{pid}-{vid}b/crossover/{rate}/test"):
                    paths.append(x)
                if len(paths[-1][-1]) == 0:
                    continue
                test_path = paths[-1][0] + '/' + paths[-1][-1][0]
                l = open(test_path, "r")
                kloc = len(l.readlines()) * (1/1000)
                l.close()

                evo_killed = set()
                for i, line in enumerate(evo):
                    if i == 0:
                        continue
                    if line[1] == 'FAIL' or line[1] == 'EXC' or line[1] == 'TIME':
                        evo_killed.add(int(line[0]))
                # print(len(evo_killed - dev_killed))
                evo_exc_dev = evo_killed - dev_killed
                killed_mutants = len(evo_exc_dev)
                unique_mutants = killed_mutants
                t.close()

                row = {
                    'fault_version': f"{pid}-{vid}b", 
                    'type': 'crossover',
                    'rate': rate,
                    'killed': round(killed_mutants, 1), 
                    'unique_mut_killed': round(killed_mutants, 1), 
                    'original_TS_mut_score': round((len(dev_killed) / mut_num) * 100, 1), 
                    'increased_killed': round((killed_mutants / mut_num) * 100, 1), 
                    'kloc': round(kloc, 1)
                    }
                
                with open("./evosuite_result.csv", "a", errors='ignore') as r:
                    writer = csv.DictWriter(r, fieldnames=fieldnames)
                    writer.writerow(row)

            for rate in rates:
                killed_mutants = 0
                kloc = 0

                # mutation rate change
                if not os.path.exists(f"./evosuite_amp_data/{pid}-{vid}b/mutation/{rate}/mutation_log"):
                    continue
                if not os.path.exists(f"./evosuite_amp_data/{pid}-{vid}b/mutation/{rate}/mutation_log/{pid}/evosuite/{vid}b.1.kill.csv"):
                    continue
                t = open(f"./evosuite_amp_data/{pid}-{vid}b/mutation/{rate}/mutation_log/{pid}/evosuite/{vid}b.1.kill.csv", "r")
                evo = csv.reader(t)
                
                paths = []
                os.system(f"mkdir ./evosuite_amp_data/{pid}-{vid}b/mutation/{rate}/test")
                os.system(f"cd ./evosuite_amp_data/{pid}-{vid}b/mutation/{rate} && tar -jxvf {pid}-{vid}b-evosuite.1.tar.bz2 -C ./test")
                for x in os.walk(f"./evosuite_amp_data/{pid}-{vid}b/mutation/{rate}/test"):
                    paths.append(x)
                if len(paths[-1][-1]) == 0:
                    continue
                test_path = paths[-1][0] + '/' + paths[-1][-1][0]
                l = open(test_path, "r")
                kloc = len(l.readlines()) * (1/1000)
                l.close()

                evo_killed = set()
                for i, line in enumerate(evo):
                    if i == 0:
                        continue
                    if line[1] == 'FAIL' or line[1] == 'EXC' or line[1] == 'TIME':
                        evo_killed.add(int(line[0]))
                # print(len(evo_killed - dev_killed))
                evo_exc_dev = evo_killed - dev_killed
                killed_mutants = len(evo_exc_dev)
                unique_mutants = killed_mutants
                t.close()

                row = {
                    'fault_version': f"{pid}-{vid}b", 
                    'type': 'mutation',
                    'rate': rate,
                    'killed': round(killed_mutants, 1), 
                    'unique_mut_killed': round(killed_mutants, 1), 
                    'original_TS_mut_score': round((len(dev_killed) / mut_num) * 100, 1), 
                    'increased_killed': round((killed_mutants / mut_num) * 100, 1), 
                    'kloc': round(kloc, 1)
                    }
                
                with open("./evosuite_result.csv", "a", errors='ignore') as r:
                    writer = csv.DictWriter(r, fieldnames=fieldnames)
                    writer.writerow(row)
            f.close()