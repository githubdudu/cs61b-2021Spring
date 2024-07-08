package byow.Core;

import java.awt.*;
import java.awt.geom.Point2D;

public class GraphUtils {
    public static Point2D translate(Point2D p, Point2D center) {
        return new Point2D.Double(p.getX() + center.getX(), p.getY() + center.getY());
    }

    public static Point round(Point2D p2) {
        return new Point((int) Math.round(p2.getX()), (int) Math.round(p2.getY()));
    }
}
