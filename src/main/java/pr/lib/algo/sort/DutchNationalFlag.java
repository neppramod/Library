package pr.lib.algo.sort;

import static pr.lib.algo.sort.SortUtil.*;

/**
 * Implement dutch national flag algorithm to sort only 3 types of elements
 */
public class DutchNationalFlag {


    /**
     * Implement dutch national flag algorithm to sort elements in array a
     * @param a
     */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * Implement dutch national flag algorithm to sort elements in array a
     * @param a elements in array
     * Compare elements with v and smaller elements come to left and bigger go to right
     *          If elements are already in correct place i and k does not move and j scans from lo to hi and completes
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int i = lo, j = lo, k = hi;
        Comparable v = a[lo];

        // j to k is not sorted, i to j is and k to n-1 is
        while (j <= k) {
            int cmp = a[j].compareTo(v);

            if (cmp < 0) {
                swap(a, i, j);
                i++;
                j++;
            } else if (cmp > 0) {
                swap(a, j, k);
                k--;
            } else {
                j++;
            }
        }

        sort(a, lo, i - 1);
        sort(a, j + 1, hi);
    }
}
