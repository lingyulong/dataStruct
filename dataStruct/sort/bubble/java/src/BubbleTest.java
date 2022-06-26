import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;

public class BubbleTest {

    private static int arrNum = 100000;
    private static int testNum = 100;

    /**
     * 从指定文件中获取 int[] ,并返回
     * @return
     */
    public static int[] getDatasFromFile() {

        StringBuilder sb = new StringBuilder();
        String path = "E:\\自研成果\\面试\\codes\\algo\\datas.txt";
        try{
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(path));
            String s = null;
            while((s = br.readLine())!=null){
                sb.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        String[] tmpData = sb.toString().split(",");
        int[] datas = new int[tmpData.length];
        for(int i=0; i<tmpData.length; i++) {
            datas[i] = Integer.parseInt(tmpData[i].replace("\r\n", ""));
        }
        return datas;
    }

    public static int[] getDatasFromRandom() {
        Random random = new Random();


        int[] data = new int[arrNum];
        for(int i=0;i<arrNum; i++) {
            data[i] = random.nextInt(arrNum*20);
        }
        return data;
    }


    /**
    冒泡排序：
    */
    public static void bubbleSort1(int[] data) {
        int end = data.length - 1;
        while(end>=1) {
            for(int i=0; i<=end-1; i++) {
                if(data[i] > data[i+1]) {
                    Sort.swap(data, i,i+1);
                }
            }
            end--;
        }
    }


    /**
     冒泡排序优化1：添加标记 isSorted
     */
    public static void bubbleSort2(int[] data) {
        int end = data.length - 1;
        boolean isSorted = true;
        while(end>=1) {
            isSorted = true;
            for(int i=0; i<=end-1; i++) {
                if(data[i] > data[i+1]) {
                    Sort.swap(data, i,i+1);
                    isSorted = false;
                }
            }
            if(isSorted == true) {
                return;
            }
            end--;
        }
    }

    /**
     冒泡排序优化2：添加有序区界定符
     */
    public static void bubbleSort3(int[] data) {
        int sortBorder = data.length - 1;
        int tmpSortBorder = sortBorder;
        int end = data.length - 1;
        boolean isSorted = true;
        while(end>=1) {
            isSorted = true;
            for(int i=0; i<=sortBorder-1; i++) {
                if(data[i] > data[i+1]) {
                    Sort.swap(data, i,i+1);
                    isSorted = false;
                    tmpSortBorder = i;
                }
            }
            if(isSorted == true) {
                return ;
            }
            sortBorder = tmpSortBorder;
            end--;
        }
    }



    public static long[] bubbleSortLog1(int[] data) {
        long iterNum = 0;    // 排序的轮数
        long compareNum = 0;    // 相邻数据比较大小的次数
        long exchangeNum = 0;    // 交换相邻数据的次数
        long codeLineNum = 0;    // 冒泡排序实际执行的代码行数

        int end = data.length - 1;
        while(end>=1) {
            iterNum++;
            codeLineNum++;  // while 比较了一次

            for(int i=0; i<=end-1; i++) {
                compareNum++;   // 相邻数据比较了一次大小
                if(data[i] > data[i+1]) {
                    Sort.swap(data, i,i+1);

                    exchangeNum++;  // 相邻数据交换了一次
                    codeLineNum++;  // swap 交换了一次
                }
                codeLineNum +=2;  // for 比较了一次，if 比较了一次
            }
            end--;
            codeLineNum++;  // end 减少了一次
        }
        return new long[]{iterNum, compareNum, exchangeNum, codeLineNum};
    }


    public static long[] bubbleSortLog2(int[] data) {
        long iterNum = 0;    // 排序的轮数
        long compareNum = 0;    // 相邻数据比较大小的次数
        long exchangeNum = 0;    // 交换相邻数据的次数
        long codeLineNum = 0;    // 冒泡排序实际执行的代码行数

        int end = data.length - 1;
        boolean isSorted = true;
        while(end>=1) {
            iterNum++;

            isSorted = true;
            codeLineNum +=2 ;  // while 比较了一次， isSorted 赋值了一次

            for(int i=0; i<=end-1; i++) {
                compareNum++;   // 相邻数据比较了一次大小
                if(data[i] > data[i+1]) {
                    Sort.swap(data, i,i+1);
                    isSorted = false;

                    exchangeNum++;  // 相邻数据交换了一次
                    codeLineNum += 2;  // swap 交换了一次，isSorted 赋值了一次
                }
                codeLineNum += 2;  // for 比较了一次， if 比较了一次
            }

            if(isSorted == true) {
                return new long[]{iterNum, compareNum, exchangeNum, codeLineNum};
            }
            end--;
            codeLineNum++;  // isSorted 比较了一次， end 赋值了一次
        }

        return new long[]{iterNum, compareNum, exchangeNum, codeLineNum};
    }


    public static long[] bubbleSortLog3(int[] data) {
        long iterNum = 0;    // 排序的轮数
        long compareNum = 0;    // 相邻数据比较大小的次数
        long exchangeNum = 0;    // 交换相邻数据的次数
        long codeLineNum = 0;    // 冒泡排序实际执行的代码行数

        int sortBorder = data.length - 1;
        int tmpSortBorder = sortBorder;
        int end = data.length - 1;
        boolean isSorted = true;
        while(end>=1) {
            iterNum++;
            codeLineNum++;  // while 比较了一次

            isSorted = true;
            codeLineNum++;  // isSorted 赋值了一次

            for(int i=0; i<=sortBorder-1; i++) {
                codeLineNum++;  // for 比较了一次

                compareNum++;   // 相邻数据比较了一次大小
                codeLineNum++;  // if 比较了一次
                if(data[i] > data[i+1]) {
                    Sort.swap(data, i,i+1);
                    exchangeNum++;  // 相邻数据交换了一次
                    codeLineNum++;  // swap 交换了一次

                    isSorted = false;
                    tmpSortBorder = i;
                    codeLineNum++;  // isSorted 赋值了一次
                    codeLineNum++;  // tmpSortBorder 赋值了一次
                }
            }

            codeLineNum++;  // isSorted 比较了一次
            if(isSorted == true) {
                return new long[]{iterNum, compareNum, exchangeNum, codeLineNum};
            }
            sortBorder = tmpSortBorder;
            end--;
            codeLineNum++;  // sortBorder 赋值了一次
            codeLineNum++;  // end 赋值了一次
        }

        return new long[]{iterNum, compareNum, exchangeNum, codeLineNum};
    }


    /**
     * 统计冒泡算法的时间，使用的是纯净的算法，没有任何多余代码
     * 日志代码会严重影响到运行的时间
     */
    public static void allAlgo() {

        long[] times1 = new long[testNum];
        long[] times2 = new long[testNum];
        long[] times3 = new long[testNum];

        // 中间变量
        int [] tmpData = new int[arrNum];
        long start = 0;
        long end = 0;


        for(int i=0;i<testNum;i++) {
            int[] data = getDatasFromRandom();

            // 必须在这里获取数据，不然就会在已经排序后的数据上进行排序
            tmpData = Arrays.copyOf(data, data.length);
            start = System.currentTimeMillis();
            bubbleSort1(tmpData);
            end = System.currentTimeMillis();
            times1[i] = end - start;
            System.out.println("原始冒泡排序：第 " + (i+1) + "次的排序的时间：" + times1[i]);


            tmpData = Arrays.copyOf(data, data.length);
            start = System.currentTimeMillis();
            bubbleSort2(tmpData);
            end = System.currentTimeMillis();
            times2[i] = end - start;
            System.out.println("冒泡排序优化1：第 " + (i+1) + "次的排序的时间：" + times2[i]);


            tmpData = Arrays.copyOf(data, data.length);
            start = System.currentTimeMillis();
            bubbleSort3(tmpData);
            end = System.currentTimeMillis();
            times3[i] = end - start;
            System.out.println("冒泡排序优化2：第 " + (i+1) + "次的排序的时间：" + times3[i]);


        }
        System.out.println("所有实验的时间迭代次数：");
        System.out.println(Arrays.toString(times1));
        System.out.println(Arrays.toString(times2));
        System.out.println(Arrays.toString(times3));
    }

    /**
     * 统计冒泡算法的迭代轮数、比较次数、交换次数
     */
    public static void allAlgoLog() {


        long[] iterNums1 = new long[testNum];
        long[] compareNums1 = new long[testNum];
        long[] exchangeNums1 = new long[testNum];
        long[] codeLineNums1 = new long[testNum];

        long[] iterNums2 = new long[testNum];
        long[] compareNums2 = new long[testNum];
        long[] exchangeNums2 = new long[testNum];
        long[] codeLineNums2 = new long[testNum];

        long[] iterNums3 = new long[testNum];
        long[] compareNums3 = new long[testNum];
        long[] exchangeNums3 = new long[testNum];
        long[] codeLineNums3 = new long[testNum];

        // 中间变量
        int [] tmpData = new int[arrNum];
        long[] tmp =  new long[4];

        for(int i=0;i<testNum;i++) {
            int[] data = getDatasFromRandom();

            // 必须在这里获取数据，不然就会在已经排序后的数据上进行排序
            tmpData = Arrays.copyOf(data, data.length);
            tmp =  bubbleSortLog1(tmpData);

            iterNums1[i] = tmp[0];
            compareNums1[i] = tmp[1];
            exchangeNums1[i] = tmp[2];
            codeLineNums1[i] = tmp[3];
            System.out.println("原始冒泡排序：第 " + (i+1) + "次的排序：");
            System.out.println("迭代轮数：" + iterNums1[i] + " 比较次数：" + compareNums1[i] + " 交换次数：" + exchangeNums1[i] + " 执行代码行数：" + codeLineNums1[i]);



            tmpData = Arrays.copyOf(data, data.length);
            tmp = bubbleSortLog2(tmpData);

            iterNums2[i] = tmp[0];
            compareNums2[i] = tmp[1];
            exchangeNums2[i] = tmp[2];
            codeLineNums2[i] = tmp[3];
            System.out.println("冒泡排序优化1：第 " + (i+1) + "次的排序：");
            System.out.println("迭代轮数：" + iterNums2[i] + " 比较次数：" + compareNums2[i] + " 交换次数：" + exchangeNums2[i] + " 执行代码行数：" + codeLineNums2[i]);


            tmpData = Arrays.copyOf(data, data.length);
            tmp = bubbleSortLog3(tmpData);

            iterNums3[i] = tmp[0];
            compareNums3[i] = tmp[1];
            exchangeNums3[i] = tmp[2];
            codeLineNums3[i] = tmp[3];
            System.out.println("冒泡排序优化2：第 " + (i+1) + "次的排序：");
            System.out.println("迭代轮数：" + iterNums3[i] + " 比较次数：" + compareNums3[i] + " 交换次数：" + exchangeNums3[i] + " 执行代码行数：" + codeLineNums3[i]);


        }
        System.out.println("所有实验的迭代次数：");
        System.out.println(Arrays.toString(iterNums1));
        System.out.println(Arrays.toString(iterNums2));
        System.out.println(Arrays.toString(iterNums3));
        System.out.println();
        System.out.println("所有实验的比较次数：");
        System.out.println(Arrays.toString(compareNums1));
        System.out.println(Arrays.toString(compareNums2));
        System.out.println(Arrays.toString(compareNums3));
        System.out.println();
        System.out.println("所有实验的交换次数：");
        System.out.println(Arrays.toString(exchangeNums1));
        System.out.println(Arrays.toString(exchangeNums2));
        System.out.println(Arrays.toString(exchangeNums3));
        System.out.println();
        System.out.println("所有实验的执行代码行数：");
        System.out.println(Arrays.toString(codeLineNums1));
        System.out.println(Arrays.toString(codeLineNums2));
        System.out.println(Arrays.toString(codeLineNums3));
        System.out.println();
    }

    public static void main(String[] args) {

//        int[] data1 = {1,5, 8, 10, 2, 5, 3};
////        bubbleSort1(data1);
//        bubbleSortLog1(data1);
//        System.out.println(Arrays.toString(data1));
//
//        int[] data2 = {1,5, 8, 10, 2, 5, 3};
////        bubbleSort2(data2);
//        bubbleSortLog2(data2);
//        System.out.println(Arrays.toString(data2));
//
//        int[] data3 = {1,5, 8, 10, 2, 5, 3};
////        bubbleSort3(data3);
//        bubbleSortLog3(data3);
//        System.out.println(Arrays.toString(data3));

//        allAlgo();
        allAlgoLog();

    }



}
