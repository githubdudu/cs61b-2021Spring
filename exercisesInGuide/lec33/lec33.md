# Lec 33: Sorting IV (Sorting Bounds) - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec33/lec33

## Recommended Problems



### C level

1. Problem 3 [from my Fall 2014 midterm](http://datastructur.es/sp16/materials/exam/CS61B_Fall2014_MT2.pdf).

   Same with Lec32 C1. Skip.

### B level

1. Make sure that you understand exactly why I chose Ω, Θ, and O in “Seeking a Sorting Lower Bound” and “Establishing a Sorting Lower Bound” above.

2. My [Fall 2013 midterm, problem 7](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/mid-f13.pdf), particularly part b.

   Same with Lec32 B1

3. My [Fall 2014 midterm, problem 6](http://datastructur.es/sp16/materials/exam/CS61B_Fall2014_MT2.pdf).

   Same with Lec32 B2 

4. Answer the questions from the [Sounds of Sorting lecture slide](https://docs.google.com/presentation/d/1XdAjNsqYRDLqOpgUeGmPSTT0EmvnXppBSmgTNPME1Qc/pub?start=false&loop=false&delayms=3000&slide=id.g4671a419d_07).

   **Answer:** 

   **How many items for selection sort?** 

   125.

   125 * (1 + 125) / 2 = 7875

   **Why does insertion sort take longer / more compares than selection sort?**

   ****More items.** 

   15526 array accesses for selection sort.  51116 array accesses for insertion sort.

   **At what time stamp does the first partition complete for Quicksort?**

   0.41

   **Could the size of the input to mergesort be a power of 2?**

   Of course.

   **What do the colors mean for heapsort?**

   Means the level of element in heap tree.

### A level

1. Find the optimal decision tree for playing puppy, cat, dog, walrus.

   **Answer: ** Build a decision tree with max level of 5. log(4!) = log(24) < 5

2. My [Spring 2013 midterm, problem 7](http://www.cs.princeton.edu/courses/archive/spr13/cos226/exams/mid-s13.pdf). 

   Same with lec32 A1.