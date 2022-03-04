import quick_sort.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//       int[] arr =
//               {24, 65, 23, -5, 876, 30, -9, 0, 12, 543, 984, 98, -43, -7, -23, 1, 2, 3, 4, 89, 56, 3, -23,
//                624, 265, 8823, -225, 86, 309, -29, 20, 162, 53, 94, 908, -3, -37, -389, 123, 222, 332, 44232, 8, 5236,
//                3124, 5565, 28873, -4345, 8676, 39, -922, 540, 182, 9543, 3445, 9995, -3243, -740, -2203, 2145, 2258, 31,
//                7204, 605, 203, -50, 8076, 300, -90, 909, 102, 5043, 9084, 908, -430, -70, -2030, 10, 200, 300, 400, 8090};

        int[] arr = new int[9999];
       for (int i = 0; i < 9999; i++) {
           arr[i] = new Random().nextInt(100000) + 1;
       }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 99; i++) {
            list.add(String.valueOf(i));
        }

        //ArrayMaxValue
//        System.out.println(ArrayMaxValue.getMaxValue(arr));

        //BinarySearch

/*        list.remove(76);
        try {
            System.out.println(list.get(new BinarySearch(list).search("76")));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Not found!");
             }*/

        //BubbleSort
//        BubbleSort.sort(arr);

        //QuickSort
        long start = System.currentTimeMillis();
        QuickSort.sort(arr);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(Arrays.toString(arr));


    }
}
