public class Heap {

    // 参考：https://blog.csdn.net/qq_51086532/article/details/120104501

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

    public static void adjustDown(int[] data, int parent, int len) {
        /*
            向下调整的前提：根元素的左右子树都已经是小(大)根堆了
            len:数组的长度
         */
        while(2*parent+1<len) {
            // 1.找到根节点左右子树中的最小值
            int left = parent * 2 + 1;
            int min_index = left;
            int right = parent * 2 + 2;
            if(right<=len-1) {
                min_index = (data[left] <= data[right] ? left : right);
            }
//            System.out.println("parent = " + parent + " left = " + left + " right = " + right + " min_index = " + min_index);

            // 2.根节点左右子树中的最小值大于根元素，说明符合小根堆的定义了，调整结束
            if(data[min_index] >= data[parent]) {
//                System.out.println("根节点左右子树中的最小值大于根元素，说明符合小根堆的定义了，调整结束");
//                System.out.println();
                return;
            }

            // 3.根节点左右子树中的最小值小于根元素，说明不符合小根堆的定义，开始调整
            // 4.根节点与最小值交换位置。之后最小值位置即为新的根节点，继续判断是否符合小根堆的定义，即重复步骤1-4，直到符合步骤2，或到了数组的最后一个元素
            swap(data, min_index, parent);
            parent = min_index;
//            formatPrint(data,"根节点与最小值交换位置的数组");
        }
    }


    public static void adjustUp(int[] data, int child, int len) {
        /*
            向上调整的前提：子元素的父节点之上的节点对应的子树都已经是小根堆了
            len:数组的长度
         */
        while(child>=1) {
            // 1.找到父节点
            int parent = (child - 1)/2;

            // 2.如果父节点的值小于子节点，说明符合小根堆的定义了，调整结束
            if(data[parent] <= data[child]) {
                return;
            }
            // 3.如果父节点的值大于子节点，说明不符合小根堆的定义，开始调整
            // 4.父节点与子节点交换位置。之后父节点位置即为新的子节点，继续判断是否符合小根堆的定义，即重复步骤1-4，直到符合步骤2，或到了数组的第一个元素
            swap(data, parent, child);
            child = parent;
        }
    }

    public static void createHeap(int[] data, boolean isDown) {
        int len = data.length;

        if(isDown) {
            // 方法1：通过向下调整构造小根堆
            // 1.最后一个非叶子节点对应的左右子树肯定是小根堆，所以可以使用向下调整算法
            // 2.经过上面的调整，前面的非叶子节点的父节点对应的左右子树肯定是小根堆，所以可以使用向下调整算法
            for(int i=(len-2)/2; i>=0; i--) {
                adjustDown(data, i, len);
            }
        }else {
            // 方法2：通过向上调整构造小根堆
            // 1.第二个元素对应的父节点之上的子树是小根堆，所以可以使用向上调整算法
            // 2.经过上面的调整，后面的节点的父节点之上的子树肯定是小根堆，所以可以使用向上调整算法
            for(int i=0; i<len; i++) {
                adjustUp(data, i, len);
            }
        }
    }

    public static void descHeapSort(int[] data) {
        // 1.构造小根堆
//        createHeap(data, true);
        createHeap(data, false);
//        formatPrint(data, "构造的小根堆");

        // 2.数组的第1个节点就是最小值，将第1个与最后一个节点交换位置，然后将 0 - len-2 区间的数据向下调整得到小根堆
        // 3.重复步骤2，直到区间变成 0 - 0，此时的数组就已经是最小值了
        for(int end_index= data.length-1; end_index>0; end_index--) {
            swap(data, 0, end_index);
            adjustDown(data, 0, end_index);
        }

    }


    public static void ascHeapSort(int[] data) {
        int[] tmp = new int[data.length];
        for(int i=0; i<data.length; i++) {
            tmp[i] = data[i];
        }

        descHeapSort(tmp);

        for(int i=0; i<data.length; i++) {
            data[i] = tmp[data.length-1-i];
        }
    }


    public static void main(String[] args) {
        int[] data = {3, 44, 28, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        createHeap(data, true);
////        createHeap(data, false);
//        formatPrint(data, "构造的小根堆");
//        descHeapSort(data);
        ascHeapSort(data);
        formatPrint(data, "排序后的数据");
    }
}
