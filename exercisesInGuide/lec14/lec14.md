# lec14: Disjoint Sets Study Guide

Ref: https://sp21.datastructur.es/materials/lectures/lec14/lec14

## Recommended Problems

> The so-called Textbook below refers to *Algorithms, 4th Edition* by Wayne and Sedgewick.

> Found out these study guides about algorithms are copied from Princeton lectures. https://www.cs.princeton.edu/courses/archive/fall14/cos226/lectures.php

### C level

1. [Problem 2a](https://tbp.berkeley.edu/exams/5286/download/) from Spring 2016 midterm #2.

   **Answer:** in the pdf file.

2. [Problem 1d](https://tbp.berkeley.edu/exams/5239/download/) from Spring 2015 midterm #1.

   > It must be Problem 1d from Spring 2015 midterm ***#2***. Since problem 1 in midterm #1 is about Java class.

   **Answer:** in the pdf file.

> Below answers are in the pdf file except A.4.
### B level

1. [Problem 1](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/mid-f11.pdf) from the Princeton Fall 2011 midterm.

2. [Problem 1](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/mid-f12.pdf) from the Princeton Fall 2012 midterm.

3. For the `WeightedQuickUnionUF` data structure, the runtime of the `union` and `connected` operations is O(logN). Suppose we create a `WeightedQuickUnionUF` object with N items and then perform MU and MC union and connected operations, give the runtime in big O notation. Answer (highlight to see): 

4. Same as #3, but give an example of a sequence of operations for which the runtime is O(N+MU+MC).

5. (From Textbook 1.5.8) Does the following implementation of Quick-Find work? If not, give a counter-example:

   ```java
    public void connect(int p, int q) {
        if (connected(p, q)) return;
   
        // Rename p’s component to q’s name.
        for (int i = 0; i &lt; id.length; i++) {
            if (id[i] == id[p]) id[i] = id[q];
        }
        count -= 1;
    }
   ```

6. [Problem 3](https://tbp.berkeley.edu/exams/5773/download/) from Spring 2017 midterm #2.

7. [Problem 2b](https://tbp.berkeley.edu/exams/5286/download/) from Spring 2016 midterm #2.

### A level

1. (From Textbook 1.5.10): In weighted quick-union, suppose that we set `id[find(p)]` to q instead of `id[find(q)]`. Would the resulting algorithm be correct?
2. If we’re concerned about tree height, why don’t we use height for deciding tree size instead of weight? What is the worst-case tree height for weighted-quick-union vs. heighted-quick-union? What is the average tree height?
3. [Problem 3](https://tbp.berkeley.edu/exams/6137/download/) from Spring 2018 midterm #2
4. Try writing weighted-quick-union-with-path-compression without looking at the code on the booksite. You may look at the API. Compare your resulting code with [the textbook’s code.](http://algs4.cs.princeton.edu/15uf/WeightedQuickUnionPathCompressionUF.java.html)