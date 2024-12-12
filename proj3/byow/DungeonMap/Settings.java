package byow.DungeonMap;

import java.awt.*;

public class Settings {
    /**
     * The width of the canvas.
     */
    public int WIDTH;
    /**
     * The height of the canvas.
     */
    public int HEIGHT;
    /**
     * The size of the grid.
     */
    public int GRID_SIZE;
    /**
     * The default major axis of the ellipse where the center of the rectangle is generated.
     */
    public double ELLIPSE_A_DEFAULT;
    /**
     * The default minor axis of the ellipse where the center of the rectangle is generated.
     */
    public double ELLIPSE_B_DEFAULT;
    /**
     * The center of the canvas.
     */
    public Point CENTER;
    /**
     * The number of rectangles to create.
     */
    public int CELL_COUNT;
    /**
     * The threshold to determine the main rooms.
     */
    public double SIZE_THRESHOLD;
    /**
     * The lambda value for the Poisson distribution to generate the width and height of the rectangle.
     */
    public int LAMBDA;

    public Settings(int WIDTH, int HEIGHT, int GRID_SIZE, double ELLIPSE_A_DEFAULT,
                    double ELLIPSE_B_DEFAULT, int CELL_COUNT,
                    double SIZE_THRESHOLD,
                    int LAMBDA) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.GRID_SIZE = GRID_SIZE;
        this.ELLIPSE_A_DEFAULT = ELLIPSE_A_DEFAULT;
        this.ELLIPSE_B_DEFAULT = ELLIPSE_B_DEFAULT;
        this.CENTER = new Point(WIDTH / 2, HEIGHT / 2);
        this.CELL_COUNT = CELL_COUNT;
        this.SIZE_THRESHOLD = SIZE_THRESHOLD;
        this.LAMBDA = LAMBDA;
    }
}
