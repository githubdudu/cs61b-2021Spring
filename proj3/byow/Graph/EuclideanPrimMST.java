package byow.Graph;

import byow.Delaunay.Triangle;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.PrimMST;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Set;

/**
 * A Euclidean representation of the MST of an EuclideanEdgeWeightedGraph.
 */
public class EuclideanPrimMST extends PrimMST {
    private final EuclideanEdgeWeightedGraph graph;
    public EuclideanPrimMST(EuclideanEdgeWeightedGraph G) {
        super(G.graph());
        this.graph = G;
    }

    public Set<Line2D> sides() {
        Set<Line2D> set = new HashSet<>();
        for (Edge edge : super.edges()) {
            int v = edge.either();
            int w = edge.other(v);
            set.add(new Triangle.Side(graph.pointOf(v), graph.pointOf(w)));
        }
        return set;
    }

    public void show() {
        show(StdDraw.GREEN);
    }

    public void show(Color color) {
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(0.006);
        for (Line2D line : sides()) {
            StdDraw.line(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
        StdDraw.setPenRadius();
    }
}
