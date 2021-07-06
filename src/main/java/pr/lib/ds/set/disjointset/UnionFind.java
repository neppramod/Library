package pr.lib.ds.set.disjointset;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Generic implementation of union find data structure.
 * Weighted union-find with path compression
 *
 * resultInitializer takes Integer as input and returns T to initialize res
 * Here we are passing index i used to initialize id back to the caller
 *
 * merger is logic used to merge two nodes. The interface implementation must be passed from caller
 *
 * find(i) returns result that uses merger.merge(i, j) logic.
 *
 * See unit test for usage
 * @param <T>
 */
public class UnionFind<T> {
    private int[] id;
    private int[] sz;
    private T[] res;
    private final BinaryOperator<T> merger;

    public UnionFind(int n, Function<Integer, T> resultInitializer, BinaryOperator<T> merger) {
        id = new int[n];
        sz = new int[n];
        res = (T[]) new Object[n];
        this.merger = merger;

        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
            res[i] = (T) resultInitializer.apply(i);
        }
    }

    public T find(int i) {
        int p = root(i);
        return res[p];
    }

    public void union(int i, int j) {
        int p = root(i);
        int q = root(j);
        T newResult = (T) merger.apply(res[p], res[q]);

        if (sz[p] > sz[q]) {
            sz[p] += sz[q];
            id[q] = p;
            res[p] = newResult;
        } else {
            sz[q] += sz[p];
            id[p] = q;
            res[q] = newResult;
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];  // compress
            i = id[i];
        }

        return i;
    }
}
