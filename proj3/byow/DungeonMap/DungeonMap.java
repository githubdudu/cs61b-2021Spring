package byow.DungeonMap;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DungeonMap {

    private List<Rectangle> rooms;
    private List<Point2D> centers;
    private int[][] graph;
    private Set<Triangle> triangles;
    public void createMap() {
        int CELL_COUNT = 60;
        double SIZE_THRESHOLD = 1.05;
        Rectangle[] rectangles = getRandomRectanglesInCenter(CELL_COUNT, GraphUtils.CENTER);
        RectangleSeparation.separateOut(rectangles);
        rooms = selectMainRooms(rectangles, SIZE_THRESHOLD);
        centers = getCenters(rooms);
        triangles = Delaunay.bowyerWatson(centers);
        System.out.println(triangles.size());
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
        double width = 0;
        double height = 0;
        List<Rectangle> list = new ArrayList<>();
        for (Rectangle r : rectangles) {
            width += r.width;
            height += r.height;
        }
        width = threshold * width / rectangles.length;
        height = threshold * height / rectangles.length;

        for (Rectangle r : rectangles) {
            if (r.width > width && r.height > height ) {
                list.add(r);
            }
        }
        return list;
    }

    /**
     * Get random rectangles that their center is distributed in the ellipse around the center.
     *
     * @param count the count of rectangles
     * @param center the center of the ellipse
     * @return the rectangles array
     */
    public static Rectangle[] getRandomRectanglesInCenter(int count, Point center) {
        final double ELLIPSE_A_DEFAULT = 70;
        final double ELLIPSE_B_DEFAULT = 10;
        RandomRectangleInEllipse rre = new RandomRectangleInEllipse(ELLIPSE_A_DEFAULT,
                ELLIPSE_B_DEFAULT, 123L);
        Rectangle[] rectangles = new Rectangle[count];
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i] = rre.nextRectangle();
            rectangles[i].setRect(rectangles[i].getX() + center.x, rectangles[i].getY() + center.y,
                    rectangles[i].getWidth(), rectangles[i].getHeight());
        }
        return rectangles;
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
        for (Rectangle r : dungeonMap.rooms) {
            GraphUtils.DrawRect(r);
        }

        StdDraw.setPenColor(StdDraw.GREEN);
        for (Triangle triangle : dungeonMap.triangles) {
            for (Line2D edge : triangle.edges()) {
                StdDraw.line(edge.getX1(), edge.getY1(), edge.getX2(), edge.getY2());
            }
        }

    }
}
