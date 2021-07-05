package pr.lib.ds.tree.fenwicktreearr;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FenwickTreeArrTest {

    @Test
    public void testCountSmaller() {
        int[] nums = {5, 2, 6, 1};
        List<Integer> expectedResult = Arrays.asList(2, 1, 1, 0);
        List<Integer> result = countSmaller(nums);

        assertEquals(result, expectedResult);
    }

    private List<Integer> countSmaller(int[] nums) {
        int offset = 10000;
        int size = offset * 2 + 1;
        int[] tree = new int[size];

        FenwickTreeArr fenwick = new FenwickTreeArr(size);

        List<Integer> result = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int smaller_count = fenwick.query(nums[i] + offset);
            result.add(smaller_count);
            fenwick.update(nums[i] + offset, 1);
        }

        Collections.reverse(result);
        return result;
    }
}