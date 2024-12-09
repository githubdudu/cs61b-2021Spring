package byow.DungeonMap.Test;

import byow.DungeonMap.GraphUtils;
import byow.DungeonMap.RandomRectangle;
import byow.DungeonMap.RandomRooms;

import edu.princeton.cs.introcs.StdDraw;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * This class tests the drawings.
 * Each test should be run separately and examined by visual inspection.
 */
public class DrawTest {
    private final Random random = new Random(123L);
    private final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA};
    @Before
    public void setUp() {
        GraphUtils.init();
    }
    @Test
    public void testGenerationOfRandomRectangle() throws InterruptedException {
        Ellipse2D ellipse = RandomRooms.getCenterEllipse();
        GraphUtils.drawEllipse(ellipse);

        RandomRectangle randomRectangle = new RandomRectangle(random, ellipse, 7);
        for (int i = 0; i < 100; i++) {
            Rectangle r = randomRectangle.nextRectangle();
            StdDraw.setPenColor(colors[i % colors.length]);
            drawRectInnerLine(r);
            System.out.println(r);
        }
        Thread.sleep(100000);
    }

    @Test
    public void testGenerationOfRandomRooms() throws InterruptedException {
        Rectangle[] rectangles = RandomRooms.randomRooms(random, 100, 7);
        for (int i = 0; i < rectangles.length; i++) {
            StdDraw.setPenColor(colors[i % colors.length]);
            drawRectInnerLine(rectangles[i]);
            System.out.println(rectangles[i]);
        }
        Thread.sleep(100000);
    }


    private void drawRectInnerLine(Rectangle r) {
        StdDraw.rectangle(r.getCenterX(), r.getCenterY(), r.getWidth() / 2 - 0.1, r.getHeight() / 2 - 0.1);
    }
}
