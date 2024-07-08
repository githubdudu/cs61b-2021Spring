package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import static java.lang.Thread.sleep;

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
public class RandomRectangleInEllipse {

    private static final int LAMBDA = 7;
    public final double ELLIPSE_A_DEFAULT = 70;
    public final double ELLIPSE_B_DEFAULT = 10;
    public final double ellipseA;
    public final double ellipseB;
    private final Random RANDOM;

    public RandomRectangleInEllipse(long Seed) {
        this.ellipseA = ELLIPSE_A_DEFAULT;
        this.ellipseB = ELLIPSE_B_DEFAULT;
        this.RANDOM = new Random(Seed);
    }

    public RandomRectangleInEllipse(double ellipseA, double ellipseB, long Seed) {
        this.ellipseA = ellipseA;
        this.ellipseB = ellipseB;
        this.RANDOM = new Random(Seed);
    }

    public Rectangle2D.Double nextRectangle2D() {
        return randomRectangle2D();
    }

    public Rectangle nextRectangle() {
        return randomRectangle();
    }

    private Rectangle randomRectangle() {
        Point2D.Double p1 = randomPointInEllipse(ellipseA, ellipseB);
        int width = randomWidth();
        int height = randomHeight();
        return new Rectangle((int) (p1.x - width / 2), (int) (p1.y - height / 2), width, height);
    }

    private Rectangle2D.Double randomRectangle2D() {
        Point2D.Double p1 = randomPointInEllipse(ellipseA, ellipseB);
        double width = randomWidth();
        double height = randomHeight();
        return new Rectangle2D.Double(p1.x - width / 2, p1.y - height / 2, width, height);
    }

    /**
     * Returns a random width for the rectangle using Poisson distribution.
     *
     * @return
     */
    private int randomWidth() {
        return RandomUtils.poisson(RANDOM, LAMBDA);
    }

    /**
     * Returns a random height for the rectangle using Poisson distribution.
     *
     * @return
     */
    private int randomHeight() {
        return RandomUtils.poisson(RANDOM, LAMBDA);
    }

    /**
     * Returns a random point in the circle
     *
     * @param radius
     * @return
     */
    private Point2D.Double randomPointInCircle(double radius) {
        return randomPointInEllipse(radius, radius);
    }

    /**
     * Returns a random point in the ellipse.
     *
     * @param a
     * @param b
     * @return
     */
    private Point2D.Double randomPointInEllipse(double a, double b) {
        // Using the polar coordinate system.
        // Angle is between 0 and 2 * PI
        double angle = RandomUtils.uniform(RANDOM, 0, 2 * Math.PI);
        // Credit: https://stackoverflow.com/a/5838055
        double u = RANDOM.nextDouble() + RANDOM.nextDouble();
        double r = u > 1 ? 2 - u : u;
        return new Point2D.Double((a * r * Math.cos(angle) / 2), (b * r * Math.sin(angle) / 2));
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please input args like \"circleConsecutive 100\"");
            System.exit(0);
        }
        RandomRectangleInEllipse randomRectangle = new RandomRectangleInEllipse(123L);

        final int WIDTH = 100;
        final int HEIGHT = 50;
        final int RADIUS = 30;
        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);

        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);

        final int N = Integer.parseInt(args[1]);
        final Point CENTER = new Point(WIDTH / 2, HEIGHT / 2);
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA};

        switch (args[0]) {
            case "circleConsecutive":
                for (int i = 0; i < N; i++) {
                    Point2D p1 = randomRectangle.randomPointInCircle(RADIUS);
                    Point2D p = GraphUtils.translate(p1, CENTER);
                    StdDraw.point(p.getX(), p.getY());
                }
                break;
            case "circleDiscrete":
                for (int i = 0; i < N; i++) {
                    Point2D p1 = randomRectangle.randomPointInCircle(RADIUS);
                    Point2D p2 = GraphUtils.translate(p1, CENTER);
                    Point2D p = GraphUtils.round(p2);
                    StdDraw.point(p.getX(), p.getY());
                }
                break;
            case "ellipseConsecutive":
                for (int i = 0; i < N; i++) {
                    Point2D p1 = randomRectangle.randomPointInEllipse(RADIUS, RADIUS / 2);
                    Point2D p = GraphUtils.translate(p1, CENTER);
                    StdDraw.point(p.getX(), p.getY());
                }
                break;
            case "ellipseDiscrete":
                for (int i = 0; i < N; i++) {
                    Point2D p1 = randomRectangle.randomPointInEllipse(RADIUS, RADIUS / 2);
                    Point2D p2 = GraphUtils.translate(p1, CENTER);
                    Point2D p = GraphUtils.round(p2);
                    StdDraw.point(p.getX(), p.getY());
                }
                break;
            case "rectangle":
                StdDraw.ellipse(CENTER.x, CENTER.y, randomRectangle.ellipseA / 2,
                        randomRectangle.ellipseB / 2);
                for (int i = 0; i < N; i++) {
                    Rectangle2D r = randomRectangle.nextRectangle2D();
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StdDraw.setPenColor(colors[i % colors.length]);
                    System.out.println(r);
                    StdDraw.rectangle(r.getCenterX() + CENTER.x, r.getCenterY() + CENTER.y,
                            r.getWidth() / 2, r.getHeight() / 2);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid shape");
        }

    }
}
