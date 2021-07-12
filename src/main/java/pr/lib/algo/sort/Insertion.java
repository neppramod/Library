package pr.lib.algo.sort;

import static pr.lib.algo.sort.SortUtil.*;

/**
 * Insertion Sort: Implements Insertion sort
 * At interation i, move ith element towards left, until a smaller elements comes on the left side
 * The invariant is that the left side is always sorted, and at each iteration we fix it it it is not
 *
 * O(n^2) running time.
 * Although it moves the element multiple time to fix the invariant on left side of i
 * The running time can be lower, if a lot of elements are already sorted
 *
 * This algorithm is very useful for sorting array that has only few elements at wrong index.
 * Also stable
 */
public class Insertion {

    /**
     * At iteration i, all elements on left (0 to i-1) is sorted
     * Element at index i is moved to left until it goes to correct index
     * @param a Comparable element
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j-1])) {
                    swap(a, j, j-1);
                } else {  // if elements are already sorted, don't go any further
                    break;
                }
            }
        }
    }
}
