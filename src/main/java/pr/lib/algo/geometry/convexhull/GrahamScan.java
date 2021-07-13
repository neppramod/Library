package pr.lib.algo.geometry.convexhull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Implements GrahamScan convex algorithm. O(nLogn) runtime.
 * Assumption: No 3 points on a line
 */
public class GrahamScan {
    List<Point2D> getConvexHullPoints(Point2D[] p) {
        if (p == null || p.length <= 2) {
            throw new IllegalArgumentException("null/incomplete array");
        }

        Stack<Point2D> hull = new Stack<>();

        Arrays.sort(p, Point2D.Y_ORDER);
        Arrays.sort(p, p[0].polarOrder());

        hull.push(p[0]);
        hull.push(p[1]);

        int n = p.length;

        for (int i = 2; i < n; i++) {
            Point2D top = hull.pop();

            // Discard points that would cause clockwise turn
            while (Point2D.ccw(hull.peek(), top, p[i]) <= 0) {
                top = hull.pop();
            }

            hull.push(top);
            hull.push(p[i]);
        }

        List<Point2D> list = new ArrayList<>();
        while (!hull.isEmpty()) {
            list.add(hull.pop());
        }

        return list;
    }
}
