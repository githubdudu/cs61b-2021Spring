# Lec13: Asymptotics I Study Guide - Exercises 

Ref: https://sp21.datastructur.es/materials/lectures/lec13/lec13

## Exercises

### B level

> *The answer is just on the same page which printed as white color.*

1. Suppose we have a function`bleepBlorp`, and its runtime

     

   R(N)

    

   has order of growth

    

   Θ(N2)

   . Which of the following can we say?

   - R(N)∈Θ(N2)) 

     - > true, this is what order of growth means!

   - R(N)∈Θ(N2) for any inputs 

     - > true, this statement is exactly the same as the one above

   - R(N)∈Θ(N2) for worst case inputs 

     - > true, since also true for ANY input

   - For large N, if we run bleepBlorp on an input of size N, and an input of size 10N, we will have to wait roughly 100 times as long for the larger input. 

     - > true, this is the nature of quadratics

   - If we run bleepBlorp on an input of size 1000, and an input of size 10000, we will have to wait roughly 100 times as long for the larger input.

     - > false, 1000 may not be a large enough N to exhibit quadratic behavior