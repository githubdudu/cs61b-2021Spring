# Lec 30: Sorting II (Quicksort)  - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec30/lec30

## Recommended Problems



### C level

1. Give a worst case input for Quicksort. Assume that we’re always picking the leftmost item as our pivot.

   **Answer: **

   **Example of Worst-Case Input (Ascending Order):**

   [1,2,3,4,5,6,7,8,9,10]

   **Example of Worst-Case Input (Descending Order):**

   [10,9,8,7,6,5,4,3,2,1]

### B level

1. (From Textbook 2.3.13) What is the recursive depth of quicksort, in the best, worst, and average cases? This is the size of the call stack that the system needs to keep track of the recursive calls.

   **Answer: ** Best O(logN); Worst O(N); Average O(logN). 

2. Suppose we use an enhanced partitioning strategy that splits items into three types: items < the pivot, items = to the pivot, and items > the pivot. Supposing that this 3-way partitioning strategy takes Θ(N) time. Prove that Quicksort on an array with N items but only 7 distinct keys (e.g. [0, 1, 0, 0, 6, 6, 5, 5, 4, 2, 2, 0, 3, 0, 1, …, 2, 6]) runs in Θ(N) time.

   **Answer: ** Worst case, sort 0 and 1 in first round, sort 2 and 3 in second round, 4, 5 and 6 in third round,

   Best case, sort 3 in first round, and 0, 1, 2 in second round, 4, 5 and 6 in third round. Always finish in three rounds : O(3N).

### A+ level

1. Find an array of integers that causes Arrays.sort to crash. `Arrays.sort(int[])` uses Quicksort. See `http://www.cs.dartmouth.edu/~doug/mdmspe.pdf` for some ideas.

   **Answer: ** Maybe a reverse sorted array? 

