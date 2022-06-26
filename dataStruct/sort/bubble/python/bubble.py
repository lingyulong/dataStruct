import copy
import time


def swap(data, i, j):
    tmp = data[i]
    data[i] = data[j]
    data[j] = tmp


def bubbleSort(data) :
    """
    冒泡排序：
        进行n轮冒泡，每轮会得到一个最大值放到未排序部分的最后一个位置：
        在未排序部分对比相邻元素，不符合排序规则的就调换相关位置。
     """
    end = len(data) - 1
    while end>=1:
        for i in range(0, end):
            if data[i] > data[i+1] :
                swap(data, i, i+1)
        end = end - 1

def get_datas():
    """
    从指定文件中获取用于排序的数据
    :return:
    """
    with open("./datas.txt", "r") as f:
        data = f.read()
        data = data.split(",")
        # print(data)
    return data



if __name__ == '__main__':
    # data = [1, 3, 8, 2, 5, 4]
    # bubbleSort(data)
    # print(data)

    data = get_datas()
    times = []
    testNum = 100
    for i in range(testNum):
        # 必须在这里获取数据，不然就会在已经排序后的数据上进行排序
        tmpData = copy.deepcopy(data)

        startTime = time.time() * 1000  # time()返回的是秒，变成毫秒
        bubbleSort(tmpData)
        endTime = time.time() * 1000  # time()返回的是秒，变成毫秒

        print("第 " + str(i+1) + "次的排序时间：" + str(endTime - startTime))
        times.append(endTime - startTime)


    print("所有实验的时间：：" + str(times))



