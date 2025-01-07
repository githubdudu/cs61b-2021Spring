package byow.Delaunay;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Triangle {
    Point2D a;
    Point2D b;
    Point2D c;

    /**
     * Construct a triangle with three points
     * Sort the points by x-coordinate, if two points have the same x-coordinate,
     * sort them by y-coordinate
     *
     * @param a point a
     * @param b point b
     * @param c point c
     */
    public Triangle(Point2D a, Point2D b, Point2D c) {
        List<Point2D> points = new ArrayList<>();
        points.add(a);
        points.add(b);
        points.add(c);
        points.sort((p1, p2) -> {
            if (p1.getX() == p2.getX()) {
                return Double.compare(p1.getY(), p2.getY());
            }
            return Double.compare(p1.getX(), p2.getX());
        });
        this.a = points.get(0);
        this.b = points.get(1);
        this.c = points.get(2);
    }

    /**
     * Representing the edge of the triangle.
     * Inner class of Triangle.
     */
    public static class Side extends Line2D.Double {
        /**
         * Construct an edge with two points.
         * Sort the points by x-coordinate, if two points have the same x-coordinate,
         * sort them by y-coordinate.
         *
         * @param p1 point 1
         * @param p2 point 2
         */
        public Side(Point2D p1, Point2D p2) {
            super();
            if (p1.getX() < p2.getX() || p1.getX() == p2.getX() && p1.getY() < p2.getY()) {
                setLine(p1, p2);
            } else {
                setLine(p2, p1);
            }
        }

        /**
         * Check if two sides are equal.
         * They are equal if they have the same points.
         *
         * @param obj the object to compare
         * @return true if the two sides are equal
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Line2D.Double) {
                Line2D.Double that = (Line2D.Double) obj;
                return getP1().equals(that.getP1()) && getP2().equals(that.getP2())
                        || getP1().equals(that.getP2()) && getP2().equals(that.getP1());
            }
            return super.equals(obj);
        }

        /**
         * Compute the hash code of the edge.
         * Consistent with the equals' method.
         *
         * @return the hash code
         */
        @Override
        public int hashCode() {
            return Objects.hash(getP1(), getP2());
        }
    }

    /**
     * Get three sides of the triangle
     *
     * @return three sides of the triangle
     */
    public Side[] getSides() {
        return new Side[]{
                new Side(a, b),
                new Side(b, c),
                new Side(c, a)
        };
    }

    /**
     * Check if the triangle contains the point p in its circumcircle
     *
     * @param p the point
     * @return true if the triangle contains the point p in its circumcircle
     */
    public boolean circumcircleContains(Point2D p) {
        Ellipse2D circle = getCircumcircle();
        return circle.contains(p);
    }

    /**
     * Get the circumcircle of the triangle
     *
     * @return the circumcircle of the triangle
     */
    private Ellipse2D getCircumcircle(){
        double ax = a.getX();
        double ay = a.getY();
        double bx = b.getX();
        double by = b.getY();
        double cx = c.getX();
        double cy = c.getY();
        double d = 2 * (ax * (by - cy) + bx * (cy - ay) + cx * (ay - by));
        double ux = ((ax * ax + ay * ay) * (by - cy) + (bx * bx + by * by) * (cy - ay) +
                (cx * cx + cy * cy) * (ay - by)) / d;
        double uy = ((ax * ax + ay * ay) * (cx - bx) + (bx * bx + by * by) * (ax - cx) +
                (cx * cx + cy * cy) * (bx - ax)) / d;
        double r = Math.sqrt((ax - ux) * (ax - ux) + (ay - uy) * (ay - uy));
        // The circle is centered at (ux, uy) with radius r
        // The bounding box is (ux - r, uy - r, 2 * r, 2 * r)
        return new Ellipse2D.Double(ux - r, uy - r, 2 * r, 2 * r);
    }

    /**
     * Check if the triangle shares a vertex with the triangle t
     *
     * @param t the triangle
     * @return true if the triangle shares a vertex with the triangle t
     */
    public boolean sharesVertex(Triangle t) {
        return a.equals(t.a) || a.equals(t.b) || a.equals(t.c) ||
                b.equals(t.a) || b.equals(t.b) || b.equals(t.c) ||
                c.equals(t.a) || c.equals(t.b) || c.equals(t.c);
    }

    /**
     * Check if the equality of two triangles.
     * They are equal if they have the same points.
     * <p>
     * Since the points are sorted, we can compare the triangles by their corresponding points.
     *
     * @param o the object to compare
     * @return true if the two triangles are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(a, triangle.a) && Objects.equals(b,
                triangle.b) && Objects.equals(c, triangle.c);
    }

    /**
     * Since the points are sorted, we can compute the hash in this order.
     * To make sure consistent with the equals' method.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
