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

**Answer:** (Official) Yes, but it would cause all keys to hash to the same spot, which would lead to poor performance.

### B level

1. [Problem 2](https://d1b10bmlvqabco.cloudfront.net/attach/hx9h4t96ea8qv/h32s1vxe6mb5o0/i7vkubmrxjn0/fa14_mt2.pdf) of the Fall 2014 midterm.

   **Answer:** (Official)

   a. The `setVal` method will change the value, and the corresponding hash code doesn't update.

   b. It is still possible to change the value and hash code of an EfficientString after it has been added to the table, which can make it "disappear" from the table.

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

   **Answer:**

   ```java
   hashCode = (hashCode * R + x.get(i).hashCode()) % M;
   	= hashCode * R % M + x.get(i).hashCode() % M;
   	= hashCode * 31 % 31 + x.get(i).hashCode() % 31;
   	= x.get(i).hashCode() % 31;
   
   ```

   So the hashCode equals `x.get(x.length - 1).hashCode() % 31` , the `R` and rest of `i` are useless. 

2. If we start with a hash table of size 2 and double when the load factor exceeds some constant. Why is this procedure for setting sizes suboptimal from the perspective of utilizing all of the bits of the hashCode?

   **Answer:** So the size of hash table would all be some times of 2. That means all M will not be prime. "If M is not prime, it may be the case that not all of the bits of the key play a role."

3. [Adapted from textbook 3.4.23] Suppose that we hash strings as described in A-level problem 1, using R = 256 and M = 255. Show that any permutation of letters within a string hashes to the same value. Why is this a bad thing?

   **Answer**: Do the math. The hashCode would be sum of all the `x.get(i).hashCode()`, so permutation within a string hashes to the same value. This is a bad thing because a good  hash function should:

   *Uniformly distribute the keys.* 

4. (4) Find 2 strings in Java that hash to the same value (writing code is probably best).

   **Answer:**

   ```java
   public class ClassNameHere {
      public static void main(String[] args) {
       System.out.println("A:" + ((int)'A'));
       System.out.println("B:" + ((int)'B'));
       System.out.println("a:" + ((int)'a'));
   
       System.out.println("Aa".hashCode());
       System.out.println("BB".hashCode());
       System.out.println("Aa".hashCode());
       System.out.println("BB".hashCode());
   
   
       System.out.println("AaAa".hashCode());
       System.out.println("BBBB".hashCode());
       System.out.println("AaBB".hashCode());
       System.out.println("BBAa".hashCode());
      }
   }
   ```

   Result:

   ```
   A:65
   B:66
   a:97
   2112
   2112
   2112
   2112
   2031744
   2031744
   2031744
   2031744
   ```

   

5. [CS61B Fall 2009 midterm](http://inst.eecs.berkeley.edu/~cs61b/fa13/samples/test2.pdf), (midterm2) #4 (really beautiful problem)

   **ANswer:**

   a) Θ(N/(26*26)) = Θ(N)

   b) This is enough characters so that there will be at most a constant number of keys per
   bucket, since they are assumed to be divided evenly. On the other hand, it takes O(lg N )
   time to compute the hash function, so overall O(1 + lg N ) = O(lg N ).

   c) There is a sequence of N values that sorted exits, that everyone of value is added in O(1) time, and total time would be O(N). Say, the heap is a min-heap, and the values are sorted from small to big, no need to swim up after add new node. 

   d) 0

   Xoring 1 with any integer totally equals find the complement of that integer.

   Ref: https://stackoverflow.com/a/13422467

   ```java
   Bit#    Weight
   31      -2^31
   30       2^30
   29       2^29
   ...      ...
   2        2^2
   1        2^1
   0        2^0
   ```

   ```java
   Binary    Weighted sum            Integer value
   0000       0 + 0 + 0 + 0           0
   0001       0 + 0 + 0 + 2^0         1
   0010       0 + 0 + 2^1 + 0         2
   0011       0 + 0 + 2^1 + 2^0       3
   0100       0 + 2^2 + 0 + 0         4
   0101       0 + 2^2 + 0 + 2^0       5
   0110       0 + 2^2 + 2^1 + 0       6
   0111       0 + 2^2 + 2^1 + 2^0     7 -> the most positive value
   1000      -2^3 + 0 + 0 + 0        -8 -> the most negative value
   1001      -2^3 + 0 + 0 + 2^0      -7
   1010      -2^3 + 0 + 2^1 + 0      -6
   1011      -2^3 + 0 + 2^1 + 2^0    -5
   1100      -2^3 + 2^2 + 0 + 0      -4
   1101      -2^3 + 2^2 + 0 + 2^0    -3
   1110      -2^3 + 2^2 + 2^1 + 0    -2
   1111      -2^3 + 2^2 + 2^1 + 2^0  -1
   ```

6. Explan why the approach in A-level question 1 works better if we initially start the hashCode at 1 instead of 0.

   EDIT: This problem is broken. It’s not better. Thanks SF.
   
   **Answer:** the writer already answered it. Not better.

### A+ level

1. Give a simple procedure that can be carried out by hand that takes a Java string X and finds another Java string Y with the same hashCode().

   **Answer**: See A-level question 4.

   Ref: https://stackoverflow.com/a/12926279

   > see a test method, basically, so long as you match, a1*31+b1 = a2*31 +b2, which means (a1-a2)*31=b2-b1

   

   