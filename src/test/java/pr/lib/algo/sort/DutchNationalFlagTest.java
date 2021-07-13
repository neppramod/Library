package pr.lib.algo.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DutchNationalFlagTest {
    @Test
    void testSort() {
        Integer[] a = {0, 1, 1, 2, 2, 0, 1, 1, 0, 1, 0, 2, 2, 2, 1, 1, 0, 0, 0, 2, 1, 2, 0, 0, 1, 2};
        DutchNationalFlag sol = new DutchNationalFlag();
        sol.sort(a, 1);
        System.out.println(Arrays.toString(a));

        assertEquals(a[0] < a[a.length-1], true);
        assertEquals(a[0] < a[a.length/2], true);
        assertEquals(a[8] < a[9], true);
    }
}