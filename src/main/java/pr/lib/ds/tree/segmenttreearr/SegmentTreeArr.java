package pr.lib.ds.tree.segmenttreearr;

/**
 * Segment Tree using arr
 * In this implementation the tree is primitive int. Since most of the segment tree application is int based
 */
public class SegmentTreeArr {
    int[] tree;
    int n;

    public SegmentTreeArr(int n) {
        tree = new int[2 * n];
        this.n = n;
    }

    // update val at index
    public void update(int index, int val) {
        index += n;
        tree[index] += val;

        while(index > 0) {
            index /= 2;
            tree[index] = tree[2 * index] + tree[2 * index + 1];
        }
    }

    // query elements from index left to right-1
    public int query(int left, int right) {
        left += n;
        right += n;
        int sum = 0;

        while (left < right) {
            if (left % 2 == 1)
                sum += tree[left++];
            if (right % 2 == 1)
                sum += tree[--right];

            left /= 2;
            right /= 2;
        }

        return sum;
    }
}
