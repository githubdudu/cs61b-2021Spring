# Lec26: Minimum Spanning Trees, Kruskal's, Prim's - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec26/lec26

## Recommended Problems

### C level

1. Problem 2 from [Princeton’s Spring 2008 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-s08.pdf#page=3)

   **Answer:** BD DG EF CH FH CG AH

   (b) AH CH FH EF CG DG BD

1. Would Kruskal or Prim’s algorithm work on a directed graph?

   **Answer:** No. **they do not give the optimal result when applied to directed graphs**.

2. True or false: Adding a constant to every edge weight does not change the solution to the MST problem (assume unique edge weights).

   **Answer:** True. Order of edges won't change.

3. True or false: Multiplying all edges weights with a constant does not change the solution to the MST problem (assume unique edge weights).

   **Answer:** False. if multiplying by negative numbers.

4. True or false: It is possible that the only Shortest Path Tree is the only Minimum Spanning Tree.

   **Answer:** True. A linked-list.

5. True or false: Prim’s Algorithm and Kruskal’s algorithm will always return the same result.

   **Answer:** **Yes and NO, Prim's and Kruskal's algorithms will always produce the same minimum spanning tree (MST) for a given graph, assuming that the graph is connected and  *if the edge weights are all unique*. **. Both algorithms are greedy algorithms, which means that they make locally optimal choices at each step in order to find the globally optimal solution.

### B level

1. Adapted from Algorithms 4.3.8. Prove the following, known as the cycle property: Given any cycle in an edge weighted graph (all edge weights distinct), the edge of maximum weight in the cycle does not belong to the MST of the graph.

   **Answer:** we can always replace the edge of maximum weight with another edge.

2. Problem 3 from [Princeton’s Fall 2009 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-f09.pdf#page=5) (part d is pretty hard).

   **Answer:** (a )1 2 3 5 6 7 8 12

   (b) w <= 8

   (c) 6 1 3 2 5 7 8 12

   (d) Find the unique path between x and y in T . This takes O(V ) time using DFS because
   there are only V −1 edges in T . We claim the edge T remains an MST if and only if w
   is greater than or equal to the weight of every edge on the path.
   • If any edge on the path has weight greater than w, we can decrease the weight of T
   by swapping the largest weight edge on the path with x-y. Thus, T does not remain
   an MST.
   • If w is greater than or equal to the weight of every edge on the path, then the cycle
   property asserts that x-y is not in some MST (because it is the largest weight edge
   on the cycle consisting of the path from x to y plus the edge x-y). Thus, T remains
   an MST.

3. Problem 4 from [Princeton’s Fall 2012 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-f12.pdf#page=5).

   **Answer:** (a) 10 20 30 40 50 100
   (b) x ≤110.
   (c) y ≤60.
   (d) z ≤80.

4. Adapted from Algorithms 4.3.12. Suppose that a graph has distinct edge weights. Does its shortest edge have to belong to the MST? Can its longest edge belong to the MST? Does a min-weight edge on every cycle have to belong to the MST? Prove your answer to each question or give a counterexample.

   **Answer:** `shortest` edge has to belong to the MST. In Kruskal’s Algorithm, it is the shortest edge being added first.

   It `longest` edge can belong to the MST, A list.

   False for a min-weight edge on every cycle have to belong to the MST. It’s a bit of a trick question. Example:

   ```
   1- 2 weight 1
   1- 3 weight 2
   2- 3 weight 4
   2- 4 weight 5
   3- 4 weight 6
   
   ```

   2- 3 is min-weight in cycle 2-3-4, but MST is 3-1-2-4

5. Adapted from Algorithms 4.3.20. True or false: At any point during the execution of Kruskal’s algorithm, each vertex is closer to some vertex in its subtree than to any vertex not in its subtree. Prove your answer.

   **Answer:** True. Otherwise this vertex willl be added to another subtree since Kruskal's algorithm add edges by its weight ascending order. 

6. True or False: Given any two components that are generated as Kruskal’s algorithm is running (but before it has completed), the smallest edge connecting those two components is part of the MST.

   **Answer:** False.

   During Kruskal's algorithms, these two components may be connected by other edges that prior to shortest path.

7. Problem 11 from [my Fall 2014 final](http://datastructur.es/sp15/materials/exams/fin-f14.pdf#page=13).

   **Answer:** 

   **a.** Given a graph G, if we add some constant k to every edge weight, G’s minimal spanning
   tree(s) remain unchanged. **Same as question C3.**
   **Answer: True**. If |V |is the number of vertices, the MST must have |V |−1 edges.
   Therefore, since the total weight of any tree with |V |−1 edges would increase by k|V |−k,
   any minimal tree will remain minimal relative to all other trees.
   **b.** Assuming every vertex is reachable from a given source, Dijkstra’s algorithm always
   finds a shortest path from that source to every vertex.
   **Answer: False.** In this problem, edge weights can be negative, and Dijkstra’s algorithm
   does not work in that case.
   **c.** The shortest edge in any cycle can always be a part of a minimal spanning tree. **Same as question B4.**
   **Answer: False.** A minimal spanning tree need not contain any of the edges in a cycle.
   Consider a tree, T , to which we then add k new edges to form a cycle of k vertices, and
   assume that the weight of each new edge is larger than the sum of the edge weights in
   the original T . Then clearly T itself will be the only MST of the resulting graph.
   **d.** The total weight of a MST of an undirected graph is always less than or equal to the
   total weight of any shortest-path tree for that graph.
   **Answer: True** by definition. A shortest-path tree is a spanning tree (it contains all
   vertices of a connected graph), so its total weight cannot be less than that of a MST.

8. Problem 13 from [my fall 2014 final](http://datastructur.es/sp15/materials/exams/fin-f14.pdf#page=15).

   **Answer:**  (a) 10 20 30 40 50 70 110

   (b) False (c) True (d) 119

9. How would you find the Minimum Spanning Tree where you calculate the weight based off the product of the edges rather than the sum. You may assume that edge weights are >1.

   **Answer:** Just use these algorithms. They apply to this too.

### A level

1. Problem 3 from [Princeton’s Spring 2008 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-s08.pdf#page=4).

   **Answer:** **Same as B2.d**

2. Problem 5 from [Kartik’s Algorithm Worksheet](http://www.kartikkapur.com/documents/DataStructureDesign.pdf#page=2).

   **Answer:** To account for the vertex weights, we will add one dummy node and connect it to every single location.
   The weight of the edge dummy node to any vertex vi would be the cost to build in the location vi. Now
   we simply run Kruskal’s or Prim’s and we will get a valid solution. Below is a picture of the original graph
   followed by a picture of the graph with the included dummy node.

3. Rigorously prove the following: For any cut C, if the weight of any edge e is smaller than all the other edges across C, then this edge is part of the Minimum Spanning Tree.

   **Answer:** If smaller but not part of MST, then there is a another edge smaller than this. Contradiction.

4. Adapted from Textbook 4.3.26: An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. Show how to find all critical edges in a graph in time proportional to E log E . Note : This question assumes that edge weights are not necessarily distinct (otherwise all edges in the MST are critical).

   **Answer:** Just check each case of removing one MST edge.