# Lec25: Shortest Paths Guide - Exercises

Ref:https://sp21.datastructur.es/materials/lectures/lec25/lec25

## Recommended Problems

### C level

1. Suppose we have an edgeTo[] and a distTo[] array, how would we implement a `public int distTo(int w)` method? How would we implement a `public Iterable<int> pathTo(int w)` method, where `distTo` returns the length of the shortest path and `pathTo` returns an Iterable of vertices where the 0th element is the source, the 1st element in the next item from the source towards w, and so forth.
2. Problem 4 from [Princeton’s Fall 2009 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f09.pdf).
3. True or false: Adding a constant to every edge weight does not change the solution to the single-source shortest-paths problem.
4. True or false: Multiplying a positive constant to every edge weight does not change the solution to the single-source shortest-paths problem.
5. Problem 1c and 1d from [my Spring 2016 final](https://tbp.berkeley.edu/exams/5662/download/).
6. Problem 9 from [my Spring 2015 final](https://tbp.berkeley.edu/exams/4911/download/).

### B level

1. (great problem) Problem 4 from [Princeton’s Fall 2011 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f11.pdf). Note that when the exam says to “relax a vertex”, that mean to relax all of a vertex’s edges. A similar problem is given as #6 on [Princeton’s Spring 2012 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-s12.pdf).
2. Problem 5 from [Princeton’s Fall 2012 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f12.pdf).
3. Problem 1 from [Kartik’s mock final](http://www.kartikkapur.com/documents/practicefinal.pdf#page=2)
4. Adapted from Algorithms 4.4.25: Given a digraph with positive edge weights, and two distinguished subsets of vertices S and T, find a shortest path from any vertex in S to any vertex in T. Your algorithm should run in time proportional to E log V, in the worst case.

### A level

1. Problem 5 from [my Spring 2013 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-s13.pdf).
2. Problem 6 from [Kartik’s Algorithm Worksheet](http://www.kartikkapur.com/documents/DataStructureDesign.pdf#page=2)
3. Describe a family of graphs with V vertices and E edges for which the worst-case running time of Dijkstra’s algorithm is achieved.
4. Iterative DFS: Problem 6 from [this semester’s discussion worksheet](http://datastructur.es/sp16/materials/discussion/discussion11.pdf) provides a flawed implementation of DFS.

### A+ level

1. Adapted from Algorithms 4.4.34. Give an algorithm to solve the following problem: Given a weighted digraph, find a monotonic shortest path from s to every other vertex. A path is monotonic if the weight of every edge on the path is either strictly increasing or strictly decreasing. The path should be simple (no repeated vertices).
2. Adapted from Algorithms 4.4.37. Develop an algorithm for finding an edge whose removal causes maximal increase in the shortest-paths length from one given vertex to another given vertex in a given edge-weighted digraph.

### A++ level

1. Problem 12 from my [http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-f13.pdf](https://sp21.datastructur.es/materials/lectures/lec25/Fall%202013%20final).