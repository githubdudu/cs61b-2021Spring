package byow.DungeonMap;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DungeonMap {

    private List<Rectangle> mainRooms;
    private int[][] graph;
    public void createMap() {

        Random random = new Random(123L);
        Rectangle[] rectangles = RandomRooms.randomRooms(random, GraphUtils.CELL_COUNT, GraphUtils.WIDTH,
                GraphUtils.HEIGHT);
        mainRooms = selectMainRooms(rectangles, GraphUtils.SIZE_THRESHOLD);

        List<Point2D> centers = getCenters(mainRooms);
        Set<Triangle> triangles = Delaunay.bowyerWatson(centers);
        System.out.println(triangles.size());
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
     * @param rectangles the rectangles
     * @param threshold the threshold
     * @return the list of selected rooms
     */
    private static List<Rectangle> selectMainRooms(Rectangle[] rectangles, double threshold) {
        double avgWidth = 0;
        double avgHeight = 0;
        List<Rectangle> list = new ArrayList<>();
        for (Rectangle r : rectangles) {
            avgWidth += r.width;
            avgHeight += r.height;
        }
        avgWidth = threshold * avgWidth / rectangles.length;
        avgHeight = threshold * avgHeight / rectangles.length;

        for (Rectangle r : rectangles) {
            if (r.width > avgWidth && r.height > avgHeight ) {
                list.add(r);
            }
        }
        return list;
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
        DungeonMap dungeonMap = new DungeonMap();
        dungeonMap.createMap();

        GraphUtils.init();
        StdDraw.setPenColor(StdDraw.RED);
        for (Rectangle r : dungeonMap.mainRooms) {
            GraphUtils.DrawRect(r);
        }

        Set<Triangle> triangles = Delaunay.bowyerWatson(getCenters(dungeonMap.mainRooms));
        StdDraw.setPenColor(StdDraw.GREEN);
        for (Triangle triangle : triangles) {
            for (Line2D edge : triangle.edges()) {
                StdDraw.line(edge.getX1(), edge.getY1(), edge.getX2(), edge.getY2());
            }
        }
    }
}
