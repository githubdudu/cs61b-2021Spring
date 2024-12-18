package byow.DungeonMap;

import byow.Core.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class generates random rectangles in an ellipse.
 * <p>
 * This is the first step of the dungeon generation.
 * <p>
 * The ellipse is defined by the major axis (a) and the minor axis (b).
 * The ellipse is centered at the origin.
 * The center of the rectangle is randomly generated in the ellipse.
 * The width and height of the rectangle are randomly generated using Poisson distribution with
 * lambda = 7.
 * <p>
 * We could generate the half-width and half-height that used as the parameters of the method:
 * StdDraw.rectangle(double x, double y, double halfWidth, double halfHeight). But in this case,
 * the width and height will always be even. So we generate the width and height directly, and then
 * convert them to half-width and half-height.
 */
public class RandomRooms {
    private Rectangle[] rooms;
    private List<Rectangle> mainRooms = new ArrayList<>();
    private List<Rectangle> sideRooms = new ArrayList<>();
    /**
     * Generate N rectangles that are not overlapping and randomly positioned in the room / canvas.
     * The room is defined by the width and height.
     *
     * @param random      the random object
     * @param centerScope the ellipse that the center of the generated rectangles should be in
     * @param settings    the settings of the dungeon
     */
    public RandomRooms(Random random, Ellipse2D centerScope, Settings settings) {
        int N = settings.CELL_COUNT;
        int lambda = settings.LAMBDA;
        rooms = new Rectangle[N];
        RandomRectangle rRect = new RandomRectangle(random, centerScope, lambda);
        for (int i = 0; i < N; i++) {
            rooms[i] = rRect.nextRectangle();
        }
        draw();
        separateOut(random);
        rooms = removeOutliers(rooms);

        selectMainRooms(settings);
        draw();
    }

    public Rectangle[] getAllRooms() {
        return rooms;
    }

    public List<Rectangle> getMainRooms() {
        return mainRooms;
    }

    public List<Rectangle> getSideRooms() {
        return sideRooms;
    }

    public void draw() {
        for (Rectangle r : rooms) {
            GraphUtils.drawRect(r);
        }
        StdDraw.setPenRadius(0.006);
        for (Rectangle r : mainRooms) {
            StdDraw.setPenColor(Color.RED);
            GraphUtils.drawRect(r);
        }
        StdDraw.setPenRadius();
    }

    /**
     * Separate out the rectangles that are overlapping.
     * The rectangles are randomly shuffled to avoid the case that the rectangles are jammed between
     * others and are moving back and forth.
     * <p>
     * This method will change the original rectangles.
     *
     * @param random     the random object
     */
    public void separateOut(Random random) {
        boolean overlapping = true;
        while (overlapping) {
            overlapping = false;
            RandomUtils.shuffle(random, rooms);
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms.length; j++) {
                    if (i != j && rooms[i].intersects(rooms[j])) {
                        Point sep = separationVector(rooms[i], rooms[j]);
                        rooms[i].setRect(rooms[i].getX() + sep.x,
                                rooms[i].getY() + sep.y,
                                rooms[i].getWidth(), rooms[i].getHeight());
                        overlapping = true;
                    }
                }
            }

            StdDraw.clear();
            draw();
        }
    }

    /**
     * Returns a vector that separates the two rectangles.
     * The vector.x is either -1, 0, or 1.
     * The vector.y is either -1, 0, or 1.
     *
     * @param r1 the first rectangle
     * @param r2 the second rectangle
     * @return the separation vector
     */
    private Point separationVector(Rectangle r1, Rectangle r2) {
        Point vector = new Point();
        Point c1 = new Point((int) r1.getCenterX(), (int) r1.getCenterY());
        Point c2 = new Point((int) r2.getCenterX(), (int) r2.getCenterY());
        double distance = c1.distance(c2);
        if (distance == 0) {
            return new Point(1, 1);
        }
        vector.x = (int) Math.round((c1.x - c2.x) / distance);
        vector.y = (int) Math.round((c1.y - c2.y) / distance);

        return vector;
    }

    private Rectangle[] removeOutliers(Rectangle[] rectangles) {
        return rectangles;
    }



    /**
     * Select main rooms by their width and height which are greater than the average width and height
     * multiplied by the threshold.
     * <p>
     * Select side rooms by their width and height which are less than the average width and height
     * multiplied by the threshold.
     *
     */
    private void selectMainRooms(Settings settings) {
        // average of the heights and width of all rooms
        double avgWidth = getWidthAvg();
        double avgHeight = getHeightAvg();
        double widthThreshold = avgWidth * settings.SIZE_THRESHOLD;
        double heightThreshold = avgHeight * settings.SIZE_THRESHOLD;
        for (Rectangle room : rooms) {
            if (room.width > widthThreshold && room.height > heightThreshold) {
                mainRooms.add(room);
            } else {
                sideRooms.add(room);
            }
        }
    }

    private double getWidthAvg() {
        return getWidthSum() / rooms.length;
    }

    private double getHeightAvg() {
        return getHeightSum() / rooms.length;
    }

    private double getWidthSum() {
        return Arrays.stream(rooms).mapToDouble(r -> r.width).sum();
    }

    private double getHeightSum() {
        return Arrays.stream(rooms).mapToDouble(r -> r.height).sum();
    }

}
