# Lec24: Graphs Traversals and Implementation Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec24/lec24

## Recommended Problems

### C level

1. Problem 2a from [Princeton’s Fall 2009 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f09.pdf)
2. Suppose we run BFS from a vertex s. The edgeTo[] array we get back is sometimes known as a ‘Breadth First Paths Tree’. What, if anything, does the BFS tree tell us about the shortest path from v to w, assuming that neither is the source?
3. Problem 1a and 1b from [Princeton’s Spring 2008 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-s08.pdf).
4. Problem 3a from [Princeton’s Fall 2010 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f10.pdf).
5. [Problem 1a and 1b](https://tbp.berkeley.edu/exams/5662/download/) of the Spring 2018 final.
6. [Problem 1a](https://tbp.berkeley.edu/exams/5773/download/) of the Spring 2017 midterm 2.

### B level

1. Problem 2b from [Princeton’s Fall 2009 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f09.pdf).
2. Problem 1c from [Princeton’s Spring 2008 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-s08.pdf).
3. Problem 3b from [Princeton’s Fall 2010 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f10.pdf).
4. Problems 4 and 5 from [Spring 2016’s discussion worksheet](http://datastructur.es/sp16/materials/discussion/discussion11.pdf).
5. Problem 4c from [my Spring 2015 Midterm 2](https://tbp.berkeley.edu/exams/5239/download/).

### A level

1. Develop an algorithm that determines whether or not a directed graph contains an Eulerian tour, i.e. a tour that visits every vertex exactly once.

2. Adapted from Algorithms textbook 4.2.27: Show that the number of different V-vertex directed graphs is 2^(V^2) (reminder, in our course, we do not allow “parallel edges”, i.e. you cannot have two or more edges from a vertex v to another vertex w).

   Then compute an upper bound on the percentage of 20-vertex digraphs that could ever be examined by any computer, under the assumptions that every electron in the universe examines a digraph every nanosecond, that the universe has fewer than 10^80 electrons, and that the age of the universe will be less than 10^20 years.

### Just for fun

1. Adapted from Algorithms textbook 4.2.40: Run experiments to determine empirically the probability that DepthFirstDirectedPaths finds a path between two randomly chosen vertices and to calculate the average length of the paths found, for various random digraph models.