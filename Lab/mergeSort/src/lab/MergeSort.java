package lab;

public class MergeSort {
    public static int[] mergeSort(int x[], int y[], int z[], int n) {

        for (int i = 0; i < x.length; i++) {

            int j = BinarySearch.binarySearch(x[i], y);
            if (z[i + j] == x[i]) {
                z[i + j + 1] = x[i];
            } else {
                z[i + j] = x[i];
            }

        }

        for (int i = 0; i < y.length; i++) {

            int j = BinarySearch.binarySearch(y[i], x);
            if (z[i + j] == y[i]) {
                z[i + j + 1] = y[i];
            } else {
                z[i + j] = y[i];
            }
        }

        return z;

    }
}
