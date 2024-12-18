package byow.DungeonMap;

import byow.Core.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.*;

public class DungeonMap {
    private final Settings settings;
    private final Random random;
    private RandomRooms randomRooms;

    private EuclideanEdgeWeightedGraph graph;
    private EuclideanPrimMST mst;
    private List<Line2D> mainPath = new ArrayList<>();

    public DungeonMap(Settings settings) {
        this.settings = settings;
        this.random = new Random(123L);
        this.randomRooms = new RandomRooms(random, getCenterEllipse(), settings);

        List<Point2D> mainCenters = getCenters(this.randomRooms.getMainRooms());
        Set<Triangle> mainTriangles = new Delaunay().bowyerWatson(mainCenters);
        Set<Triangle.Side> mainSides = this.getSides(mainTriangles);

        this.graph = new EuclideanEdgeWeightedGraph(new HashSet<Line2D>(mainSides), mainSides.size());
        this.mst = new EuclideanPrimMST(this.graph);
        this.setMainPath(mainSides, this.mst.sides());

        show();
        System.out.println("There are " + mainTriangles.size() + " triangles.");
        buildPath(this.mainPath);
    }

    /**
     * Returns the ellipse that is centered at the center of the canvas.
     *
     * @return the ellipse
     */
    public Ellipse2D.Double getCenterEllipse() {
        return new Ellipse2D.Double(settings.CENTER.x - settings.ELLIPSE_A_DEFAULT / 2,
                settings.CENTER.y - settings.ELLIPSE_B_DEFAULT / 2, settings.ELLIPSE_A_DEFAULT,
                settings.ELLIPSE_B_DEFAULT);
    }

    /**
     * Get the list of centers of a list of rooms.
     *
     * @param rooms a list of rooms
     * @return the list of centers
     */
    public List<Point2D> getCenters(List<Rectangle> rooms) {
        return rooms.stream().map(
                r -> (Point2D) new Point2D.Double(r.getCenterX(), r.getCenterY())).toList();
    }


    private Set<Triangle.Side> getSides(Iterable<Triangle> triangles) {
        Set<Triangle.Side> sides = new HashSet<>();
        triangles.forEach(t -> {
            sides.add(new Triangle.Side(t.a, t.b));
            sides.add(new Triangle.Side(t.b, t.c));
            sides.add(new Triangle.Side(t.c, t.a));
        });
        return sides;
    }

    private void setMainPath(Set<Triangle.Side> sides, Set<Triangle.Side> mstSides) {
        this.mainPath.addAll(mstSides);

        Set<Triangle.Side> remainingSides = new HashSet<>(sides);
        remainingSides.removeAll(mstSides);

        addRemainingEdgesToMainPath(remainingSides.toArray(Triangle.Side[]::new));
    }

    private void addRemainingEdgesToMainPath(Triangle.Side[] remainingSides) {
        //  choose remaining edges to add to mst
        RandomUtils.shuffle(random, remainingSides);
        int addedCount = (int) (remainingSides.length * 0.15);
        this.mainPath.addAll(Arrays.asList(remainingSides).subList(0, addedCount));
    }

    public void show() {
        this.graph.show();
        this.mst.show();
        for (Line2D line : this.mainPath) {
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.setPenRadius();
            StdDraw.line(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
    }

    public static void main(String[] args) {
        GraphUtils.initCanvas(GraphUtils.SETTINGS1);
        GraphUtils.SETTINGS1.SIZE_THRESHOLD = 1.0;
        DungeonMap dungeonMap = new DungeonMap(GraphUtils.SETTINGS1);

    }
}
