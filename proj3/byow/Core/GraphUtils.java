package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GraphUtils {
    final static int WIDTH = 100;
    final static int HEIGHT = 60;
    final static int RADIUS = 30;
    final static int GRID_SIZE = 16;
    final static Point CENTER = new Point(WIDTH / 2, HEIGHT / 2);

    public static Point2D translate(Point2D p, Point2D center) {
        return new Point2D.Double(p.getX() + center.getX(), p.getY() + center.getY());
    }

    public static Point round(Point2D p2) {
        return new Point((int) Math.round(p2.getX()), (int) Math.round(p2.getY()));
    }

    public static void DrawRect(Rectangle2D r) {
        StdDraw.rectangle(r.getCenterX(), r.getCenterY(), r.getWidth() / 2, r.getHeight() / 2);
    }

    public static void init() {
        StdDraw.setCanvasSize(WIDTH * GRID_SIZE, HEIGHT * GRID_SIZE);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
    }
}
