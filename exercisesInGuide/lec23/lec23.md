# Lec23: Trees and Graph Traversals Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec23/lec23

## Recommended Problems

### C level

1. [Question 1](http://inst.eecs.berkeley.edu/~cs61b/fa14/ta-materials/discussion7.pdf) from the Fall 2014 discussion worksheet.

   1 **Binary Tree Traversals** 

   Write the preorder, inorder, and postorder traversals of the following binary tree. 

   ```
       10 
      / \ 
     2   3 
    / \   \ 
   5   7   0 
        \   \
         8   2 
   ```

   Bonus questions: What is the height of a binary tree with N nodes that has the same preorder and inorder traversal? Is this also true for non-binary trees?

   **Answer: **

   Preorder: 10 2 5 7 8 3 0 2

   Inorder: 5 2 7 8 10 3 0 2

   Postorder:  5 8 7 2 2 0 3 10

   If the preorder and the inorder traversal are the same, this means that all nodes have only right children. The second question is a trick question: Inorder traversal is not well defined for non-binary trees.

### B level

1. [Question 4](https://d1b10bmlvqabco.cloudfront.net/attach/hx9h4t96ea8qv/h32s1vxe6mb5o0/i7vkubmrxjn0/fa14_mt2.pdf) from the Fall 2014 midterm 2.

   **Answer**:

   Since the height of an empty tree is -1 and that of a tree with one node is 0, the possible diameter that contains the root is height of left branch + height of right branch + 2;
   
   ```java
   /** Return the diameter of T. */
   public static int diameter(BinTree T){
     if (height(T) <= 1){
   		return 0;
     } else {
       int possibleDiameter = 2 + height(T.left) + height(T.right);
       int maxChildrenDiameter = Math.max(diameter(T.left), diameter(T.right));
       return Math.max(possibleDiameter, maxChildrenDiameter);
     }
   }
   ```
   
    

### A level

1. [Question 7](http://datastructur.es/sp15/materials/guerrilla/guerrilla2.pdf) from the guerrilla section worksheet #2 from Spring 2015.

   **Answer:** (a)

   ```
          C
         / \
         T  A
        /\  /\
       U S Z O
      /\
     W X
   ```

   (b)  `W U X T S C Z A O`