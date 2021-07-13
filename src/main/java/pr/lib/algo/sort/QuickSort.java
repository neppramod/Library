package pr.lib.algo.sort;

import pr.lib.algo.random.StdRandom;

import static pr.lib.algo.sort.SortUtil.*;

/**
 * Sorting: QuickSort Implementation
 * Best case O(nlogn), Worse case O(n^2)
 * Inplace, unstable sorting algorithm
 * Quite fast in practice, if we use shuffle to randomize
 * Worse case happens when pivot is always extreme smallest or largest. Array is sorted, sorted in reverse order or same element
 */
public class QuickSort {

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

    /**
     * Implement quick sort
     * Shuffle the array before sorting
     * @param a
     */
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    /**
     * Implement quick sort.
     * Element at index j will be sorted in its place in partition step
     * Left and right half are then sorted recursively
     *
     * @param a  Array elements
     * @param lo  low index
     * @param hi high index
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int j = partition(a, lo, hi);  // element at j will be sorted
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
}
