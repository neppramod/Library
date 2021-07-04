package pr.lib.ds.tree.segmenttree;

import java.util.Arrays;

/**
 * @param <T> Type of object in segment tree
 *
 *   See examples in SegmentTreeTest
 *
 *   This class fits more to range sum query, rather than min, max. Those use-cases may need
 *           separate class.
 *
 */
public class SegmentTree<T> {
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
        T rightQuery = querySegmentTree(treeIndex * 2 + 2, mid + 1, hi, mid, right);

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
