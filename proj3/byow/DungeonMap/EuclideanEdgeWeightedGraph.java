package byow.DungeonMap;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class EuclideanEdgeWeightedGraph {
    private Map<Point2D, Integer> map; // Point2D -> index
    private Point2D[] keys; // index -> Point2D
    private EdgeWeightedGraph graph; // the graph

    public EuclideanEdgeWeightedGraph(Iterable<Line2D> lines, int N) {
        map = new HashMap<>();
        graph = new EdgeWeightedGraph(N);

        for (Line2D line : lines) {
            Point2D p1 = line.getP1();
            Point2D p2 = line.getP2();
            if (!map.containsKey(p1)) {
                map.put(p1, map.size());
            }
            if (!map.containsKey(p2)) {
                map.put(p2, map.size());
            }
            graph.addEdge(new Edge(map.get(p1), map.get(p2), p1.distance(p2)));
        }

        // inverted indexOf to get string keys in an array
        keys = new Point2D[map.size()];
        for (Point2D p : map.keySet()) {
            keys[map.get(p)] = p;
        }

    }

    public boolean contains(Point2D s) {
        return map.containsKey(s);
    }

    public int indexOf(Point2D s) {
        return map.get(s);
    }

    public Point2D pointOf(int v) {
        return keys[v];
    }

    public EdgeWeightedGraph graph() {
        return graph;
    }

    public void show() {
        StdDraw.setPenColor(StdDraw.GREEN);
        for (Edge edge : graph.edges()) {
            int v = edge.either();
            int w = edge.other(v);
            Point2D p1 = pointOf(v);
            Point2D p2 = pointOf(w);
            StdDraw.line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }
}