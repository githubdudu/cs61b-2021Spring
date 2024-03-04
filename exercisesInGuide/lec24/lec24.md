# Lec24: Graphs Traversals and Implementation Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec24/lec24

## Recommended Problems

### C level

1. Problem 2a from [Princeton’s Fall 2009 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f09.pdf)

   **Answer:** `A B C D E G F H I` 

2. Suppose we run BFS from a vertex s. The edgeTo[] array we get back is sometimes known as a ‘Breadth First Paths Tree’. What, if anything, does the BFS tree tell us about the shortest path from v to w, assuming that neither is the source?

   **Answer:** No, it doesn't. We can just know the shortest path from source to any other vertex.

3. Problem 1a and 1b from [Princeton’s Spring 2008 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-s08.pdf).

   **Answer:** 1a) `A B C E D G H F I`

   1b) `A B D E C F G H I`

4. Problem 3a from [Princeton’s Fall 2010 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f10.pdf).

   **Answer:** 3a)

   Preorder: `A B G C D F E H I`

   Postorder: `G B E I H F D C A` 

5. [Problem 1a and 1b](https://tbp.berkeley.edu/exams/5662/download/) of the Spring 2018 final.

   **Answer: ** 

   a)`61A, 61B, 169, 170, 188, 61C`

   `169, 170, 188, 61B, 61C, 61A`

   `61A, 61B, 61C, 169, 170, 188`

   b) 

   `61A, 61B, 61C, 169, 70, 170, 188`

6. [Problem 1a](https://tbp.berkeley.edu/exams/5773/download/) of the Spring 2017 midterm 2.

   **Answer: ** `9,0,1,6,5,2,7`

   `0,2,7,5,6,1,9`

   `9,0,1,2,6,7,5`

### B level

1. Problem 2b from [Princeton’s Fall 2009 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f09.pdf).

   **Answer**: (d) I, II and III.

2. Problem 1c from [Princeton’s Spring 2008 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-s08.pdf).

   **Answer: ** When I am trying to find the shortest path.

3. Problem 3b from [Princeton’s Fall 2010 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f10.pdf).

   **Answer:**  I and II only.

4. Problems 4 and 5 from [Spring 2016’s discussion worksheet](http://datastructur.es/sp16/materials/discussion/discussion11.pdf).

   **Answer: **

   **Problem4** To solve this problem, we simply run a special version of DFS or BFS from any vertex. This special version marks the start vertex with a U, then each of its children with a V, and each of their children with a U, and so forth. For the vertices who are already marked, check if its U mark or V mark is right. It is also a directed version of TwoColor on Page 547 of the textbook Algorithms.

   O(V + E)

   **Problem5** The solution is the Directed Cycle on Algorithms 4th edition by Robert Sedgewick and Kevin Wayne, Page 577, . It is also a directed version of Cycle on Page 547.

5. Problem 4c from [my Spring 2015 Midterm 2](https://tbp.berkeley.edu/exams/5239/download/).

   ( write  a  method  sumDescendants)

   **Answer: ** 

   ```java
   /* Replaces value with sum of all of its descendants' values. */ 
           public int sumDescendants() { 
               if (left == null && right == null) { 
                   int oldVal = value; 
                   value = 0_________; 
                   return oldVal; 
               } 
               else { 
                 	int sumLeft = 0;
                 	int sumRight = 0;
   								if(left != null){
                     sumLeft = left.sumDescendants();
                   }
                   if(right != null){_______________________________ 
                     sumRight = right.sumDescendants();______________ 
                   }______________________________ 
                   _______________________________ 
                   _______________________________ 
                   int oldVal = value; 
                   value = sumLeft + sumRight________________________; 
                   return oldVal + value; 
               } 
           }
   ```

   

### A level

1. Develop an algorithm that determines whether or not a directed graph contains an Eulerian tour, i.e. a tour that visits every vertex exactly once.

   **Answer: ** It's definition might be wrong: on the textbook P.562: 	Euler cycles (cycles that visit each edge exactly once); Hamilton cycles (cycles that visit each vertex exactly once);

   Let's try to answer: a tour that visits every vertex exactly once.

   Use `dfs`, maintain an array that contains all the node that on the search path. When path meet the end, check if the length of the array equals to V. 

   We'd try every V as a source.

2. Adapted from Algorithms textbook 4.2.27: Show that the number of different V-vertex directed graphs is 2^(V^2) (reminder, in our course, we do not allow “parallel edges”, i.e. you cannot have two or more edges from a vertex v to another vertex w).

   
   
   Then compute an upper bound on the percentage of 20-vertex digraphs that could ever be examined by any computer, under the assumptions that every electron in the universe examines a digraph every nanosecond, that the universe has fewer than 10^80 electrons, and that the age of the universe will be less than 10^20 years.
   
   
   
   **Answer:** 
   
   There are about V^2 possible directed edges. Whether or not each of edges being added are two choices, so there will be totally 2^(V^2) possiblities. 
   
   
   
   ```
   int vertex = 20;
   int possibleGraphs = 2^(V^2) = 2^(20 * 20) = 2^400 = 10^(3 * 40) = 10^120;
   int electronsCount = 10^80;
   int calculationFrequencyPerSecond = 10^9;
   int ageOfTheUniverseInSeconds = 10^20*60*60*24*365 = 3.0 x 10^27;
   
   double percentageExamined 
   = electronsCount * calculationFrequencyPerSecond * ageOfTheUniverseInSeconds /possibleGraphs
   = 10^80 * 10^9 * 3.0 x 10^27 / 10^120 
   = 0.0003 = 0.03%
   ```

### Just for fun

1. Adapted from Algorithms textbook 4.2.40: Run experiments to determine empirically the probability that DepthFirstDirectedPaths finds a path between two randomly chosen vertices and to calculate the average length of the paths found, for various random digraph models.	

   **Answer**: Skip the code.

   *Testing all algorithms and studying all parameters against all graph models is unrealistic. For each problem listed below, write a client that addresses the problem for any given input graph, then choose among the generators above to run experiments for that graph model. Use your judgment in selecting experiments, perhaps in response to results of previous experiments .*

   The generators may be:

   1. *Random digraphs* 
   2. *Random simple digraphs*
   3. *Random sparse digraphs*
   4. *Random Euclidean digraphs*
   5. *Random grid digraphs*
   6. *Real-world digraphs*
   7. *Real-world DAG*

​		





​		