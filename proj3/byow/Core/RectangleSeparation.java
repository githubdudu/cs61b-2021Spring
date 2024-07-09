package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class RectangleSeparation {

    private static final Random RANDOM = new Random(123L);

    public static void separateOut(Rectangle[] rectangles) {
        boolean overlapping = true;
        while(overlapping) {
            overlapping = false;
            RandomUtils.shuffle(RANDOM, rectangles);
            for (int i = 0; i < rectangles.length; i++) {
                for (int j = 0; j < rectangles.length; j++) {
                    if (i != j && rectangles[i].intersects(rectangles[j])) {
                        Point sep = separationVector(rectangles, i, j);
                        rectangles[i].setRect(rectangles[i].getX() + sep.x,
                                rectangles[i].getY() + sep.y,
                                rectangles[i].getWidth(), rectangles[i].getHeight());
                        overlapping = true;
                    }
                }
            }
        }
    }

    private static Point separationVector(Rectangle[] rectangles, int i, int j) {
        Point sep = new Point();
        Point c1 = new Point((int) rectangles[i].getCenterX(), (int) rectangles[i].getCenterY());
        Point c2 = new Point((int) rectangles[j].getCenterX(), (int) rectangles[j].getCenterY());
        double distance = c1.distance(c2);
        if(distance == 0) {
            return new Point(1, 1);
            }
        sep.x = (int) Math.round((c1.x - c2.x) / distance);
        sep.y = (int) Math.round((c1.y - c2.y) / distance);

        return sep;
    }


    public static void main(String[] args) {
        RandomRectangleInEllipse rre = new RandomRectangleInEllipse(123L);
        Point CENTER = GraphUtils.CENTER;

        Rectangle[] rectangles = new Rectangle[60];
        GraphUtils.init();

        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i] = rre.nextRectangle();
            rectangles[i].setRect(rectangles[i].getX() + CENTER.x, rectangles[i].getY() + CENTER.y,
                    rectangles[i].getWidth(), rectangles[i].getHeight());
        }

        StdDraw.clear();
        RectangleSeparation.separateOut(rectangles);

        for (int i = 0; i < rectangles.length; i++) {
            GraphUtils.DrawRect(rectangles[i]);
            StdDraw.text(rectangles[i].getX(), rectangles[i].getY(), i + "");
        }
    }
}
