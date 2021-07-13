package pr.lib.algo.sort;

import static pr.lib.algo.sort.SortUtil.*;

/**
 * Shell Sort: Implements Shell sort
 *
 * Uses Knuth 3h + 1
 * Similar to insertion sort (move smaller elements to left), but uses h-sorting: h interleaved sorted subsequences
 * Unstable sorting
 *
 * In worst case for 3x + 1, the number of comparisons is O(N^(3/2))
 *
 * Practically useful algorithm for small arrays. E.g. bzip2, cClibc (embedded), /linux/kernel/groups.c
 */
public class Shell {

    public static void sort(Comparable[] a) {
        int n = a.length;

        int h = 1;
        while (h < n/3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    swap(a, j, j-h);
                }
            }

            h = h / 3;
        }
    }
}
