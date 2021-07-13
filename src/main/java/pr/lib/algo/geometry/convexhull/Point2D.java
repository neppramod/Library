package pr.lib.algo.geometry.convexhull;

import java.util.Comparator;
import java.util.Objects;

public final class Point2D implements Comparable<Point2D>{
    private final double x;
    private final double y;

    // Compare by y coordinates
    public static final Comparator<Point2D> Y_ORDER = new YOrder();

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int compareTo(Point2D p) {
        return y < p.y ? -1 : y > p.y ? +1 : 0;
    }

    public Comparator<Point2D> polarOrder() {
        return new PolarOrder();
    }

    private class PolarOrder implements Comparator<Point2D> {

        @Override
        /**
         * Compare polar angle between this point, p1 and p2
         *
         * if p1 is above p and p2 is below p, then p1 makes smaller polar angle
         * if p1 is below p and p2 is above p, then p1 makes larger polar angle
         * Otherwise ccw(p, p1, p2) identifies which of p1 or p2 makes larger angle
         */
        public int compare(Point2D p1, Point2D p2) {
            double dx1 = p1.x - x;
            double dy1 = p1.y - y;
            double dx2 = p2.x - x;
            double dy2 = p2.y - y;

            if (dy1 >= 0 && dy2 < 0) {  // p1 above, p2 below
                return -1;
            } else if (dy1 < 0 && dy2 >= 0) {  // p1 below, p2 above
                return +1;
            }  else if (dy1 == 0 && dy2 == 0) {  // if collinear and horizontal
                if (dx1 >= 0 && dx2 < 0) {    // p1 on the right, p2 on left
                    return -1;
                } else if (dx1 < 0 && dx2 >= 0) {  // p1 on left and p2 on right
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return -ccw(Point2D.this, p1, p2);   // both above or below
            }
        }
    }

    private static class YOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D p1, Point2D p2) {
            return p1.y < p2.y ? -1 : p1.y > p2.y ? 1 : 0;
        }
    }


    /**
     * Check counter clockwise angle
     * Use (b - a) * (c - a)
     * @return -1 for clockwise, 1 for counter clockwise and 0 for collinear
     */
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);

        return area < 0 ? -1 : area > 0 ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Double.compare(point2D.x, x) == 0 &&
                Double.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}