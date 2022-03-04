package merge_sort;

public class MergeSort {
    public static void mergeSort(int[] array) {

        if(array.length == 1) {
            return;
        }

        int n = array.length;
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] left, int[] right) {

        int step = 0;
        int leftStep = 0;
        int rightStep = 0;

        while (leftStep < left.length && rightStep < right.length) {
            if (right[rightStep] < left[leftStep]) {
                array[step++] = right[rightStep++];
            } else {
                array[step++] = left[leftStep++];
            }
        }

        if(leftStep < left.length) {
            for(int i = step; i < array.length; i++) {
                array[i] = left[leftStep++];
            }
        }
        if(rightStep < right.length) {
            for(int i = step; i < array.length; i++) {
                array[i] = right[rightStep++];
            }
        }
    }
}
