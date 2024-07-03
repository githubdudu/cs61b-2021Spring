# Lec25: Shortest Paths Guide - Exercises

Ref:https://sp21.datastructur.es/materials/lectures/lec25/lec25

## Recommended Problems

### C level

1. Suppose we have an edgeTo[] and a distTo[] array, how would we implement a `public int distTo(int w)` method? How would we implement a `public Iterable<int> pathTo(int w)` method, where `distTo` returns the length of the shortest path and `pathTo` returns an Iterable of vertices where the 0th element is the source, the 1st element in the next item from the source towards w, and so forth.

   **Answer**: It is a shortest path problem. It is Breadth First Paths algorithm should be applied.  See the Algorithm 4.2 on textbook. A copy of the algorithm is under folder C1.

2. Problem 4 from [Princeton’s Fall 2009 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f09.pdf).

   **Answer:** (a)

   ```
   vertex:   A C D  F  H  E  B  G  I
   
   Distance: 0 1 12 20 25 28 34 40 53
   ```

   (b) Omit graph. A-C, C-D, C-B, D-F, F-H, H-E, E-G, G-I

3. True or false: Adding a constant to every edge weight does not change the solution to the single-source shortest-paths problem.

   **Answer:** False. Example: 

   ```
   A -1-> B -1-> C, A -3-> C
   ```

   The SPT is A-B-C. If we add 100 to each edge.

   ```
   A -101-> B -101-> C, A -103-> C
   ```

   The shortest path becomes A-B, A-C

4. True or false: Multiplying a positive constant to every edge weight does not change the solution to the single-source shortest-paths problem.

   **Answer:** True.  Let's say, the shortest path will not change if you drive slower on the road.

5. Problem 1c and 1d from [my Spring 2016 final](https://tbp.berkeley.edu/exams/5662/download/).

   **Answer:**

   1c: A C B D E G F

   1d: 8 14

6. Problem 9 from [my Spring 2015 final](https://tbp.berkeley.edu/exams/4911/download/).

   **Answer:** a. A B C D F H G E

   b. Vertex `E` and vertex `H` to `-3`. 

   c. A0 B3 (D5) H7 G11

   

### B level

1. (great problem) Problem 4 from [Princeton’s Fall 2011 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f11.pdf). Note that when the exam says to “relax a vertex”, that mean to relax all of a vertex’s edges. A similar problem is given as #6 on [Princeton’s Spring 2012 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-s12.pdf).

   **Answer:** (a) From the distTo array, we can easily defer the first five vertices which have smaller distTo value: "3, 0, 10, 5, 2"

   (b)

   When 3 deleted from queue. 

   3 -> 0 1.0

   3 -> 10 3.0

   3-> 8 9

   3-> 6 13

   3 -> 1 25

   Then 0 deleted from queue.

   3 -> 10 3.0

   0->2 7

   0->4 7

   3-> 8 9

   3-> 6 13

   0->5 18

   3 -> 1 25

   Then 10 deleted from queue.

   10->5 5

   0->2 7

   0->4 7

   3-> 8 9

   3-> 6 13

   10 -> 1 18

   Then 5 deleted from queue.

   5->2 6

   0->4 7

   3-> 8 9

   3-> 6 13

   5->7 15

   5-> 1 17

   Then 2 deleted from queue.

   0->4 7

   3-> 8 9

   2->7 12

   3-> 6 13

   5-> 1 17

   Then 4 deleted from queue.

   4-> 8 8

   4->7 10

   4-> 6 11

   5-> 1 17

   4-> 9 22

2. Problem 5 from [Princeton’s Fall 2012 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f12.pdf).

   **Answer:** (a) 0 1 5 4

   (b) Before we begin: 

   Fringe: [ 0 (0),1 (inf), 2 (inf), 3 (inf), 4 (inf), 5 (inf), 6 (inf), 7 (inf) ]  

   0: Lower cost paths found for 1, 2, 3.   

   Fringe: [ 1 (2), 2 (15), 3 (23), 4 (inf), 5 (inf), 6 (inf), 7 (inf) ] 

   1(2): Lower cost paths found for 4, 5. 

   Fringe: [ 5 (7), 2 (15), 4 (17), 3 (23), 6 (inf), 7 (inf) ] 

   5(7): Lower cost paths found for 4(11), 7(7+ y), 6(36), 2(13)

   Fringe: [4(11), 2(13), 3(23), 6(36)]  7(7 + y), 7 + y > 11 , y = 5, 6, 7

   4(11): found potential lower cost path for 7(11 + x), 11+ x = 19, x = 8

   Fringe: [ 2(13), 7(19), 3(23), 6(36)]

   x = 8

   (c) y > 12

   (d) 2(13) deleted, 3 and 6 updated

   Fringe: [7(19), 3(20), 6(35)]

3. Problem 1 from [Kartik’s mock final](http://www.kartikkapur.com/documents/practicefinal.pdf#page=2)

   **Answer:** (a) See question C.6b

   (b) Two nodes graph won't fail.

   (c) Cite answer: 1 edge is the least amount of negative edges for which Dijkstra’s algorithm works. This will only 
   work for directed graphs and if the shortest path to the vertex the edge goes to would be the 
   shortest path if the negative edge was weighted 0. 

   Most amount of negative paths is all the path. If the graph is a tree. 

   (d) no negative edges.

4. Adapted from Algorithms 4.4.25: Given a digraph with positive edge weights, and two distinguished subsets of vertices S and T, find a shortest path from any vertex in S to any vertex in T. Your algorithm should run in time proportional to E log V, in the worst case.

   **Answer:**  Find the smallest bridge. Then use the two nodes to find all shortest path.

### A level

1. Problem 5 from [my Spring 2013 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-s13.pdf).

   **Answer:** (a) 3 5 1 7 4 (b) 4, distTo[]= 1, edgeTo[4]=6. (c) edge(3-7) >= 10. Then there will be path from 6->4 prior to 7->4.

2. Problem 6 from [Kartik’s Algorithm Worksheet](http://www.kartikkapur.com/documents/DataStructureDesign.pdf#page=2)

   You are an avid traveler who wants to travel between the 2 cities in your country which consists of V cities.
   To go between these cities you have decided that you can narrow down your modes of transportation to
   dinghy sailing, gliding, and unicycling. For every city in the country, you are given a list of cities that you
   can get to- this list remains constant regardless of which mode of transportation you pick. The set of all
   sailing costs is labeled as Es, the set of all gliding costs is given ans Eg, and the cost of all unicycling costs
   is the set Eu.
   Sadly, you get sea/air/landsick quite frequently so you cannot take the same mode of transportation
   consecutively- in other words you cannot go into city i with unicycling and leave unicycling. Find an
   algorithm that computes the fastest route from city i to city j with your constraints.

   **ANswer:** 

   Add 3 dummy vertices with a zero-weight edge to each for each city. Each vertices represent one way of travelling.

   And apply *Dijkstra’s algorithm.*

3. Describe a family of graphs with V vertices and E edges for which the worst-case running time of Dijkstra’s algorithm is achieved.

   **ANswer:** In worst case, graph will be a complete graph. E = V(V - 1)/2

4. Iterative DFS: Problem 6 from [this semester’s discussion worksheet](http://datastructur.es/sp16/materials/discussion/discussion11.pdf) provides a flawed implementation of DFS.

   **Answer**: push all the neighbor of the vertex into fringe makes it not DFS.

   ```
   A- - D
   | \C |
   B -- -
   ```

   

### A+ level

1. Adapted from Algorithms 4.4.34. Give an algorithm to solve the following problem: Given a weighted digraph, find a monotonic shortest path from s to every other vertex. A path is monotonic if the weight of every edge on the path is either strictly increasing or strictly decreasing. The path should be simple (no repeated vertices).

   **Answer**:  *Partial solution*: relax edges in ascending order and find a best path; then relax edges in descending order and find a best path.

2. Adapted from Algorithms 4.4.37. Develop an algorithm for finding an edge whose removal causes maximal increase in the shortest-paths length from one given vertex to another given vertex in a given edge-weighted digraph.

   **Answer:**

   1. remove the last edge before t, and run shortest path from s to t, find the change in distance, if infinite return
   2. compare the new path with the original one, and find the last common vertex, use that as new t, repeat 1, 2
   3. when reach s, compare all the distance changes by removals, decide which is the critical one

### A++ level

1. Problem 12 from my [http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-f13.pdf](https://sp21.datastructur.es/materials/lectures/lec25/Fall%202013%20final).

​	**Answer: **
​	(a) Using *Dijkstra algorithm* , instead of maintaining a distance table for each vertex, we maintaining two distances for each vertex. We only mark a vertex until it has two min distances.

​	(b) Using two array to maintain the Fringe. One contains vertices that has odd-length path to, one contains even-length path to.

And two kind of mark array, one is visited as even step, one is visited as odd step.