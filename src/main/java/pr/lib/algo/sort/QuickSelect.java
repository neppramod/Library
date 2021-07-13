package pr.lib.algo.sort;

import pr.lib.algo.random.StdRandom;
import static pr.lib.algo.sort.SortUtil.*;

/**
 * Select kth smallest element in array a
 * Runtime is O(n) in average
 */
public class QuickSelect {
    /**
     * Select kth smallest element in a, where a is not sorted
     * @param a Array element
     * @param k kth index
     * @return return element that is kth smallest
     */
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);

        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);

            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return a[k];
            }
        }

        return a[k];
    }


    /**
     * partition step
     * Scan i from left to right so long as (a[i] < a[lo])
     * Scan j from right to left so long as (a[j] > a[lo])
     * Swap a[i] with a[j]
     * When pointers cross, swap a[lo] with a[j]
     *
     * @param a Array elements
     * @param lo    low index
     * @param hi    high index
     * @return    return index, where elements at a[i] is bigger than elements at a[lo] to a[i-1]
     * and smaller than elements at a[i+1] to a[hi]
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;

        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;
                }
            }

            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            } else {
                swap(a, i, j);
            }
        }
        swap(a, lo, j);
        return j;
    }
}
