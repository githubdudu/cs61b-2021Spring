package byow.DungeonMap;

import byow.Core.RandomUtils;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class RandomRectangle {
    private final Random random;
    private final double lambda;
    private final Ellipse2D ellipse;

    public RandomRectangle(Random random, Ellipse2D ellipse, double lambda) {
        this.random = random;
        this.ellipse = ellipse;
        this.lambda = lambda;
    }

    /**
     * Returns a Rectangle that its center is random positioned in the given ellipse.
     * Its width and height are randomly generated using Poisson distribution.
     * All the values are integers.
     */
    public Rectangle nextRectangle() {
        Point2D.Double center = randomPointInEllipse();
        int width = randomWidth();
        int height = randomHeight();
        return new Rectangle((int) (center.getX() - width / 2), (int) (center.getY() - height / 2),
                width, height);
    }

    /**
     * Returns a random point in the given ellipse.
     * Values are double.
     *
     * @return the random point
     */
    private Point2D.Double randomPointInEllipse() {
        while (true) {
            Point2D.Double center = randomCenter();
            if (ellipse.contains(center)) {
                return center;
            }
        }
    }


    /**
     * Returns a random point in the rectangle that contains the ellipse.
     *
     * @return the random point
     */
    private Point2D.Double randomCenter() {
        double a = ellipse.getWidth();
        double b = ellipse.getHeight();
        return new Point2D.Double(
                RandomUtils.uniform(random, ellipse.getX(), a + ellipse.getX()),
                RandomUtils.uniform(random, ellipse.getY(), b + ellipse.getY()));
    }

    /**
     * Returns a random width using Poisson distribution.
     *
     * @return the random width
     */
    private int randomWidth() {
        return RandomUtils.poisson(random, lambda);
    }

    /**
     * Returns a random height using Poisson distribution.
     *
     * @return the random height
     */
    private int randomHeight() {
        return RandomUtils.poisson(random, lambda);
    }
}
