import array_max_value.ArrayMaxValue;
import binary_search.BinarySearch;
import bubble_sort.BubbleSort;
import merge_sort.MergeSort;
import org.jetbrains.annotations.NotNull;
import quick_sort.QuickSort;
import rabin_karp.RabinKarpExtended;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//       int[] arr =
//               {24, 65, 23, -5, 876, 30, -9, 0, 12, 543, 984, 98, -43, -7, -23, 1, 2, 3, 4, 89, 56, 3, -23,
//                624, 265, 8823, -225, 86, 309, -29, 20, 162, 53, 94, 908, -3, -37, -389, 123, 222, 332, 44232, 8, 5236,
//                3124, 5565, 28873, -4345, 8676, 39, -922, 540, 182, 9543, 3445, 9995, -3243, -740, -2203, 2145, 2258, 31,
//                7204, 605, 203, -50, 8076, 300, -90, 909, 102, 5043, 9084, 908, -430, -70, -2030, 10, 200, 300, 400, 8090};

//        int[] arr = {3, -6, 2, 9 , 1, 7, 45, -1, 4};

//        int[] arr = {99, 76, 45, 32, 1, 32, 24, 21, 11, 51, 9, 8, 7};

        int[] arr = new int[99999];
        for (int i = 0; i < 99999; i++) {
            arr[i] = new Random().nextInt(100000) + 1;
        }

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 99; i++) {
            list.add(String.valueOf(i));
        }

        //ArrayMaxValue
//        System.out.println(ArrayMaxValue.getMaxValue(arr));

        //BinarySearch
//        try {
//            System.out.println(list.get(new BinarySearch(list).search("76")));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Not found!");
//        }

        //BubbleSort
//        long start = System.currentTimeMillis();
//        BubbleSort.sort(arr);
//        System.out.println(System.currentTimeMillis() - start);


        //QuickSort
//        long start = System.currentTimeMillis();
//        QuickSort.sort(arr);
//        System.out.println(System.currentTimeMillis() - start);


        //MergeSort
//        long start = System.currentTimeMillis();
//        MergeSort.mergeSort(arr);
//        System.out.println(System.currentTimeMillis() - start);
//
//
//        System.out.println(Arrays.toString(arr));

        String text ="Attention! Ela! World and Everybody! Hello! helLo! Hellohhhh!";
        searchByRabinKarp(text, "Hello");
        System.out.println();
        searchByRabinKarp(text, "AoA");
    }

    @NotNull
    private static List<Integer> searchByRabinKarp(String baseText, String query) {
        RabinKarpExtended text = new RabinKarpExtended(baseText);
        List<Integer> searchingText = text.search(query);
        if (searchingText.isEmpty()) System.out.println("Not founded!");
        return searchingText;
    }
}
