package pr.lib.algo.random;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class StdRandomTest {
    @Test
    public void testSort() {
        Integer[] arr = {9, 3, 6, 3, 6, 3, 5, 12, 92};
        StdRandom.shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }
}