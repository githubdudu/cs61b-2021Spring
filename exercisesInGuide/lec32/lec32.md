# Lec 32: Sorting III - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec32/lec32

## Recommended Problems



### C level

1. Problem 3 [from my Fall 2014 midterm](http://datastructur.es/sp16/materials/exam/CS61B_Fall2014_MT2.pdf).

   **Answer:**

   Y Y Y Y
    N N Y Y
    N N Y N

2. Why does Java’s built-in `Array.sort` method use Quicksort for `int`, `long`, `char`, or other primitive arrays, but Mergesort for all Object arrays?

   **Answer:**

   **Stability**: Mergesort is a stable sorting algorithm, meaning it maintains the relative order of equal elements. Stability is important for sorting objects, especially when the objects are complex and may have multiple fields that influence their natural ordering.

   **Partitioning Efficiency**: For primitive data types, partitioning operations in Quicksort are straightforward and highly optimized in Java, allowing Quicksort to leverage these optimizations to perform well.

   **Performance Guarantees**: Mergesort has a guaranteed worst-case time complexity of O(nlog⁡n)O(n \log n)O(nlogn). This consistency is valuable for object arrays, where unpredictable performance could be problematic.

### B level

1. My [Fall 2013 midterm, problem 7](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/mid-f13.pdf), particularly part b.

   **Answer:** (a) yes yes yes no no

   (b)A. lf we get randomly ordered double value, we can use quicksort.  Cause no memory limit, we can also use merge sort.

   C. If partially sorted, Insertion sort is the best.

   B. Merge sort. Quick sort may cause problem.

   C. we want to insert back into list. So insertion sort is better.

2. My [Fall 2014 midterm, problem 6](http://berkeley-cs61b.github.io/public_html/materials/exams/mid2-f14.pdf).

   **Answer:**

   ```
   public static void zorkSort(int[] A, int k) {
   	int i;
   	int n = A.length;
   	i = 0;
   	PriorityQueue<Integer> pq = new PriorityQueue<>();
   	while (i < k) {
   		add(A[i]);
   		i += 1;
   	}
   	while (i < n) {
   		A[i - k] = pq.remove();
   		add(A[i]);
   		i += 1;
   	}
   	while (!pq.isEmpty()) {
   	  A[i - k] = pq.remove();
   	  i += 1;
   	}
   }
   ```

   b. n lg(k)

### A level

1. My [Spring 2013 midterm, problem 7](http://www.cs.princeton.edu/courses/archive/spr13/cos226/exams/mid-s13.pdf).

   **Answer:**

   Yes

   Possible : using partition in quick sort

   P: Tarjan?  (weird . not taught)

   I: LLRB is 2logN

   P: Sort and build

   P: 

   I: 

   P: **Proposition R.** Sink-based heap construction uses fewer than 2*N* compares and fewer than *N* exchanges to construct a heap from *N* items.

   I: Certainly we can accomplish this task in time proportional to *N* log *N*, by proceeding from left to right through the array, using swim() to ensure that the items to the left of the scanning pointer make up a heap-ordered complete tree, like successive priority- queue insertions.

   I: the height of tree is higher than expected.

   

2. Given that Quick sort runs fastest if we can always somehow pick the median item as the pivot, why don’t we use Quick select to find the median to optimize our pivot selection (as opposed to using the leftmost item).

   **Answer:** 

   Quick Select just is a quick sort except skipping recursing into one side. 

   The additional overhead of finding the median will exceed the efficiency gains.

3. We can make Mergesort adaptive by providing an optimization for the case where the left subarray is all smaller than the right subarray. Describe how you’d implement this optimization, and give the runtime of a merge operation for this special case.

   **Answer:**

   Just check the biggest element in left subarray and the smallest element in right subarray. If left < right, just skip merge and concatenate them. 

   