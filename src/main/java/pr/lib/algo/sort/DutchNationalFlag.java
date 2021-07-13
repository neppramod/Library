package pr.lib.algo.sort;

import static pr.lib.algo.sort.SortUtil.*;

/**
 * Implement dutch national flag algorithm to sort only 3 types of elements
 */
public class DutchNationalFlag {
    /**
     * Implement dutch national flag algorithm to sort elements in array a
     * @param a elements in array
     * @param mid pass in the element that is considered mid, elements smaller than mid go to left half,
     *            and greater than mid to right half
     */
    public void sort(Comparable[] a, Comparable mid) {
        int n = a.length;

        int i = 0, j = 0, k = n-1;

        while (j <= k) {
            if (less(a[j], mid)) {  // lower
                swap(a, i, j);
                i++;
                j++;
            } else if (greater(a[j], mid)) {  // higher
                swap(a, j, k);
                k--;
            } else {  // middle
                j++;
            }
        }
    }
}
