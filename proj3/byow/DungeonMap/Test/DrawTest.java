package byow.DungeonMap.Test;

import byow.DungeonMap.DungeonMap;
import byow.DungeonMap.GraphUtils;
import byow.Rooms.RandomRoomGenerator;
import byow.Rooms.RandomRooms;
import edu.princeton.cs.introcs.StdDraw;
import org.junit.Before;
import org.junit.Test;

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
        GraphUtils.initCanvas(GraphUtils.SETTINGS1);
    }
    @Test
    public void testGenerationOfRandomRectangle() throws InterruptedException {
        Ellipse2D ellipse = new DungeonMap(GraphUtils.SETTINGS1, 123L).getCenterEllipse();
        GraphUtils.drawEllipse(ellipse);

        RandomRoomGenerator generator = new RandomRoomGenerator(random, ellipse, 7);
        for (int i = 0; i < 100; i++) {
            Rectangle r = generator.nextRectangle();
            StdDraw.setPenColor(colors[i % colors.length]);
            drawRectInnerLine(r);
            System.out.println(r);
        }
        Thread.sleep(100000);
    }

    @Test
    public void testGenerationOfRandomRooms() throws InterruptedException {
        GraphUtils.SETTINGS1.CELL_COUNT = 100;
        Rectangle[] rectangles = new RandomRooms(random,
                new DungeonMap(GraphUtils.SETTINGS1, 123L).getCenterEllipse(), GraphUtils.SETTINGS1).getAllRooms();
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
