package byow.DungeonMap;

import edu.princeton.cs.algs4.PrimMST;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.*;

public class DungeonMap {
    private final Settings settings;
    private final Random random;
    private Rectangle[] rectangles;
    private List<Rectangle> mainRooms;
    private EuclideanEdgeWeightedGraph graph;
    private EuclideanPrimMST mst;

    public DungeonMap(Settings settings) {
        this.settings = settings;
        this.random = new Random(123L);
        this.rectangles = new RandomRooms(random, getCenterEllipse(), settings.CELL_COUNT,
                settings.LAMBDA).getRooms();
        this.mainRooms = selectMainRooms();
        List<Point2D> centers = getCenters(mainRooms);
        Set<Triangle> triangles = new Delaunay().bowyerWatson(centers);
        Set<Line2D> sides = this.getSides(triangles);
        this.graph = new EuclideanEdgeWeightedGraph(sides, sides.size());
        this.mst = new EuclideanPrimMST(this.graph);
        this.graph.show();
        this.mst.show();

        System.out.println("There are " + triangles.size() + " triangles.");
    }

    public void createMap() {

    }

    /**
     * Returns the ellipse that is centered at the center of the canvas.
     *
     * @return the ellipse
     */
    public Ellipse2D.Double getCenterEllipse() {
        return new Ellipse2D.Double(settings.CENTER.x - settings.ELLIPSE_A_DEFAULT / 2,
                settings.CENTER.y - settings.ELLIPSE_B_DEFAULT / 2, settings.ELLIPSE_A_DEFAULT,
                settings.ELLIPSE_B_DEFAULT);
    }

    public int[][] getMSTPath(List<Rectangle> rooms) {
        List<Point2D> centers = getCenters(rooms);
        Set<Triangle> triangles = new Delaunay().bowyerWatson(centers);
        return null;
    }
    /**
     * Select rooms that their width and height are greater than the average width and height
     * multiplied by the threshold.
     *
     * @return the list of selected rooms
     */
    private List<Rectangle> selectMainRooms() {
        // average of the heights and width of all rooms
        double avgHeight = getHeightAvg();
        double avgWidth = getWidthAvg();
        return Arrays.stream(rectangles).filter(
                        r -> r.width > avgWidth * settings.SIZE_THRESHOLD && r.height > avgHeight * settings.SIZE_THRESHOLD)
                .toList();
    }

    private double getWidthAvg() {
        return getWidthSum() / rectangles.length;
    }

    private double getHeightAvg() {
        return getHeightSum() / rectangles.length;
    }

    private double getWidthSum() {
        return Arrays.stream(rectangles).mapToDouble(r -> r.width).sum();
    }

    private double getHeightSum() {
        return Arrays.stream(rectangles).mapToDouble(r -> r.height).sum();
    }

    /**
     * Get the list of centers of a list of rooms.
     *
     * @param rooms a list of rooms
     * @return the list of centers
     */
    public List<Point2D> getCenters(List<Rectangle> rooms) {
        return rooms.stream().map(
                r -> (Point2D) new Point2D.Double(r.getCenterX(), r.getCenterY())).toList();
    }


    private Set<Line2D> getSides(Iterable<Triangle> triangles) {
        Set<Line2D> sides = new HashSet<>();
        triangles.forEach(t -> {
            sides.add(new Triangle.Side(t.a, t.b));
            sides.add(new Triangle.Side(t.b, t.c));
            sides.add(new Triangle.Side(t.c, t.a));
        });
        return sides;
    }

    public static void main(String[] args) {
        GraphUtils.initCanvas(GraphUtils.SETTINGS1);
        GraphUtils.SETTINGS1.SIZE_THRESHOLD = 1.0;
        DungeonMap dungeonMap = new DungeonMap(GraphUtils.SETTINGS1);

        StdDraw.setPenColor(StdDraw.RED);
        for (Rectangle r : dungeonMap.mainRooms) {
            GraphUtils.drawRect(r);
        }
    }
}
