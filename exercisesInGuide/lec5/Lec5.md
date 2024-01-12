# Lec5: Lists3 Study Guide - Exercises C & B

Ref: https://sp21.datastructur.es/materials/lectures/lec5/lec5

### C Level

1. Complete the exercises from the online textbook [here](https://joshhug.gitbooks.io/hug61b/content/chap2/chap23.html) and [here](https://joshhug.gitbooks.io/hug61b/content/chap2/chap24.html).

   **Exercise 2.3.1:** Consider the box and pointer diagram representing the `SLList` implementation above, which includes the last pointer. Suppose that we'd like to support `addLast`, `getLast`, and `removeLast` operations. Will the structure shown support rapid `addLast`, `getLast`, and `removeLast` operations? If not, which operations are slow?

   ![sllist_last_pointer.png](assets\sllist_last_pointer.png)

   

   **Answer 2.3.1:** `addLast` and `getLast` will be fast, but `removeLast` will be slow. That's because we have no easy way to get the second-to-last node, to update the `last` pointer, after removing the last node.

   > This is the answer from online textbook

     

   **Exercise 2.3.2:** Try to devise a scheme for speeding up the `removeLast` operation so that it always runs in constant time, no matter how long the list. Don't worry about actually coding up a solution, we'll leave that to project 1. Just come up with an idea about how you'd modify the structure of the list (i.e. the instance variables).

   **Answer 2.3.2: ** Obvious answer: add backwards link for each node.

     

   **Exercise 2.4.1:** After running the code below, what will be the values of x\[0]\[0] and w\[0]\[0]? Check your work by clicking [here](http://goo.gl/fCZ9Dr).

   ```java
   int[][] x = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
   
   int[][] z = new int[3][];
   z[0] = x[0];
   z[1] = x[1];
   z[2] = x[2];
   z[0][0] = -z[0][0];
   
   int[][] w = new int[3][3];
   System.arraycopy(x[0], 0, w[0], 0, 3);
   System.arraycopy(x[1], 0, w[1], 0, 3);
   System.arraycopy(x[2], 0, w[2], 0, 3);
   w[0][0] = -w[0][0];
   ```
   **Answer 2.4.1: **

   -1  
   1

2. Can you create a 2 dimensional array with different types? For example, one sub array would be composed of all Strings and another sub array would be made of only ints.

   **Answer:** 

   We can't. The definition of array is (from https://dev.java/learn/language-basics/arrays/#intro):

   > An *array* is a container object that holds a fixed number of values of a **single** type.

   But we can utilize some trick:

   ```java
   public class Try {
   
       public static void main(String[] args) {
           int[][] x = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
   
           Object[][] z = new Object[3][];
           z[0] = new Integer[3];
           z[1] = new String[3];
       }
   }
   ```

   It works. But it is not what the question is asking. After all, here the Integers and Strings are considered as same type: Objects.

### B Level

1. At each step follow the instructions

   ```java 
    public class Deck{
        public static  int[] cards;
        Deck(){
            cards = [1, 3, 4, 10];
        }
    }
   ```

   Write down the contents of dingie’s array cards.

   ```java
      Deck dingie = new Deck(); // [1, 3, 4, 10]
      dingie.cards[3] = 3; // [1, 3, 4, 3]
   ```

   Write the contents of pilates’s array and dingie’s array.

   ```java
      Deck pilates = new Deck(); //p-[1, 3, 4, 10] d-[1, 3, 4, 10]
      pilates.cards[1] = 2; //p-[1, 2, 4, 10] d-[1, 2, 4, 10]
   ```

   Write the contents of pilates’s array and dingie’s array.

   ```java
      int[] newArrWhoDis = {2, 2, 4, 1, 3};
      dingie.cards = pilates.cards;//p-[1, 2, 4, 10] d-[1, 2, 4, 10]
      pilates.cards = newArrWhoDis; //p-{2, 2, 4, 1, 3} d-{2, 2, 4, 1, 3}
      newArrWhoDis = null; // p-{2, 2, 4, 1, 3} d-{2, 2, 4, 1, 3}
   ```
   **Answer:** In the comments.
2. Say we have a 2 dimensional DList. We want this 2-D DList to be as even as possible. To do this we will try to fill up rows as uniformly as possible- meaning that not row will have a greater size than any other row by more than 1 element. Write a method that will take in a sub DList and add the given element if it fulfills the constraints. If the constraints are not fulfilled, the item will be attempted to be put in the DList below the one you attempted to insert in originally and so forth until the bottom most DList is reached in which case move to the top DList.

   **Answer:** 

   Try writing DList. Using 2D array and only ints. Key part is how to use array and arraycopy() and proper methods to break down the big problem to smaller ones. 

   See code repo.

### A level

1. Complete problem 10 from practice midterm 1 in Kartik’s [textbook](http://www.kartikkapur.com/documents/mt1.pdf#page=10)

   **Answer:** coding

   ==There is a critical typo in question code, line 12.==

   ==It should be ```width```, not ```p.length```.==

   This would be a great misleading if there is ```p.length```. 

   For **part c**, it is really hard to know what the question giver wants and his meaning. 
   From the solution pdf we can know that, he wants us to use `arraycopy()` to deal with result of `sortHalfLong()`.
   I couldn't figure it out. I thought should this be used to implement a merge sort with some trick or something? I nearly wrote a sort method myself. 

2. Complete problem 7 from midterm 1 from Spring 2015 [here](https://tbp.berkeley.edu/exams/4695/download/#page=9)

   **Answer:** coding

