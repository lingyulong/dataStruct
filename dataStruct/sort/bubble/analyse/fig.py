import pandas as pd
from matplotlib import pyplot as plt

def fig_algos_times():

    data = pd.read_excel('./各个排序算法的效率.xlsx', sheet_name='各个排序算法用时实验', engine='openpyxl')
    print(data)
    print()

    algos = data['算法'].values
    arr_nums = list(data.columns[2:])
    times = data[arr_nums].values
    markers = ['v', 'o', '1', 's', 'p', 'h', '+']
    print(algos)
    print(arr_nums)
    print(times)
    print()

    plt.figure(figsize=(10, 8))
    for i in range(len(times)):
        time  = times[i]
        algo = algos[i]
        marker = markers[i]
        plt.plot(range(len(arr_nums)), time, marker=marker, label=algo, markersize=15)
    plt.legend(loc='upper left', fontsize=25)
    plt.xticks(range(len(arr_nums)), arr_nums, fontsize=20)
    plt.xticks(fontsize=20)
    plt.xlabel("排序元素的个数", fontsize=20)
    plt.ylabel("排序花费的时间（单位：毫秒）", fontsize=20)
    plt.title("各个算法在不同数据集下10次排序的平均时间", fontsize=20)
    plt.savefig('./各个算法的排序时间.png')
    plt.show()

def fig_bubble_times():
    data_java = [16772, 16303, 12429, 12588, 12531, 12644, 12574, 12753, 12589, 12616, 12608, 12722, 12732, 12710, 12586, 12676, 12540, 12607, 12575, 12564, 12494, 12403, 12502, 12463, 12402, 12481, 12417, 12503, 12426, 12406, 12377, 12478, 12408, 12495, 12541, 12591, 12581, 12597, 12600, 12570, 12562, 12413, 12483, 12607, 12570, 12532, 12617, 12499, 12613, 12535, 12500, 12453, 12533, 12434, 12468, 12508, 12477, 12539, 12516, 12489, 12473, 12461, 12553, 12596, 12597, 12645, 12631, 12632, 12690, 12586, 12616, 12647, 12820, 16593, 16081, 14556, 12074, 11772, 12227, 12438, 12700, 13588, 13847, 13289, 13512, 15663, 15246, 15027, 15896, 15146, 14631, 13439, 13710, 13767, 13591, 13648, 13416, 13558, 13872, 13437]
    plt.figure(figsize=(10, 8))
    plt.plot(range(len(data_java)), data_java)
    plt.xticks(fontsize=20)
    plt.xlabel("实验次数", fontsize=20)
    plt.ylabel("排序花费的时间（单位：毫秒）", fontsize=20)
    plt.title("冒泡排序算法100次实验的排序时间", fontsize=20)
    plt.grid(True)
    plt.savefig('./冒泡排序算法的排序时间.png')
    plt.show()





if __name__ == '__main__':
    # fig_algos_times()
    fig_bubble_times()





