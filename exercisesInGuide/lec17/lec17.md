# Lec17: B-Trees Study Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec17/lec17

## Practice Problems

1. Draw the 2-3 tree that results when you insert the keys A B C D E F G in order.

   **answer:** 

   ![1706345121399](G:\code\cs61b\skeleton-sp21\exercisesInGuide\lec17\assets\1706345121399.png)

2. How many compares does it take in the worst case to decide whether to go left, middle, or right from a 3 node?

   **Answer:** Two

   

3. [Problem 5](https://d1b10bmlvqabco.cloudfront.net/attach/hx9h4t96ea8qv/h32s1vxe6mb5o0/i7vkubmrxjn0/fa14_mt2.pdf) of the Fall 2014 midterm.

   **Answer:** 

   ```java
   class InnerNode2_4 extends Node2_4{
       @override
       boolean contains(String key) {
           for (int i = 0; i < arity() - 1; i += 1) {
               if(key(i).equals(key)) {
                   return true;
               } else if(key.compareTo(key(i)) < 0) {
                   return kid(i).contains(key);
               }
           }
           return kid(arity() - 1).contains(key);
       }
   }
   ```

   The solution use of `size()` instead of `arity()` is wrong.

4. [Problem 1c, e](https://tbp.berkeley.edu/exams/6137/download/) of the Spring 2018 Midterm 2

   **Answer:**

   c) 

   ```
     2,7
    / |   \
   1  3,5  8,9
   
   ```

   e)

   ```
      5
     /  \
    2    8
   / \  / \
   1 3  7 9
   ```

   

5. [Problem 8b](https://tbp.berkeley.edu/exams/5286/download/) of the Spring 2016 Midterm 2

   `1,2,3,4,5,6,7,8,9`