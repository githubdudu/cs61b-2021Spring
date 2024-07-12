package byow.DungeonMap;

import byow.Core.RandomUtils;

import java.awt.*;
import java.awt.geom.Point2D;
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

    private static final int LAMBDA = 7;

    /**
     * Returns N rectangles that are not overlapping and randomly positioned in the room / canvas.
     * The room is defined by the width and height.
     *
     * @param random     the random object
     * @param N          the number of rectangles we want to generate
     * @param roomWidth  the width of the room
     * @param roomHeight the height of the room
     * @return the array of rectangles
     */
    public static Rectangle[] randomRooms(Random random, int N, double roomWidth,
                                          double roomHeight) {
        Point2D center = new Point2D.Double(roomWidth / 2, roomHeight / 2);
        double ellipseA = GraphUtils.ELLIPSE_A_DEFAULT;
        double ellipseB = GraphUtils.ELLIPSE_B_DEFAULT;
        Rectangle[] overlappedRect = randomRectanglesInEllipse(random, N, center, ellipseA,
                ellipseB);
        separateOut(random, overlappedRect);
        return overlappedRect;
    }

    /**
     * Returns N Rectangles their centers are random positioned in the ellipse specified by center,
     * ellipseA and ellipseB.
     * Its width and height are randomly generated using Poisson distribution.
     * All the values are integers.
     *
     * @param random   the random object
     * @param N        the number of rectangles we want to generate
     * @param center   the center of the ellipse
     * @param ellipseA the major axis of the ellipse
     * @param ellipseB the minor axis of the ellipse
     * @return the array of rectangles
     */
    public static Rectangle[] randomRectanglesInEllipse(Random random, int N, Point2D center,
                                                        double ellipseA, double ellipseB) {
        Rectangle[] rectangles = new Rectangle[N];
        for (int i = 0; i < N; i++) {
            Point2D.Double centerOfRect = randomPointInEllipse(random, ellipseA, ellipseB);
            int width = RandomUtils.poisson(random, LAMBDA);
            int height = RandomUtils.poisson(random, LAMBDA);
            int x = (int) (centerOfRect.x - width / 2 + center.getX());
            int y = (int) (centerOfRect.y - height / 2 + center.getY());
            rectangles[i] = new Rectangle(x, y, width, height);
        }
        return rectangles;
    }

    /**
     * Returns a random point in the circle.
     * Values are double.
     *
     * @param random the random object
     * @param radius the radius of the circle
     * @return the random point
     */
    private static Point2D.Double randomPointInCircle(Random random, double radius) {
        return randomPointInEllipse(random, radius, radius);
    }

    /**
     * Returns a random point in the ellipse.
     * Values are double.
     *
     * @param random the random object
     * @param a      the major axis of ellipse
     * @param b      the minor axis of ellipse
     * @return the random point
     */
    private static Point2D.Double randomPointInEllipse(Random random, double a, double b) {
        // Using the polar coordinate system.
        // Angle is between 0 and 2 * PI
        double angle = RandomUtils.uniform(random, 0, 2 * Math.PI);
        // Credit: https://stackoverflow.com/a/5838055
        double u = random.nextDouble() + random.nextDouble();
        double r = u > 1 ? 2 - u : u;
        return new Point2D.Double((a * r * Math.cos(angle) / 2), (b * r * Math.sin(angle) / 2));
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
