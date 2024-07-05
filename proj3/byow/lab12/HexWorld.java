package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static void addHexagon(TETile[][] world, int size, int x, int y, TETile t) {
        int width = 3 * size - 2;
        for (int i = 0; i < size; i++) {
            int dx = size - 1;
            for (int j = dx - i; j < width - dx + i; j++) {
                world[x + j][y + i] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = i; j < width - i; j++) {
                world[x + j][y + i + size] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
            }
        }
    }

    private static void addSerialOfHexagon(TETile[][] world, int size, int count, int x, int y) {
        for (int i = 0; i < count; i++) {
            addHexagon(world, size, x, y, randomTile());
            x += 4 * size - 2;
        }
    }

    /**
     * Create a Hex world in the pattern that consisting of 19 hexagons.
     *
     * @param renderer TERenderer
     * @param size     size of each hexagon
     * @return TETile[][] with set hexagon pattern
     */
    public static TETile[][] createHexWorld(TERenderer renderer, int size) {
        int widthOfWorld = (size * 2 - 1) * 6 - size;
        int heightOfWorld = 5 * size * 2;
        renderer.initialize(widthOfWorld, heightOfWorld);
        TETile[][] world = new TETile[widthOfWorld][heightOfWorld];
        for (int x = 0; x < widthOfWorld; x++) {
            for (int y = 0; y < heightOfWorld; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        for (int line = 0; line < 9; line += 1) {
            addSerialOfHexagon(world, size, getCount(line), getStartOfX(line, size),
                    getStartOfY(line, size));
        }

        return world;
    }

    public static int getStartOfX(int lineCount, int size) {
        int x = lineCount % 2 == 0 ? 0 : size * 2 - 1;
        if (lineCount == 0 || lineCount == 8) {
            x = 4 * size - 2;
        }
        return x;
    }

    public static int getStartOfY(int lineCount, int size) {
        return lineCount * size;
    }

    public static int getCount(int lineCount) {
        int[] count = {1, 2, 3, 2, 3, 2, 3, 2, 1};
        return count[lineCount];
    }

    /**
     * Picks a RANDOM tile
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(11);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.AVATAR;
            case 3:
                return Tileset.FLOOR;
            case 4:
                return Tileset.GRASS;
            case 5:
                return Tileset.LOCKED_DOOR;
            case 6:
                return Tileset.UNLOCKED_DOOR;
            case 7:
                return Tileset.SAND;
            case 8:
                return Tileset.TREE;
            case 9:
                return Tileset.WATER;
            case 10:
                return Tileset.MOUNTAIN;
            default:
                return Tileset.NOTHING;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        // the size of each hexagon
        int HEX_SIZE = 7;
        ter.renderFrame(createHexWorld(ter, HEX_SIZE));
    }
}
