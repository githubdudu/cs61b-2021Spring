# Lec22: Tries Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec22/lec22

## Recommended Problems

Throughout, we define R as the size of the alphabet, and N as the number of items in a trie.

### C level

1. Problem 5 from [Princeton’s Spring 2008 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-s08.pdf).
2. Problem 8 from [Princeton’s Spring 2011 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f11.pdf).
3. Problem 8 from [Princeton’s Spring 2012 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f12.pdf).
4. Draw the R-way trie that result after inserting the strings: sam, sad, sap, same, a, awls.

### B level

1. When looking for a single character string in a Trie, what is the worst case time to find that string in terms of R and N?
2. Problem 5 from [Princeton’s Fall 2009 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-f09.pdf).
3. Problem 9 from [Princeton’s Fall 2012 final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-s12.pdf).
4. Problem 1 from [my Fall 2013 final](http://www.cs.princeton.edu/courses/archive/fall13/cos226/exams/fin-f13.pdf).
5. True or false: The number of character compares required to construct an R-way trie is always less than or equal to the number required to construct an LLRB.
6. True or false: The number of character compares required to construct an R-way trie is always less than or equal to the number of character accesses needed to construct a hash table.

### A level

1. There are many different data structures that we can use to store the children of an R-way trie node. The simplest but most memory hungry choice is to create an array of length R, with one link per character in the trie. A second choice is to have each node contain a TreeMap that maps a given character to the appropriate node. A third choice is to use HashMaps instead of TreeMaps. is to have each node contain a TreeMaps, some of you used R-way trie nodes that contained HashMaps. For the “average” node, which of these three choices is likely to use the most memory? Why does this mean that this type of node takes longer to construct?
2. Suppose we use an R-way trie to implement a set. For each of the three implementations of an R-way trie node described in problem 2, what are the best and worst case key lookup times in terms of R an N?
3. Problem 9 from [my Fall 2014 midterm 2](http://datastructur.es/sp16/materials/exam/CS61B_Fall2014_MT2.pdf).