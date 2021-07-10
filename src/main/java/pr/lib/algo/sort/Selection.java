package pr.lib.algo.sort;

import static pr.lib.algo.sort.SortUtil.*;

/**
 * Selection sort. Implements Selection Sort.
 * At iteration i, all elements on left (0 to i-1) is sorted
 * Find the index of the smallest element on the right of i (including i), and swap with i
 */
public class Selection {

    /**
     * At iteration i, all elements on left (0 to i-1) is sorted
     * Find the index of the smallest element on the right of i (including i), and swap with i
     * Takes O(n^2) runtime. Sorting order is unstable.
     * However, memory update is only O(n), as it selects the minimum index each iteration before
     * deciding to move the element to i
     * @param a Comparable element
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;

            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }

            if (min != i) {
                swap(a, i, min);
            }
        }
    }
}
