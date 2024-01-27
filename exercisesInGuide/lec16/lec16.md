# Lec16: ADTs and BST Study Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec16/lec16

## Recommended Problems

### C level

1. What is the best case BST height in terms of N? Worst case?

   **answer:**  log(N) ; N

2. If shuffling yields logN tree height (with extremely high probability), why don’t we simply shuffle our input data before building our BST based symbol table to avoid worst case behavior?

   **answer:**  

   Ref: Textbook 3.2 page414

   "With quicksort, we were able to randomize; with a symbol-table API, we do not have that freedom, because the client controls the mix of operations."

   Also, the worst case behavior is rare. 

3. [Adapted from Textbook 3.2.3] Give two orderings of the keys `A X C S E R H` that, when inserted into an empty BST, yield the best case height. Draw the tree.

   **answer:** H C S A E R X or H C A E S R X

   ![1706164206806](G:\code\cs61b\skeleton-sp21\exercisesInGuide\lec16\assets\1706164206806.png)

4. Delete the root from the tree you created for question C-3.

   **answer:** One of answer is: 

   ![1706164279715](G:\code\cs61b\skeleton-sp21\exercisesInGuide\lec16\assets\1706164279715.png)

### B level

1. [Adapted from Textbook 3.2.4] Suppose that a certain BST has keys that are integers between 1 and 10, and we search for 5. Which sequence(s) of keys below are possible during the search for 5?

   a. 10, 9, 8, 7, 6, 5

   b. 4, 10, 8, 7, 53

   c. 1, 10, 2, 9, 3, 8, 4, 7, 6, 5

   d. 2, 7, 3, 8, 4, 5

   e. 1, 2, 10, 4, 8, 5

   **answer: **a, c, e are possible.

2. Give an example of something that you might like to store as a key in a symbol table for which there is no natural compare method.

   **answer:**

   The websites addresses (string) and their IP addresses (a serial of numbers).

3. Do there exist any objects for which it is impossible to define a total order? In other words, can we always write a compare method if we’re willing to do the work, or are some data types fundamentally impossible to compare other than for equality?

   **answer:**

   Not all data types lead to key values that are easy to compare, even though having a
   symbol table still might make sense. To take an extreme example, you may wish to use
   pictures or songs as keys. There is no natural way to compare them, but we can certainly
   test equality (with some work). 

4. When we delete from a BST, we always chose either the predecessor or successor as a replacement for the root. We said that these two items always have zero or one children. Why?

   **answer:**

   If they have two children, then one of children must be more near the root to be the real predecessor or successor.

5. Is the delete operation commutative? In other words, if we delete x, then y, do we always get the same tree as if we delete y, then x?

   **answer:**

   It is not commutative.  (Ref: https://stackoverflow.com/questions/2990486/deletion-procedure-for-a-binary-search-tree)

   ```
       4
      / \
     3   7
        /
       6
   ```

   Delete 4, then delete 3:

   ```
      6     6
     / \     \
    3   7	   7
   ```

   Delete 3, then delete 4:

   ```
    4         7
     \       /
      7     6
     /
    6
   ```

6. [Problem 1](https://d1b10bmlvqabco.cloudfront.net/attach/hx9h4t96ea8qv/h32s1vxe6mb5o0/i7vkubmrxjn0/fa14_mt2.pdf) from the Fall 2014 midterm.

   **Answer:**  "Has either zero or two children." Depth of tree is roughly N/2, so worst-case search time is Θ(N/2) = Θ(N).

   ```
   1     1      1      1
   	 / \    / \    / \
   	2   3  2   3  2   3
   	           /\     /\
   	          4  5   4  5
   	                    /\
   	                   6  7 
   ```

   

7. [Problem 1](https://tbp.berkeley.edu/exams/6137/download/) from Spring 2018 midterm #2. Skip parts c and d for now.

   **Answer: ** Apparently, these exercises have been copied from somewhere or just old-version of previous courses and have not been updated for 2021 spring. The part b (rotating a tree) is for next lecture. 

   part a: 

   ```
            8
            /\
           5 12
          /  /
         1  10
          \   \
           3   11
          /
         2
   ```

   Part b: `rotateLeft(7)`.

### A+ level

1. [Problem 3](http://inst.eecs.berkeley.edu/~cs61b/fa14/samples/2009/test2.pdf) from the Fall 2009 midterm. This problem is magnificent in its elegance and difficulty.

   **Answer**: As one of the merge operation in `mergeSort`, it will take linear time in the total number of items in T and L when we using `inorder traversal` .

   1. Perform an inorder traversal on the first BST and store the resulting elements in a list or an array. 
   2. Perform an in-order traversal on the second BST and append the elements to the existing list or array. 
   3. Return the root node of the first BST.

   But this way has memory use for the array of storing elements. If there are millions notes in the tree, the cost could be unacceptable. I'll try use in-order traversal with a lesser array memory use (`logM`).

   See code `IntTree.java`.

