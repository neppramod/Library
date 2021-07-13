package pr.lib.algo.sort;

/**
 * Utility that defines some sorting methods
 */
public abstract class SortUtil {

    /**
     * Compare a and b and return true if a comes before b when sorted
     * @param a first parameter of type Comparable
     * @param b second parameter of type Comparable
     * @return returns true if a comes before b when sorted
     */
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * Compare a and b and return true if b comes before a when sorted
     * @param a first parameter of type Comparable
     * @param b second parameter of type Comparable
     * @return returns true if b comes before a when sorted
     */
    public static boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    /**
     * Swap index i and j in a
     * @param a  array of Comparable
     * @param i  left index
     * @param j right index
     */
    public static void swap(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
