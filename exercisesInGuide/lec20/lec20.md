# Lec20: Hashing Study Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec20/lec20

## Recommended Problems

## Example Implementations

[External Chaining HT](http://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html)

[Linear Probing HT](http://algs4.cs.princeton.edu/34hash/LinearProbingHashST.java.html)

### C level

1. [Adapted from Textbook 3.4.5] Is the following implementation of hashCode() legal?

   ```
    public int hashCode() {
        return 17;
    }
   ```

If so, describe the effect of using it. If not, explain why.

### B level

1. [Problem 2](https://d1b10bmlvqabco.cloudfront.net/attach/hx9h4t96ea8qv/h32s1vxe6mb5o0/i7vkubmrxjn0/fa14_mt2.pdf) of the Fall 2014 midterm.

### A level

1. One strategy discussed in class for hashing objects containing multiple pieces of data is as follows (in pseudocode), where `x.get(i)` returns the i-th piece of data in `x`. See Code 1.

   ```
   int hashCode = 0;
    for (int i = 0; i < x.length; i += 1) {
       hashCode *= R;
       hashCode += x.get(i).hashCode();
       hashCode = hashCode % M;
    }
    return hashCode;
   ```

   Effectively, we’re summing each piece of data multiplied by a different power of R. In class, we used R = 31, and M is the number of buckets. Explain why the idea above does not work well if M = 31 (or some power of 31).

   EDIT: The problem above is kind of weird. Fix later. (It’s very strange to mod inside of a hashcode function!)

2. If we start with a hash table of size 2 and double when the load factor exceeds some constant. Why is this procedure for setting sizes suboptimal from the perspective of utilizing all of the bits of the hashCode?

3. [Adapted from textbook 3.4.23] Suppose that we hash strings as described in A-level problem 1, using R = 256 and M = 255. Show that any permutation of letters within a string hashes to the same value. Why is this a bad thing?

4. Find 2 strings in Java that hash to the same value (writing code is probably best).

5. [CS61B Fall 2009 midterm](http://inst.eecs.berkeley.edu/~cs61b/fa13/samples/test2.pdf), #4 (really beautiful problem)

6. Explan why the approach in A-level question 1 works better if we initially start the hashCode at 1 instead of 0.

   EDIT: This problem is broken. It’s not better. Thanks SF.

### A+ level

1. Give a simple procedure that can be carried out by hand that takes a Java string X and finds another Java string Y with the same hashCode().