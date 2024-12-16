package byow.DungeonMap;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.PrimMST;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Set;

public class EuclideanPrimMST extends PrimMST {
    private EuclideanEdgeWeightedGraph graph;
    public EuclideanPrimMST(EuclideanEdgeWeightedGraph G) {
        super(G.graph());
        this.graph = G;
    }

    public Iterable<Line2D> lines() {
        Set<Line2D> set = new HashSet<>();
        for (Edge edge : super.edges()) {
            int v = edge.either();
            int w = edge.other(v);
            set.add(new Line2D.Double(graph.pointOf(v), graph.pointOf(w)));
        }
        return set;
    }

    public void show() {
        show(StdDraw.GREEN);
    }

    public void show(Color color) {
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(0.004);
        for (Line2D line : lines()) {
            StdDraw.line(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
    }
}
