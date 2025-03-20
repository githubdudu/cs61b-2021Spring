package byow.DungeonMap.Test;

import byow.DungeonMap.Triangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

public class GeoTest {
    @Before
    public void setUp() {
    }

    @Test
    public void testTriangleSides() {
        Point2D.Double p1 = new Point2D.Double(0, 0);
        Point2D.Double p2 = new Point2D.Double(1, 0);
        Point2D.Double p3 = new Point2D.Double(0, 1);
        Triangle t = new Triangle(p1, p2, p3);
        Assert.assertEquals(t.getSides().length, 3);
        Assert.assertEquals(t.getSides()[0], new Triangle.Side(p1, p3));
        Assert.assertEquals(t.getSides()[1], new Triangle.Side(p2, p3));
        Assert.assertEquals(t.getSides()[2], new Triangle.Side(p1, p2));
    }

    @Test
    public void testTriangleEquals() {
        Point2D.Double p1 = new Point2D.Double(0, 0);
        Point2D.Double p2 = new Point2D.Double(1, 0);
        Point2D.Double p3 = new Point2D.Double(0, 1);
        Triangle t1 = new Triangle(p1, p2, p3);
        Triangle t2 = new Triangle(p1, p2, p3);
        Assert.assertEquals(t1, t2);
        Assert.assertNotEquals(t1, null);
        Assert.assertNotEquals(t1, new Object());
        Assert.assertNotEquals(t1, new Triangle(p1, p2, new Point2D.Double(1, 1)));
    }

    @Test
    public void testTriangleCircumcircle() {
        Point2D.Double p1 = new Point2D.Double(0 - 0.1, 0 - 0.1);
        Point2D.Double p2 = new Point2D.Double(3 + 0.1, 0 - 0.1);
        Point2D.Double p3 = new Point2D.Double(0 - 0.1, 3 + 0.1);
        Triangle t = new Triangle(p1, p2, p3);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Assert.assertTrue(i + " " + j, t.circumcircleContains(new Point2D.Double(i, j)));
            }
        }
    }

    @Test
    public void testTriangleCircumcircle2() {
        Point2D.Double p1 = new Point2D.Double(0, 0);
        Point2D.Double p2 = new Point2D.Double(3, 0);
        Point2D.Double p3 = new Point2D.Double(0, 3);
        Triangle t = new Triangle(p1, p2, p3);
        Assert.assertFalse(t.circumcircleContains(new Point2D.Double(0, 0)));
        Assert.assertFalse(t.circumcircleContains(new Point2D.Double(3, 0)));
        Assert.assertFalse(t.circumcircleContains(new Point2D.Double(0, 3)));
    }

    @Test
    public void testTriangleShareVertex() {
        Point2D.Double p1 = new Point2D.Double(0, 0);
        Point2D.Double p2 = new Point2D.Double(3, 0);
        Point2D.Double p3 = new Point2D.Double(0, 3);
        Triangle t1 = new Triangle(p1, p2, p3);
        Triangle t2 = new Triangle(p1, p2, new Point2D.Double(3, 3));
        Triangle t3 = new Triangle(p1, new Point2D.Double(3, 3), new Point2D.Double(4, 4));
        Triangle t4 = new Triangle(new Point2D.Double(3, 3), new Point2D.Double(4, 4), new Point2D.Double(5, 5));
        Assert.assertTrue(t1.sharesVertex(t2));
        Assert.assertTrue(t1.sharesVertex(t3));
        Assert.assertFalse(t1.sharesVertex(t4));
    }
}
