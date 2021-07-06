package pr.lib.ds.tree.segmenttree;


import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class SegmentTreeRecTest {

    @Test
    public void testbuildTree() {
        BinaryOperator<Integer> merger = (x, y) -> (x == null ? (y == null ? null : y) : x + y);
        SegmentTreeRec<Integer> segmentTree = new SegmentTreeRec<>(merger);
        Integer[] arr = {2, 3, 5, 6, 7};
        segmentTree.buildTree(arr);
        segmentTree.printTree();
    }

    @Test
    public void testQueryTree() {
        BinaryOperator<Integer> merger = (x, y) -> (x == null ? (y == null ? null : y) : x + y);
        SegmentTreeRec<Integer> segmentTree = new SegmentTreeRec<>(merger);
        Integer[] arr = {2, 3, 5, 6, 7};
        segmentTree.buildTree(arr);

        assertEquals((int)segmentTree.querySegmentTree(1, 3), 14);
        assertEquals((int)segmentTree.querySegmentTree(0, 4), 23);
        assertEquals((int)segmentTree.querySegmentTree(2, 2), 5);
        assertEquals((int)segmentTree.querySegmentTree(2, 3), 11);
        assertEquals((int)segmentTree.querySegmentTree(0, 0), 2);

        Exception leftBoundaryException = assertThrows(IllegalArgumentException.class, () ->
                segmentTree.querySegmentTree(-1, 3));
        assertEquals("left and right outside boundary", leftBoundaryException.getMessage());
    }

    @Test
    public void testUpdateTree() {
        BinaryOperator<Integer> merger = (x, y) -> (x == null ? (y == null ? null : y) : x + y);
        SegmentTreeRec<Integer> segmentTree = new SegmentTreeRec<>(merger);
        Integer[] arr = {2, 3, 5, 6, 7};
        segmentTree.buildTree(arr);

        assertEquals((int)segmentTree.querySegmentTree(0, 4), 23);

        // here update will add value
        segmentTree.updateSegmentTree(1, 3, 3);  // {2, 6, 8, 9, 7}
        assertEquals((int)segmentTree.querySegmentTree(1, 3), 23);

        segmentTree.printTree();

        assertEquals((int)segmentTree.querySegmentTree(0, 4), 32);
        assertEquals((int)segmentTree.querySegmentTree(0, 3), 25);
    }
}