package pr.lib.ds.tree.fenwicktreearr;

/**
 * Fenwick Tree using arr
 * In this implementation the tree is primitive int. Since most of the fenwick tree application is int based
 */
public class FenwickTreeArr {
    int[] tree;

    public FenwickTreeArr(int n) {
        tree = new int[n + 1];
    }
    public int query(int index) {
        int result = 0;

        while (index > 0) {
            result += tree[index];
            index -= index & -index;
        }

        return result;
    }

    public void update(int index, int val) {
        index++;  // Binary index tree index is 1 more than original index
        while (index < tree.length) {
            tree[index] += val;
            index += index & - index;
        }
    }
}
