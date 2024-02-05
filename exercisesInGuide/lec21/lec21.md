# Lec21: Heaps and Priority Queues Study Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec21/lec21

## Recommended Problems

Note: The reason I’ve given lots of problems here is not because this is a more important topic, but because there are just so many interesting problems.

### C level

1. Is an array that is sorted in descending order also a max-oriented heap?
   **Answer**: Yes.

   > **Definition.** A *binary heap* is a collection of keys arranged in a complete heap-or- dered binary tree, represented in level order in an array (not using the first entry).

   A sorted array in descending order totally satisfies the definition.

2. Textbook 2.4.2 (assume we’d also like to support delete operations)

   > **2.4.2** Criticize the following idea: To implement *find the maximum* in constant time, why not use a stack or a queue, but keep track of the maximum value inserted so far, then return that value for *find the maximum*?

   **Answer:** Will need to update the maximum value from scratch after a *remove-the-maximum* operation.

3. [Problem 3](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/mid-f09.pdf) from Princeton’s Fall 2009 midterm or [Problem 4](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/mid-f08.pdf) from Princeton’s Fall 2008 midterm.

   **Answer fa2009-p3:** 

   a) K M R

   Position 11 is the last position that the one inserted. So the possible keys are along the "KMRT" path. "T" is not possible since "R" can't be placed at the postion 1.

   b)

   ```
               S
          -----/\
         R       O
         /\      /\
       J   M     K A
      /\   |
      E I  H 
   ```

   

   **Answer fa2008-p4:** 

   a)

   ```
               Z
          -----/\
         S       T
         /\      /\
       P   N    R  A
      /\   |\   /
      E I  H G O
   ```

   b)

   ```
               T
          -----/\
         S       R
         /\      /\
       P   N    O  A
      /\   |\   /
      E I  H G 
   ```

   c) True

   Inserting a key larger than any of the N keys amount to `swim` that key from bottom to the top. Every key on the path will go down one level.

   Deleting the maximum key means delete the same key. So the key that smaller than every key on the path will do `sink` operation. The original parent key will get back to its formal place since it is bigger than its children when selected as a child key.

   

4. Why do we leave the 0th position empty in our array representation for heaps?

   **Answer:** 

   > Doing so simplifies the arithmetic a bit. It is not difficult to implement the heap methods based on a 0-based heap where the children of a[0] are a[1] and a[2], the children of a[1] are a[3] and a[4], the children of a[2] are a[5] and a[6], and so forth, but most programmers prefer the simpler arithmetic that we use. Also, us- ing a[0] as a sentinel value (in the parent of a[1]) is useful in some heap applications.

   

### B level

1. (Textbook 2.4.7) The largest item in a heap must appear in position 1, and the second largest must appear in position 2 or 3. Give the list of positions in a heap of size 31 where the kth largest CAN appear, and where the kth largest CANNOT appear for k=2, 3, 4. Assume values are distinct.

   **Answer:** 

   Use the photo 1 of problem 5 at B level. 

   k=2, possible position: 2,3

   k=3, possible position: 2,3,4,5,6,7

   k=4, possible position: 2~15

   The rest position is the place it can't appear.

   

2. (Textbook 2.4.10) Suppose we wish to avoid wasting one position in a heap-ordered array pq[], putting the largest value in pq[0], its children in pq[1] and pq[2], and so forth, proceeding in level order. Where are the parents and children of pq[k]?

   **Answer:**

   Children: 2k + 1, 2k + 2;

   Parents: (k - 1) / 2 

   

3. (Textbook 2.4.21) Explain how to use a priority queue to implement the stack and queue data types.

   **Answer:** Stack is LIFO. Implement a stack with maximum-oriented PQ, assigning each inserted data with a key, the key start at 0 and increase 1 after each inserting.

   Queue is FIFO. Implement a queue with minimum-oriented PQ, assigning each inserted data with a key, the value start at 0 and increase 1 after each inserting.

   

4. (Adapted from Textbook 2.4.27). Add a min() method to a maximum-oriented PQ. Your implementation should use constant time and constant extra space.

   **Answer:** 

   ```java
    public class MaxPQ<Key extends Comparable<Key>>
    {
        private Key min; // constant extra space
        
        public void insert(Key v)
        {
            if(isEmpty()) { // constant time
                min = v;
            } else {
                min = Math.min(min, v);
            }
            pq[++N] = v;
            swim(N);
        }
        public Key min(){ // constant time
            if(isEmpty()){
                throw new IllegalArgumentException("There is no Key in priority queue");
            } else {
                return min;
            }
        }
   
   ```

   An interesting problem, this `min()` function is like a minimum PQ which size is just 1.

   

5. (Textbook 2.4.14) What is the minimum number of items that must be exchanged during a remove-the-max operationin a heap of size N with no duplicate keys? Give a heap of size 15 for which the minimum is achieved. Answer the same qusetion for two and three successive remove-the-maximum operations.

   **Answer:** 

   From the heap below, that node1 >= 3 >= 7 >=15. After node15 setteled at top of the heap, if node15 exchange with 3, then node15 must be exchanged with 7 too. The minimum exchange happened only when node15 exchange with node2. It is possible that node2 > node3.

   ![image-20240203234355895](lec21.assets/image-20240203234355895.png)

   Hence, minimum number of items that must be exchanged for one move is two( 15 and 2).

   Now `node15 <= node3`, when `node2` removed and `node14` settled at top, there must be an exchange between `node14` and `node3`. After the exchange, the situation for `node14` becomes the same as for `node15` in last `sink` operation, the `node14` has to move at leave once more. So for two successive: (2, 3). For three successive (2, 3, 4).

   ![image-20240203235516117](lec21.assets/image-20240203235516117.png)

   

6. [Problem 4](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/mid-s08.pdf) from Princeton’s Spring 2008 midterm.

   **Answer:** (a) k smallest elements from `a` in descending order; 

   (b) N*log*k 

   (c) k largest elements from `a` in descending order; 

   (d) N*log*N

   

7. [Problem 6](https://d1b10bmlvqabco.cloudfront.net/attach/hx9h4t96ea8qv/h32s1vxe6mb5o0/i7vkubmrxjn0/fa14_mt2.pdf) from my Fall 2014 midterm (mid2).

   **Answer:** 

   ```
   while(i < k){
     pq.add(A[i]);
     i += 1;
   }
   while(i < n){
     A[i - k] = pq.remove();
     pq.add(A[i]);
     i += 1;
   }
   while(!isEmpty() ){
     A[i - k] = pq.remove();
   }
   ```

   

8. [Problem 4](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/mid-f12.pdf) from Princeton’s 2012 midterm.

   **Answer**: (a)

   77 33 75 66 10 30 25 23 60 7 14 21 50 9 

   (b) 3k - 1, 3k, 3k + 1

   (c) 3log_3 N

   

9. [Problem 5a and 5b](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/mid-s13.pdf) of my Spring 2013 midterm.

   **Answer:** (a) V J R H J G F F B F E A E B D

   (b) O(N) linear time. 

   

10. Problem 3 from my [Fall 2014 midterm](http://berkeley-cs61b.github.io/public_html/materials/exams/fin-f14.pdf). (The link is pointing to cs61b_fa14_final-soln and the link is broken. Found the file online and attached it.)

    **Answer**: just copy answer: a) O(lg N/ lg d) 

    b) O(1)

    c) O(d lg N/ lg d)

    d) O(N^2)

    

### A level

1. Design a data type that supports insert in O(log N) time, find-the-median in O(1) time, and delete-the-median in O(log N) time.

   **Answer**: A data type contains a node which has two children. Left children is a maximum-oriented PQ, right children is a minimum-oriented PQ. Let's assume that when the total size is even, the median is the smaller one of the two.

   Invariants: 

   1. Size of left PQ must be equals to size of right PQ, or just smaller than size of right PQ by one.
   2. The node is always be the median.

   ***Insert***: If `left.size() == right.size()`, compare new key with the key of the median, if smaller than the key, we will make this new key as median, and insert previous median into right PQ. If bigger, we just insert new key into right PQ.

   If `left.size() < right.size()`, and new key smaller than the median, insert new key into left PQ. If larger, insert previous median into left PQ, make new key as new median.

   ***Delete***: after remove the median, remove from the PQ that can keep the invariants. Make this removed key as new median.

   ***find***: just return the node.

   

2. Design a data type that supports insert in O(log N) time, delete-the-max in O(log N) time, and delete-the-minimum in O(log N) time.

   **Answer:** 

   Solution1: Using two index PQ. One minimum-oriented, one maximum-oriented.

   Use additional array to trace the index of each item in both PQ. 

   ***Delete***: delete at both PQ

   ***Insert***: insert at both PQ

   Remember updating both index when move item in one PQ.

   

   Solution2: Balanced Search Tree

   Solution3: A min-max heap described in online Textbook web exercise 17. 

   ​	Link: https://algs4.cs.princeton.edu/24pq/    

   ​	Code: http://cg.scs.carleton.ca/~morin/teaching/5408/refs/minmax.pdf 

   ​	(The code link is dead. Found a explaination of the data type: https://medium.com/%E7%8B%97%E5%A5%B4%E5%B7%A5%E7%A8%8B%E5%B8%AB/%E5%9C%96%E8%A7%A3-double-ended-priority-queue-%E9%80%B2%E9%9A%8E%E6%A8%B9-1ae18d2ca402 )

   

3. [Problem 7](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/mid-f10.pdf) from Princeton’s Fall 2010 midterm.

   **Answer:** Replace the key with a nested key that has a timestamp variable.

   Stable means when two keys are tied, return the least-recently inserted. So when they tie, compare their timestamp. Bigger timestamp is the least-recently inserted one.

   

4. [Problem 8](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/mid-s12.pdf) from Princeton’s Spring 2012 midterm.

   **Answer:** Definition of`RandomQueue()`  appeared on Textbook p168  (Textbook problem 1.3.35).

   ![image-20240205021206165](lec21.assets/image-20240205021206165.png)

   ```java
   public Key sample() {
     int r = 1 + StdRandom.uniform(N); // between 1 and N
     return a[r];
   }
   public Key delRandom() {
     int r = 1 + StdRandom.uniform(N); // between 1 and N
     Key key = a[r]; // save for return
     
     exch(r, N); // same way to delete min
     a[N] = null;
     N -= 1;
     sink(r); // if a[N] was too big
     swim(r); // if a[N] was too small
   
     return key;
   }
   ```

   