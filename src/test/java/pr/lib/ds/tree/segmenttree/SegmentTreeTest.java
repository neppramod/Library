package pr.lib.ds.tree.segmenttree;

import junit.framework.TestCase;

public class SegmentTreeTest extends TestCase {

    public void testIsDone() {
        SegmentTree segmentTree = new SegmentTree();
        assertTrue(segmentTree.isDone());
    }
}