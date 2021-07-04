package pr.lib.ds.tree.segmenttree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


/*
   https://leetcode.com/problems/count-of-smaller-numbers-after-self/

   Below we use SegmentTree to Solve Count Of Smaller Number After Self.

   This is a self contained file, contains the whole SegmentTree class as inner class.

   This is to show how we can copy the whole SegmentTree and Merger code for coding challenge
   in a single file.

   Question
   ========

   You are given an integer array nums and you have to return a new counts array.
   The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

   I found following code to use 271 ms (which is much higher than other solutions using primitive array).
   Here, we initialize Integer (wrapper) object of size OFFSET * 2 + 1, which is much bigger in comparison to primitive array in size.

    All the SegmentTree code including the Merger interface is part of the library.

    If you can use this library as a dependency (or jar), you don't need the code below the OFFSET declaration (import it from the library)
 */

public class CountOfSmallerNumberAfterSelfTest {

    @Test
    public void testCountOfSmallerNumberAfterSelfTest() {
        int[] nums = {5,2,6,1};
        List<Integer> expectedResult = Arrays.asList(2, 1, 1, 0);
        CountOfSmallerNumberAfterSelfTest sol = new CountOfSmallerNumberAfterSelfTest();
        assertEquals(sol.countSmaller(nums), expectedResult);
    }

    public List<Integer> countSmaller(int[] nums) {
        Merger<Integer> merger = (x, y) -> (x == null ? (y == null ? null : y) : x + y);
        SegmentTree<Integer> segmentTree = new SegmentTree<>(merger);

        Integer[] arr = new Integer[OFFSET * 2 + 1];
        Arrays.fill(arr, 0);


        segmentTree.buildTree(arr);

        List<Integer> result = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            // add 1 to index offset + nums[i]
            int updateIndex = nums[i] + OFFSET;

            // find count of elements between 0 and nums[i] - 1 + offset
            result.add(segmentTree.querySegmentTree(0, updateIndex - 1));

            // increment value at updateIndex
            segmentTree.updateSegmentTree(updateIndex, updateIndex, 1);
        }

        Collections.reverse(result);

        return result;
    }

    static final int OFFSET = 10001;


    /************************
     All the code below this is part of the library. You can just import the merger and SegmentTree in
     your class.
     ************************/

    static interface Merger<T> {
        T merge(T x, T y);
    }

    static class SegmentTree<T> {
        private T[] tree;
        private T[] lazy;
        private int N;
        private final Merger<T> merger;

        /**
         * @param merger Interface that defines how merge happens
         *               Because of this update uses same logic, instead of being able to set update
         *               values separately. Logic can be provided using lambda
         */
        public SegmentTree(Merger merger) {
            this.merger = merger;
        }

        /**
         * @param arr  Builds tree using arr
         */
        public void buildTree(T[] arr) {
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException("Input arr is null/blank");
            } else if (this.merger == null) {
                throw new IllegalArgumentException("Merger cannot be null");
            }

            N = arr.length;
            tree = (T[]) new Object[4 * N];
            lazy = (T[]) new Object[4 * N];

            buildTree(arr, 0, 0, N-1);
        }

        /**
         * @param left  Left index of query. Must fall within array index boundary 0 to N-1
         * @param right Right index of query. Must fall within array index boundary 0 to N-1
         * @return Returns the result of merge logic between elements from left to right index on arr
         */
        public T querySegmentTree(int left, int right) {
            if (checkBoundary(left, right)) {
                return querySegmentTree(0, 0, N-1, left, right);
            }

            return null;
        }

        /**
         * Updates values from left to right in arr with merge of current value and val
         * @param left Left index of query. Must fall within array index boundary 0 to N-1
         * @param right Right index of query. Must fall within array index boundary 0 to N-1
         * @param val  Value to merge with current values from left to right index in arr
         */
        public void updateSegmentTree(int left, int right, T val) {
            if (checkBoundary(left, right)) {
                updateSegmentTree(0, 0, N-1, left, right, val);
            }
        }

        /**
         * Prints current values of tree array
         */
        public void printTree() {
            System.out.println(Arrays.toString(tree));
        }

        private void buildTree(T[] arr, int treeIndex, int lo, int hi) {

            if (lo == hi) {
                tree[treeIndex] = arr[lo];
                return;
            }

            int mid = lo + (hi - lo) / 2;
            buildTree(arr, treeIndex * 2 + 1, lo, mid);
            buildTree(arr, treeIndex * 2 + 2, mid + 1, hi);
            tree[treeIndex] = merger.merge(tree[treeIndex * 2 + 1], tree[treeIndex * 2 + 2]);
        }

        private T querySegmentTree(int treeIndex, int lo, int hi, int left, int right) {
            if (lo > hi || lo > right || hi < left) {
                return null;
            }
            if (lazy[treeIndex] != null) {
                tree[treeIndex] = merger.merge(tree[treeIndex], lazy[treeIndex]);

                if (lo != hi) {
                    lazy[treeIndex * 2 + 1] = merger.merge(lazy[treeIndex * 2 + 1], lazy[treeIndex]);
                    lazy[treeIndex * 2 + 2] = merger.merge(lazy[treeIndex * 2 + 2], lazy[treeIndex]);
                }

                lazy[treeIndex] = null;
            }

            if (left <= lo && hi <= right) {
                return tree[treeIndex];
            }

            int mid = lo + (hi - lo) / 2;

            if (left > mid) {
                return querySegmentTree(treeIndex * 2 + 2, mid+1, hi, left, right);
            } else if (right <= mid) {
                return querySegmentTree(treeIndex * 2 + 1, lo, mid, left, right);
            }

            T leftQuery = querySegmentTree(treeIndex * 2 + 1, lo, mid, left, mid);
            T rightQuery = querySegmentTree(treeIndex * 2 + 2, mid + 1, hi, mid + 1, right);

            return merger.merge(leftQuery, rightQuery);
        }

        private void updateSegmentTree(int treeIndex, int lo, int hi, int left, int right, T val) {
            if (lo > hi || lo > right || hi < left) {
                return;
            }

            if (lazy[treeIndex] != null) {
                tree[treeIndex] = merger.merge(tree[treeIndex], lazy[treeIndex]);

                if (lo != hi) {
                    lazy[treeIndex * 2 + 1] = merger.merge(lazy[treeIndex * 2 + 1], lazy[treeIndex]);
                    lazy[treeIndex * 2 + 2] = merger.merge(lazy[treeIndex * 2 + 2], lazy[treeIndex]);
                }

                lazy[treeIndex] = null;
            }

            if (left <= lo && hi <= right) {
                tree[treeIndex] = merger.merge(tree[treeIndex], val);

                if (lo != hi) {
                    lazy[treeIndex * 2 + 1] = merger.merge(lazy[treeIndex * 2 + 1], val);
                    lazy[treeIndex * 2 + 2] = merger.merge(lazy[treeIndex * 2 + 2], val);
                }

                return;
            }

            int mid = lo + (hi - lo) / 2;
            updateSegmentTree(treeIndex * 2 + 1, lo, mid, left, right, val);
            updateSegmentTree(treeIndex * 2 + 2, mid + 1, hi, left, right, val);

            tree[treeIndex] = merger.merge(tree[treeIndex * 2 + 1], tree[treeIndex * 2 + 2]);
        }

        private boolean checkBoundary(int left, int right) {
            if (left < 0 || right >= N) {
                throw new IllegalArgumentException("left and right outside boundary");
            }

            return true;
        }
    }
}
