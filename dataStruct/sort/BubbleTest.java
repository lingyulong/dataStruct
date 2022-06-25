import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class BubbleTest {

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

        boolean isSorted = false;
        int end = data.length - 1;
        while(end>=1) {
            for(int i=0; i<=end-1; i++) {
                if(data[i] > data[i+1]) {
                    Sort.swap(data, i,i+1);
                    isSorted = true;
                }
            }
            if(isSorted == false) {
                break;
            }
            end--;
        }

    }

    /**
     冒泡排序优化2：添加有序区界定符
     */
    public static void bubbleSort3(int[] data) {

        boolean isSorted = false;
        int sortBorder = data.length - 1;
        int tmpSortBorder = sortBorder;
        int end = data.length - 1;
        while(end>=1) {
            for(int i=0; i<=sortBorder-1; i++) {
                if(data[i] > data[i+1]) {
                    Sort.swap(data, i,i+1);
                    isSorted = true;
                    tmpSortBorder = i;
                }
            }
            if(isSorted == false) {
                break;
            }
            sortBorder = tmpSortBorder;
            end--;
        }
    }

    /**
     * 从指定文件中获取 int[] ,并返回
     * @return
     */
    public static int[] getDatas() {

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


    public static void oneAlgo() {
        int[] data = getDatas();

        int testNum = 100;
        long[] times = new long[testNum];
        for(int i=0;i<testNum;i++) {
            // 必须在这里获取数据，不然就会在已经排序后的数据上进行排序
            int [] tmpData = Arrays.copyOf(data, data.length);

            long startTime = System.currentTimeMillis();
            bubbleSort1(tmpData);
            long endTime = System.currentTimeMillis();

            System.out.println("第 " + (i+1) + "次的排序时间：" + (endTime - startTime));
            times[i] = (endTime - startTime);
        }
        System.out.println("所有实验的时间：");
        System.out.println(Arrays.toString(times));
    }


    public static void allAlgo() {
        int[] data = getDatas();

        int testNum = 100;
        long[] times1 = new long[testNum];
        long[] times2 = new long[testNum];
        long[] times3 = new long[testNum];
        for(int i=0;i<testNum;i++) {
            // 必须在这里获取数据，不然就会在已经排序后的数据上进行排序
            int [] tmpData = Arrays.copyOf(data, data.length);
            long startTime = System.currentTimeMillis();
            bubbleSort1(tmpData);
            long endTime = System.currentTimeMillis();
            System.out.println("原始冒泡排序：第 " + (i+1) + "次的排序时间：" + (endTime - startTime));
            times1[i] = (endTime - startTime);


            tmpData = Arrays.copyOf(data, data.length);
            startTime = System.currentTimeMillis();
            bubbleSort2(tmpData);
            endTime = System.currentTimeMillis();
            System.out.println("冒泡排序优化1：第 " + (i+1) + "次的排序时间：" + (endTime - startTime));
            times2[i] = (endTime - startTime);

            tmpData = Arrays.copyOf(data, data.length);
            startTime = System.currentTimeMillis();
            bubbleSort3(tmpData);
            endTime = System.currentTimeMillis();
            System.out.println("冒泡排序优化2：第 " + (i+1) + "次的排序时间：" + (endTime - startTime));
            times3[i] = (endTime - startTime);

        }
        System.out.println("所有实验的时间：");
        System.out.println(Arrays.toString(times1));
        System.out.println(Arrays.toString(times2));
        System.out.println(Arrays.toString(times3));
    }///

    public static void main(String[] args) {

//        int[] data1 = {1,5, 8, 10, 2, 5, 3};
//        bubbleSort1(data1);
//        System.out.println(Arrays.toString(data1));
//
//        int[] data2 = {1,5, 8, 10, 2, 5, 3};
//        bubbleSort2(data2);
//        System.out.println(Arrays.toString(data2));
//
//        int[] data3 = {1,5, 8, 10, 2, 5, 3};
//        bubbleSort3(data3);
//        System.out.println(Arrays.toString(data3));

        allAlgo();

    }



}
