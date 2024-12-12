package byow.DungeonMap.Test;

import byow.DungeonMap.DungeonMap;
import byow.DungeonMap.GraphUtils;
import byow.DungeonMap.RandomRooms;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class RandomRoomsTest {
    public static void main(String[] args) {
        args = "rectangle 60".split(" ");
        if (args.length != 2) {
            System.out.println("Please input args like \"circleConsecutive 100\"");
            System.exit(0);
        }

        GraphUtils.initCanvas(GraphUtils.SETTINGS1);

        Random random = new Random(123L);
        final int N = Integer.parseInt(args[1]);
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA};

        switch (args[0]) {
            case "rectangle":
                // Test Rectangles generation
                Ellipse2D el = new DungeonMap(GraphUtils.SETTINGS1).getCenterEllipse();
                GraphUtils.drawEllipse(el);
                RandomRooms rr = new RandomRooms(random, el, N, 7);
                Rectangle[] rectangles = rr.getRooms();
                for (int i = 0; i < N; i++) {
                    Rectangle r = rectangles[i];
                    StdDraw.setPenColor(colors[i % colors.length]);
                    System.out.println(r);
                    GraphUtils.drawRect(r, String.format("%d", i));
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid shape");
        }

    }
}
