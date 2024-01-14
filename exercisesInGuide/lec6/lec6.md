# Lec6: Lists4 Study Guide - Exercises C & B

Ref: https://sp21.datastructur.es/materials/lectures/lec6/lec6

### C Level

1. Complete the exercises from the online textbook [here](https://joshhug.gitbooks.io/hug61b/content/chap2/chap25.html).

   2.5.1, 2.5.2 coding.

   2.5.3 **answer**: We resize the array. That means we need to copy the previous array to a larger new one.

   2.5.4 coding

   2.5.5 **answer:** We will need to create 100 and fill 2. We have 100 at any time.

   2.5.6 **answer:** created and filled: 100 + 101 + ... + 900 = 495000

### B Level

1. We did not touch upon the method `addFirst` for an `AList`. Think of some of the problems you would experience in writing an `addFirst` method, and think of some potential solutions. If you think you've got a good one, write it out.

   **answer:** The problem is that we need to rewrite the total Array at every `addFirst`. Potential solutions: use dynamic head and tail sign. See `ArrayDeque.java` in Project 1, the implementation of `ArrayDeque` is using this solution. 

2. Do the bonus question from this [slide](https://docs.google.com/presentation/d/1LGQeMHb8-HFKdvJi5nGKRIPZt4on18fZe-cIyTJv8_4/edit#slide=id.g1c42a46f23_4_396)

   > Bonus question: What is the maximum number of array boxes that Java will track at any given time? Assume that "garbage collection" happens immediately when all references to an object are lost.

   Java will track 100 array boxes to 1000 array boxes during this period. When resizing, the boxes tracked will be temporarily doubled as N *2 +1.

   