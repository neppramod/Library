package pr.lib.algo.sort;

import static pr.lib.algo.sort.SortUtil.less;

/**
 * Merge Sort: Implements Merge sort
 */
public class Merge {
    /**
     * Implement merge operation, where a[lo..mid] is sorted and a[mid+1..hi] is sorted
     * O(nlogn) runtime guarantee, stable, but uses at least O(n) memory
     * @param a  Array of elements
     * @param aux Use auxiliary array to merge
     * @param lo  low index
     * @param mid  mid index
     * @param hi high index
     */
    private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // Copy from array a to auxiliary array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = 0, j = mid + 1;

        // merge
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    /**
     * Merge Sort elements of array a
     * @param a Array a elements
     */
    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    /**
     * Merge Sort elements of array a
     * @param a Elements of array a of type Comparable
     * @param aux  Auxiliary array used for sorting
     * @param lo  lo index
     * @param hi hi index
     */
    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);

        if (!less(a[mid+1], a[mid])) {
            return;
        }

        merge(a, aux, lo, mid, hi);
    }
}
