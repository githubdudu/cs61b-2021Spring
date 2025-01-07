package byow.Graph;

import edu.princeton.cs.algs4.Digraph;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

class EuclideanDigraph {
    private Map<Point2D, Integer> map; // Point2D -> index
    private Point2D[] keys; // index -> Point2D
    private Digraph digraph; // the graph

    public EuclideanDigraph(Iterable<Line2D> lines, int N) {
        map = new HashMap<>();
        digraph = new Digraph(N);

        for (Line2D line : lines) {
            Point2D p1 = line.getP1();
            Point2D p2 = line.getP2();
            if (!map.containsKey(p1)) {
                map.put(p1, map.size());
            }
            if (!map.containsKey(p2)) {
                map.put(p2, map.size());
            }
            digraph.addEdge(map.get(p1), map.get(p2));
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

    public Digraph graph() {
        return digraph;
    }

    public void show() {

    }
}