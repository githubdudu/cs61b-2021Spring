package byow.DungeonMap;

import byow.Core.RandomUtils;

import java.awt.*;
import java.awt.geom.Ellipse2D;
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

    public static Ellipse2D ELLIPSE;

    /**
     * Returns N rectangles that are not overlapping and randomly positioned in the room / canvas.
     * The room is defined by the width and height.
     *
     * @param random the random object
     * @param N      the number of rectangles we want to generate
     * @return the array of rectangles
     */
    public static Rectangle[] randomRooms(Random random, int N, int lambda) {
        Rectangle[] overlappedRect = new Rectangle[N];
        ELLIPSE = getCenterEllipse();
        RandomRectangle rRect = new RandomRectangle(random, ELLIPSE, lambda);
        for (int i = 0; i < N; i++) {
            overlappedRect[i] = rRect.nextRectangle();
        }
        separateOut(random, overlappedRect);
        return overlappedRect;
    }

    /**
     * Returns the ellipse that is centered at the center of the canvas.
     *
     * @return the ellipse
     */
    public static Ellipse2D.Double getCenterEllipse() {
        return new Ellipse2D.Double(GraphUtils.CENTER.x - GraphUtils.ELLIPSE_A_DEFAULT / 2,
                GraphUtils.CENTER.y - GraphUtils.ELLIPSE_B_DEFAULT / 2,
                GraphUtils.ELLIPSE_A_DEFAULT,
                GraphUtils.ELLIPSE_B_DEFAULT);
    }

    /**
     * Separate out the rectangles that are overlapping.
     * The rectangles are randomly shuffled to avoid the case that the rectangles are jammed between
     * others and are moving back and forth.
     * <p>
     * This method will change the original rectangles.
     *
     * @param random     the random object
     * @param rectangles the array of rectangles
     */
    public static void separateOut(Random random, Rectangle[] rectangles) {
        boolean overlapping = true;
        while (overlapping) {
            overlapping = false;
            RandomUtils.shuffle(random, rectangles);
            for (int i = 0; i < rectangles.length; i++) {
                for (int j = 0; j < rectangles.length; j++) {
                    if (i != j && rectangles[i].intersects(rectangles[j])) {
                        Point sep = separationVector(rectangles[i], rectangles[j]);
                        rectangles[i].setRect(rectangles[i].getX() + sep.x,
                                rectangles[i].getY() + sep.y,
                                rectangles[i].getWidth(), rectangles[i].getHeight());
                        overlapping = true;
                    }
                }
            }
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
    private static Point separationVector(Rectangle r1, Rectangle r2) {
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
}
