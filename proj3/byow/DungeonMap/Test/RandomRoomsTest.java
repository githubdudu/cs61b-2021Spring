package byow.DungeonMap.Test;

import byow.DungeonMap.GraphUtils;
import byow.DungeonMap.RandomRooms;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

import static byow.DungeonMap.GraphUtils.*;

public class RandomRoomsTest {
    public static void main(String[] args) {
        args = "rectangle 60".split(" ");
        if (args.length != 2) {
            System.out.println("Please input args like \"circleConsecutive 100\"");
            System.exit(0);
        }

        GraphUtils.init();

        Random random = new Random(123L);
        final int N = Integer.parseInt(args[1]);
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA};

        switch (args[0]) {
            case "rectangle":
                // Test Rectangles generation
                StdDraw.ellipse(CENTER.x, CENTER.y, ELLIPSE_A_DEFAULT / 2,
                        ELLIPSE_B_DEFAULT / 2);
                Rectangle[] rectangles = RandomRooms.randomRooms(random, N, 7);
                for (int i = 0; i < N; i++) {
                    Rectangle r = rectangles[i];
                    StdDraw.setPenColor(colors[i % colors.length]);
                    System.out.println(r);
                    GraphUtils.drawRect(r);
                }

                // Test RectangleSeparation
                StdDraw.clear();
                StdDraw.setPenColor();
                RandomRooms.separateOut(random, rectangles);
                for (int i = 0; i < rectangles.length; i++) {
                    GraphUtils.drawRect(rectangles[i]);
                    StdDraw.text(rectangles[i].getX(), rectangles[i].getY(), i + "");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid shape");
        }

    }
}
