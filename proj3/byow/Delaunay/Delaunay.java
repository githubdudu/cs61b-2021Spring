package byow.Delaunay;

import byow.DungeonMap.GraphUtils;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class Delaunay {
    private final Set<Triangle> delaunayTriangles;
    public Delaunay(List<Point2D> pointList) {
        delaunayTriangles = bowyerWatson(pointList);
    }

    /**
     * Bowyer-Watson algorithm
     * <p>
     * In computational geometry, the Bowyer–Watson algorithm is a method for computing the
     * Delaunay triangulation of a finite set of points in any number of dimensions.
     *
     * @param pointList pointList is a list of coordinates defining the points to be triangulated
     * @return a set of triangles
     */
    public Set<Triangle> bowyerWatson(List<Point2D> pointList) {
        Set<Triangle> triangulation = new HashSet<>();
        // Super triangle must be large enough to completely contain all the points in pointList
        Triangle superTriangle = getSuperTriangle(GraphUtils.SETTINGS1.GRID_SIZE);
        triangulation.add(superTriangle);

        for (Point2D p : pointList) { // add all the points one at a time to the triangulation
            List<Triangle> badTriangles = new ArrayList<>();
            for (Triangle t : triangulation) { // first find all the triangles that are no longer
                // valid due to the insertion
                if (t.circumcircleContains(p)) {
                    badTriangles.add(t);
                }
            }
            Set<Triangle.Side> polygon = new HashSet<>();
            for (Triangle t : badTriangles) { // find the boundary of the polygonal hole
                for (Triangle.Side v : t.getSides()) {
                    if (polygon.contains(v)) {
                        polygon.remove(v);
                    } else {
                        polygon.add(v);
                    }
                }
            }
            for (Triangle t : badTriangles) { // Remove bad triangles from the data structure
                triangulation.remove(t);
            }
            for (Triangle.Side e : polygon) { // re-triangulate the polygonal hole
                triangulation.add(new Triangle(e.getP1(), e.getP2(), p));
            }
        }
        // Remove triangles that shared getSides with "super triangle"
        triangulation.removeIf(t -> t.sharesVertex(superTriangle));

        return triangulation;
    }

    /**
     * Convert a list of triangles to a set of sides.
     *
     * @return the set of sides
     */
    public Set<Line2D> getSides() {
        Set<Line2D> sides = new HashSet<>();
        for (Triangle t : delaunayTriangles) {
            sides.addAll(Arrays.asList(t.getSides()));
        }
        return sides;
    }

    /**
     * Get the super triangle that used for the Bowyer-Watson algorithm.
     *
     * @param grid the size of the grid of canvas
     * @return the super triangle
     */
    private Triangle getSuperTriangle(int grid) {
        // if grid == 16, M = 1000
        final int M = 16000 / grid;
        return new Triangle(new Point(-M, -M), new Point(2 * M, -M), new Point(M, 2 * M));
    }
}
