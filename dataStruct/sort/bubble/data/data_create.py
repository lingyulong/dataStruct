import random

data = []
for i in range(100000):
    item = random.randint(0, 1000000)
    data.append(item)

print(data)

with open('datas.txt', 'w') as f:
    for i in range(len(data)):
        item = data[i]
        f.write(str(item))
        if i != len(data)-1:
            f.write(",")








