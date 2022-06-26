import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Sort {
    /*
    各个算法的最好时间复杂度，最坏时间复杂度，空间复杂度；
    各个算法的应用场景：很多算法的时间和空间复杂度是一样的，那为啥还要在已经有某一算法的前提下，还要设计其他具有同样时间复杂度的算法；
    写个测试代码，测试100个序列长度为10， 100， 1000， 10000情况下，各算法的时间，做一个直观的对比；
     */

    public static void formatPrint(int[] data, String msg) {
        System.out.println(msg);
        for(int item : data) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void insertedSort(int[] data) {
        /*
        直接插入排序：
            从未排序的数据中取出一个插入到已排序数据中的合适位置
         */
//        formatPrint(data, "原始数据");


        for(int i=0;i<data.length-1;i++) {
            // 有序数据最后一个元素所在的索引
            int end_index = i;
            // 无序数组中第一个位置处的值
            int cur_data = data[end_index+1];
            // 将值插入到有序数组中合适的位置
            while(end_index>=0 && cur_data < data[end_index]) {
                data[end_index+1] = data[end_index];
                end_index--;
            }
            data[end_index+1] = cur_data;

//            formatPrint(data, "第" + (i+1) + "次排序之后的结果");
//            System.out.println();
        }

//        formatPrint(data, "排序之后的结果");
    }

    public static void shellSort(int[] data) {
        /*
        希尔排序：
            将整个数据分成多组，每组单独进行直接插入排序。
            最后使用组长为 1 进行排序，即直接插入排序
         */
//        formatPrint(data, "原始数据");

        // 分成若干组，间隔 gap 个元素的数据构成一组
        for(int gap = data.length/2; gap>0; gap=gap/2) {
            // 我原来的想法是分成若成组，每组单独进行直接插入排序，这里没有这么处理，而是把各组混一块进行单独处理了
            // 多个组的元素从第2个元素一直到最后一个元素，a组元素后接着b组元素，b组元素后接着c组元素。。。
            for(int i=gap; i<data.length; i++) {
                int end_index = i-gap;
                // 无序数组中第一个位置处的值
                int cur_data = data[end_index+gap];
                // 将值插入到有序数组中合适的位置
                while(end_index>=0 && cur_data < data[end_index]) {
                    data[end_index+gap] = data[end_index];
                    end_index = end_index - gap;
                }
                data[end_index+gap] = cur_data;
            }

//            formatPrint(data, "gap = " + gap + "得到的排序结果");
//            System.out.println();
        }

//        formatPrint(data, "排序之后的结果");
    }

    public static void selectSort(int[] data) {
        /*
        选择排序：
            从未排序的部分选择出最小的元素，和未排序部分第一个位置的数据进行交换
         */
//        formatPrint(data, "原始数据");

        int begin = 0;
        int end = data.length-1;
        while(begin <= end) {
            int min_index = begin;
            for(int i=begin+1; i<=end; i++) {
                if(data[min_index] > data[i]) {
                    min_index = i;
                }
            }
//            System.out.println("begin=" + begin + " end=" + end + " min_index=" + min_index);
            swap(data, begin, min_index);

            begin++;

//            formatPrint(data, "未排序的起点索引 begin = " + begin + "之前都是已经正确排序的结果");
//            System.out.println();
        }
    }

    public static void heapSort(int[] data) {
        Heap.ascHeapSort(data);
    }

    public static void bubbleSort(int[] data) {
        /*
        冒泡排序：
            进行n轮冒泡，每轮会得到一个最大值放到未排序部分的最后一个位置：
                在未排序部分对比相邻元素，不符合排序规则的就调换相关位置。
         */
//        formatPrint(data, "原始数据");

        int end = data.length - 1;
        while(end>=1) {
            for(int i=0; i<=end-1; i++) {
                if(data[i] > data[i+1]) {
                    swap(data, i,i+1);
                }
            }
            end--;

//            formatPrint(data, "未排序的最后位置索引 end = " + end + "之后都是已经正确排序的结果");
//            System.out.println();
        }
    }


    public static int getBaseIndex(int[] data, int left, int right, boolean isOptimize) {
        if(left>=right) {
            return left;
        }
        if(!isOptimize) {
            // 直接把最左边的数据当做选择的基准值
            return left;
        }else {
            // 从 left、right、mid 中选择中位数作为基准值
            int min_index = (left + right)/2;
            if(data[min_index] >= data[left]) {
                return data[min_index]<=data[right] ? min_index : right;
            }else {
                return data[min_index]>=data[right] ? min_index : left;
            }
        }
    }

    public static int _quickSort(int[] data, int left, int right, boolean isOptimize) {
        /*
        从待排序区间中任选一个基准值，
            （1）将其放到最终有序数组中的对应位置，
            （2）左边区间的值均比其小，右边区间的值均比其大
        这里使用交换法，即交换位置。
         */
        // 1.选择基准值和下标
        int base_index = getBaseIndex(data, left, right, isOptimize);
        // 将基准下标挪到最左边，同时让右指针先走，这样可以保证：
        // （1）右指针走到左指针时，左指针的值小于基准值，
        // （2）左指针走到指针时，因为右指针先走的，所以右指针指向的值一定小于基准值，即左/右指针的值小于基准值，
        // 这样在交换 left 和 基准下标值后，可以保证基准值在正确的位置
        swap(data, left, base_index);
        base_index= left;
        int base_data = data[base_index];
//        formatPrint(data, "交换left 和 base_index 之后的数据");
        // 2.对 left - right 区间进行一次完整的交换顺序
        while(left < right) {
            // 2.1从右边开始找到第一个小于 cur_data 值的位置
            while(left<right && data[right]>=base_data) {
                right--;
            }
            // 2.2从左边开始找到第一个大于 cur_data 的位置
            while(left<right && data[left]<=base_data) {
                left++;
            }
            // 3.交换数据
//            System.out.println("left = " + left + " " + data[left] + " right = " + right + " " + data[right]);
            swap(data, left, right);
//            formatPrint(data, "交换之后的数据");
//            System.out.println();

        }
        // 4.将相遇点放到最终有序数组中的对应位置
        swap(data, base_index, left);
//        formatPrint(data, "base_index 和 left 交换之后的数据");
//        System.out.println();
        // 返回相遇点下标
        return left;
    }


    public static void quickSort(int[] data, int left, int right) {
        /*
        递归进行快速排序：对 left - right 区间进行快速排序
         */

        // 1.待排序部分只有一个元素或者没有元素时，排序结束
        if (left >= right) {
            return;
        }
        // 2.对 left-right 部分进行交换顺序，得到基准值所在的真实位置，左边值均比其小，右边值均比其大
        int mid_index = _quickSort(data, left, right, false);

        // 3.对 left - min-1 部分进行快速排序
        quickSort(data, left, mid_index-1);
        // 4.对 min - right 部分进行快速排序
        quickSort(data, mid_index+1, right);
    }

    public static void quickSort2(int[] data, int left, int right) {
        /*
        借助栈进行快速排序：对 left - right 区间进行快速排序
         */
//        formatPrint(data, "原始数据");
        // 1.right、left 指针依次进栈
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(right);
        stack.push(left);

        // 2.遍历，直到栈空
        while(!stack.empty()) {
            // 3.出栈，得到 left、right 指针
            left = stack.pop();
            right = stack.pop();

            // 4.对 left-right 部分进行交换顺序，得到基准值所在的真实位置，左边值均比其小，右边值均比其大
//            System.out.println("left = " + left + " right = " + right);
            int mid_index = _quickSort(data, left, right, true);
//            System.out.println("mid_index = " + mid_index);
//            formatPrint(data, "left = " + left + " right = " + right + " 交换排序之后数据");
//            System.out.println();

            // 5.将右边区间 right、mid_index+1 指针依次进栈
            if(mid_index+1 < right) {
                stack.push(right);
                stack.push(mid_index + 1);
            }
            // 6.将左边区间 mid_index-1、left 指针依次进栈
            if(left < mid_index - 1) {
                stack.push(mid_index - 1);
                stack.push(left);
            }

        }
    }



    public static void mergeSort(int[] data, int left, int right) {
        /*
        归并排序
            先使每个子序列有序，再使子序列之间有序
         */
        // 1.只有一个元素时，直接返回
        if(left >= right) {
            return;
        }
        // 2.找到中间值
        int mid_index= (left + right) /2 ;
        // 3.将 left - 中间值变成有序子序列
        mergeSort(data, left, mid_index);
        // 4.将 中间值+1 - right 变成有序子序列
        mergeSort(data, mid_index+1, right);

        // 5.合并两个有序子序列
        int begin1 = left;
        int end1 = mid_index;
        int begin2 = mid_index + 1;
        int end2 = right;
        int[] tmp = new int[right - left + 1];
        int point = 0;
        while(begin1<=end1 && begin2<=end2) {
            if(data[begin1] <= data[begin2]) {
                tmp[point++] = data[begin1++];
            }else {
                tmp[point++] = data[begin2++];
            }
        }
        while(begin1<=end1) {
            tmp[point++] = data[begin1++];
        }
        while(begin2<=end2) {
            tmp[point++] = data[begin2++];
        }
        for(int i=0;i<point;i++) {
            data[left + i] = tmp[i];
        }
    }


    public static void testPerformance() {

        Random random = new Random();
        int[] arrNums = {10, 100, 1000, 10000, 100000, 1000000};
        int testNum = 10;
        long[][] times = new long[7][arrNums.length];

        for(int i=0;i<arrNums.length; i++) {
            int item = arrNums[i];
            // 1.创建第 testNum 轮数据用于测试
            for(int k=0; k<testNum; k++) {
                // 2.生成数据
                int[] tmpData = new int[item];
                for(int j=0;j<item; j++) {
                    int tmp = random.nextInt(item*20);
                    tmpData[j] = tmp;
                }


                // 3.计算该轮数据的排序时间
                // 必须在这里获取数据，不然就会在已经排序后的数据上进行排序
                int [] data = Arrays.copyOf(tmpData, tmpData.length);
                Long startTime = System.currentTimeMillis();
                insertedSort(data);
                Long endTime = System.currentTimeMillis();
                times[0][i] += endTime - startTime;

                data = Arrays.copyOf(tmpData, tmpData.length);
                startTime = System.currentTimeMillis();
                shellSort(data);
                endTime = System.currentTimeMillis();
                times[1][i] += endTime - startTime;

                data = Arrays.copyOf(tmpData, tmpData.length);
                startTime = System.currentTimeMillis();
                selectSort(data);
                endTime = System.currentTimeMillis();
                times[2][i] += endTime - startTime;

                data = Arrays.copyOf(tmpData, tmpData.length);
                startTime = System.currentTimeMillis();
                heapSort(data);
                endTime = System.currentTimeMillis();
                times[3][i] += endTime - startTime;

                data = Arrays.copyOf(tmpData, tmpData.length);
                startTime = System.currentTimeMillis();
                bubbleSort(data);
                endTime = System.currentTimeMillis();
                times[4][i] += endTime - startTime;

                data = Arrays.copyOf(tmpData, tmpData.length);
                startTime = System.currentTimeMillis();
                quickSort2(data, 0, data.length-1);
                endTime = System.currentTimeMillis();
                times[5][i] += endTime - startTime;

                data = Arrays.copyOf(tmpData, tmpData.length);
                startTime = System.currentTimeMillis();
                mergeSort(data, 0, data.length-1);
                endTime = System.currentTimeMillis();
                times[6][i] += endTime - startTime;

            }

            // 4.计算平均时间
            times[0][i] /= testNum;
            times[1][i] /= testNum;
            times[2][i] /= testNum;
            times[3][i] /= testNum;
            times[4][i] /= testNum;
            times[5][i] /= testNum;
            times[6][i] /= testNum;
        }


        // 5.打印时间
        for(int i=0; i<times.length; i++) {
            for(int j=0; j<times[i].length; j++) {
                System.out.print(times[i][j] + ",");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        int[] data = {3, 44, 28, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

//        insertedSort(data);
//        shellSort(data);
//        selectSort(data);
//        heapSort(data);
//        bubbleSort(data);
//        quickSort(data, 0, data.length-1);
//        quickSort2(data, 0, data.length-1);
//        formatPrint(data, "最终排序的结果");
//        mergeSort(data, 0, data.length-1);
//        formatPrint(data, "最终排序的结果");

        testPerformance();
    }


}
