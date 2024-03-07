# Lec26: Minimum Spanning Trees, Kruskal's, Prim's - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec26/lec26

## Recommended Problems

### C level

1. Problem 2 from [Princeton’s Spring 2008 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-s08.pdf#page=3)
2. Would Kruskal or Prim’s algorithm work on a directed graph?
3. True or false: Adding a constant to every edge weight does not change the solution to the MST problem (assume unique edge weights).
4. True or false: Multiplying all edges weights with a constant does not change the solution to the MST problem (assume unique edge weights).
5. True or false: It is possible that the only Shortest Path Tree is the only Minimum Spanning Tree.
6. True or false: Prim’s Algorithm and Kruskal’s algorithm will always return the same result.

### B level

1. Adapted from Algorithms 4.3.8. Prove the following, known as the cycle property: Given any cycle in an edge weighted graph (all edge weights distinct), the edge of maximum weight in the cycle does not belong to the MST of the graph.
2. Problem 3 from [Princeton’s Fall 2009 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-f09.pdf#page=5) (part d is pretty hard).
3. Problem 4 from [Princeton’s Fall 2012 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-f12.pdf#page=5).
4. Adapted from Algorithms 4.3.12. Suppose that a graph has distinct edge weights. Does its shortest edge have to belong to the MST? Can its longest edge belong to the MST? Does a min-weight edge on every cycle have to belong to the MST? Prove your answer to each question or give a counterexample.
5. Adapted from Algorithms 4.3.20. True or false: At any point during the execution of Kruskal’s algorithm, each vertex is closer to some vertex in its subtree than to any vertex not in its subtree. Prove your answer.
6. True or False: Given any two components that are generated as Kruskal’s algorithm is running (but before it has completed), the smallest edge connecting those two components is part of the MST.
7. Problem 11 from [my Fall 2014 final](http://datastructur.es/sp15/materials/exams/fin-f14.pdf#page=13).
8. Problem 13 from [my fall 2014 final](http://datastructur.es/sp15/materials/exams/fin-f14.pdf#page=15).
9. How would you find the Minimum Spanning Tree where you calculate the weight based off the product of the edges rather than the sum. You may assume that edge weights are >1.

### A level

1. Problem 3 from [Princeton’s Spring 2008 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-s08.pdf#page=4).
2. Problem 5 from [Kartik’s Algorithm Worksheet](http://www.kartikkapur.com/documents/DataStructureDesign.pdf#page=2).
3. Rigorously prove the following: For any cut C, if the weight of any edge e is smaller than all the other edges across C, then this edge is part of the Minimum Spanning Tree.
4. Adapted from Textbook 4.3.26: An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. Show how to find all critical edges in a graph in time proportional to E log E . Note : This question assumes that edge weights are not necessarily distinct (otherwise all edges in the MST are critical).