package byow.DungeonMap;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.*;

public class DungeonMap {
    private final Settings settings;
    private Rectangle[] rectangles;
    private List<Rectangle> mainRooms;
    private int[][] graph;

    public DungeonMap(Settings settings) {
        this.settings = settings;
    }

    public void createMap() {

        Random random = new Random(123L);
        rectangles = new RandomRooms(random, getCenterEllipse(), settings.CELL_COUNT,
                settings.LAMBDA).getRooms();
        mainRooms = selectMainRooms();

        List<Point2D> centers = getCenters(mainRooms);
        Set<Triangle> triangles = Delaunay.bowyerWatson(centers);
        System.out.println(triangles.size());
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
        Set<Triangle> triangles = Delaunay.bowyerWatson(centers);
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
    public static List<Point2D> getCenters(List<Rectangle> rooms) {
        List<Point2D> centers = new ArrayList<>();
        for (Rectangle r : rooms) {
            centers.add(new Point2D.Double(r.getCenterX(), r.getCenterY()));
        }
        return centers;
    }

    public static void main(String[] args) {
        DungeonMap dungeonMap = new DungeonMap(GraphUtils.SETTINGS1);
        dungeonMap.createMap();

        GraphUtils.initCanvas(GraphUtils.SETTINGS1);
        StdDraw.setPenColor(StdDraw.RED);
        for (Rectangle r : dungeonMap.mainRooms) {
            GraphUtils.drawRect(r);
        }

        Set<Triangle> triangles = Delaunay.bowyerWatson(getCenters(dungeonMap.mainRooms));
        StdDraw.setPenColor(StdDraw.GREEN);
        for (Triangle triangle : triangles) {
            for (Line2D edge : triangle.getSides()) {
                StdDraw.line(edge.getX1(), edge.getY1(), edge.getX2(), edge.getY2());
            }
        }
    }
}
