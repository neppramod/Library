package pr.lib.ds.set.disjointset;

import org.junit.jupiter.api.Test;
import pr.lib.ds.tree.segmenttree.Merger;

import static org.junit.jupiter.api.Assertions.*;

class UnionFindTest {
    @Test
    public void testFindLargest() {
        Merger<Integer> merger = (x, y) -> (x == null ? (y == null ? null : y) : Math.max(x, y));
        UnionFind<Integer> uf = new UnionFind<>(6, merger);
        uf.union(3, 1);

        assertEquals(uf.find(1), Integer.valueOf(3));

        uf.union(1, 2);
        assertEquals(uf.find(1), Integer.valueOf(3));

        uf.union(0, 4);
        assertEquals(uf.find(0), Integer.valueOf(4));

        uf.union(0, 5);
        assertEquals(uf.find(4), Integer.valueOf(5));

        uf.union(2, 5);
        assertEquals(uf.find(1), Integer.valueOf(5));
    }
}