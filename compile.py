import os

path = "./docker/workspace/data/Jsoup-69b/"
sample_list = os.listdir(path)
sample_list = [int(i) for i in sample_list]
sample_num = max(sample_list) + 1
print(sample_num)