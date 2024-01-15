# Lec8: Inheritance1 Study Guide - Exercises A

https://sp21.datastructur.es/materials/lectures/lec8/lec8

## Exercises

### A Level

1. Problem 4 from [my Spring 2017 midterm 1](https://hkn.eecs.berkeley.edu/examfiles/cs61b_sp17_mt1.pdf#page=5).

   **a)**F1

   F1

   F1 F3

   

   **b)** F1 F2 F3 F4

   F1

   F2

   F3

   F4

   

   The argument `bird` can only fit in F1 or F3.  F1 and F3 together will be overriding and trigger dynamic type selection. So F3 will be executed. 

   The argument `falcon` can fit into these four methods. Call from instance of `Falcon` can call methods from inherited `Bird` if there is no corresponding method in `Falcon` . So for question **b**) , all four methods can be executed.

   