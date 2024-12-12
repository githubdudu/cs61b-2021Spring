package byow.DungeonMap;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Delaunay {

    /**
     * Bowyer-Watson algorithm
     * <p>
     * In computational geometry, the Bowyer–Watson algorithm is a method for computing the
     * Delaunay triangulation of a finite set of points in any number of dimensions.
     *
     * @param pointList pointList is a list of coordinates defining the points to be triangulated
     * @return
     */
    public static Set<Triangle> bowyerWatson(List<Point2D> pointList) {
        Set<Triangle> triangulation = new HashSet<>();
        // Super triangle must be large enough to completely contain all the points in pointList
        Triangle superTriangle = new Triangle(new Point(-GraphUtils.SETTINGS1.HEIGHT, 0),
                new Point(GraphUtils.SETTINGS1.WIDTH / 2,
                        GraphUtils.SETTINGS1.HEIGHT + GraphUtils.SETTINGS1.WIDTH / 2),
                new Point(GraphUtils.SETTINGS1.WIDTH + GraphUtils.SETTINGS1.HEIGHT, 0));
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

}
