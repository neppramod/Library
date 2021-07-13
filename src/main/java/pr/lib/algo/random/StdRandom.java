package pr.lib.algo.random;

import java.util.Random;
import static pr.lib.algo.sort.SortUtil.*;

/**
 * Shuffle Algorithm
 */
public class StdRandom {

    /**
     * Implements knuth shuffle algorithm
     * In ith iteration, pick integer r between 0 and i, uniformly at random and swap a[i] and a[r]
     *
     * @param a Input element
     */
    public static void shuffle(Comparable[] a) {
        Random r = new Random();

        for (int i = 0; i < a.length; i++) {
            int randomIndex = r.nextInt(i + 1);  // 0 to i
            if (randomIndex != i) {
                swap(a, i, randomIndex);
            }
        }
    }
}
