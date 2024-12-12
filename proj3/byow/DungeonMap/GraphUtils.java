package byow.DungeonMap;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GraphUtils {
    final public static Settings SETTINGS1 = new Settings(100, 60, 16, 70, 10, 60, 1.05, 7);
    final public static Settings SETTINGS2 = new Settings(100, 60, 8, 70, 10, 60, 1.00, 7);
    /**
     * 1920 X 1080
     */
    final public static Settings SETTINGS3 = new Settings(240, 135, 8, 70, 10, 60, 1.05, 7);
    /**
     * 1920 X 1080
     */
    final public static Settings SETTINGS4 = new Settings(120, 67, 16, 70, 10, 60, 1.05, 7);

    public static Point2D translate(Point2D p, Point2D center) {
        return new Point2D.Double(p.getX() + center.getX(), p.getY() + center.getY());
    }

    public static Point round(Point2D p2) {
        return new Point((int) Math.round(p2.getX()), (int) Math.round(p2.getY()));
    }

    public static void drawRect(Rectangle2D r) {
        StdDraw.rectangle(r.getCenterX(), r.getCenterY(), r.getWidth() / 2, r.getHeight() / 2);
    }

    public static void drawRect(Rectangle2D r, String subtext) {
        drawRect(r);
        StdDraw.text(r.getX(), r.getY(), subtext);
    }

    public static void drawEllipse(Ellipse2D e) {
        StdDraw.ellipse(e.getCenterX(), e.getCenterY(), e.getWidth() / 2, e.getHeight() / 2);
    }

    public static void drawEllipse(Ellipse2D e, String subtext) {
        drawEllipse(e);
        StdDraw.text(e.getX(), e.getY(), subtext);
    }

    public static void initCanvas(Settings settings) {
        StdDraw.setCanvasSize(settings.WIDTH * settings.GRID_SIZE,
                settings.HEIGHT * settings.GRID_SIZE);
        StdDraw.setXscale(0, settings.WIDTH);
        StdDraw.setYscale(0, settings.HEIGHT);
    }
}
