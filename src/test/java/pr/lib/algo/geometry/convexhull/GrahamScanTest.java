package pr.lib.algo.geometry.convexhull;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GrahamScanTest {

    @Test
    public void testGrahamScan() {
        Point2D[] points = {c(0, 3), c(1, 1), c(2, 2), c(4, 4), c(0, 0), c(1,2), c(3, 1), c(3, 3)};
        GrahamScan sol = new GrahamScan();
        List<Point2D> result = sol.getConvexHullPoints(points);

        Set<Point2D> actualSet = new HashSet<>(result);
        Set<Point2D> expectedSet = new HashSet<>(Arrays.asList(c(0, 3), c(4, 4), c(3, 1), c(0, 0)));

        assertEquals(actualSet, expectedSet);

    }

    static Point2D c(int x, int y) {
        return new Point2D(x, y);
    }
}