package pr.lib.algo.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {
    @Test
    public void testSort() {
        Integer[] arr = {9, 3, 6, 3, 6, 3, 5, 12, 92};
        Integer val = (Integer) QuickSelect.select(arr, 4);  // 4th smallest is 6
        assertTrue(val == 6);
    }
}