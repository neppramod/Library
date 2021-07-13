package pr.lib.algo.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    @Test
    public void testSort() {
        Integer[] arr = {9, 3, 6, 3, 6, 3, 5, 12, 92};
        MergeSort.sort(arr);
        assertArrayEquals(arr, new Integer[]{3, 3, 3, 5, 6, 6, 9, 12, 92});
    }
}