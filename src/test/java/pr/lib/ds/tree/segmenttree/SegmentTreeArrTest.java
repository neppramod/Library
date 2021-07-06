package pr.lib.ds.tree.segmenttree;

import org.junit.jupiter.api.Test;
import pr.lib.ds.tree.segmenttree.SegmentTreeArr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTreeArrTest {

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

        List<Integer> result = new ArrayList<>();
        SegmentTreeArr segmentTree = new SegmentTreeArr(size);

        for (int i = nums.length - 1; i >= 0; i--) {
            int smaller_count = segmentTree.query(0, nums[i] + offset);
            result.add(smaller_count);
            segmentTree.update(nums[i] + offset, 1);
        }

        Collections.reverse(result);
        return result;
    }
}