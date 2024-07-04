# Lec 35: Radix Sorts Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec35/lec35

## Recommended Problems



### C level

1. For a fixed alphabet and key size, what are the best case and worst case inputs for LSD? For MSD?

   **ANswer:** LSD: best case and worst case is same : Θ(WN + WR). 

   MSD  : best case Θ(N + R), worst case  Θ(WN + WR). 

2. Question #6 from Princeton’s [Spring 2012 Final](http://www.cs.princeton.edu/courses/archive/spring15/cos226/exams/fin-s12.pdf)

   **ANswer:** rabid cable table fable sable cache ... hedge wedge ledge media medic

### B level

1. Adapted from Algorithms 5.1.8: Give the number of characters examined by MSD string sort for a file of N keys a, aa, aaa, aaaa, aaaaa, …

   W = N, R = 26.   N(N + 1) / 2

2. For very long random strings, would a comparison based sort or a radix sort be faster? For very long, highly similar string, which would be faster?

   **Conclusion:** GPT-4o:  For very long random strings, radix sort is generally faster due to its linear dependency on the length of the strings and the number of strings. It avoids the high overhead of repeated full-string comparisons required by comparison-based sorts.

   **Conclusion:** GPT-4o:  For very long, highly similar strings, comparison-based sorts are generally faster because they can exploit early termination in comparisons, leading to fewer overall character examinations and comparisons.

3. For very long sequences of Java ints, would radix sort or a comparison sort be faster?

   **ANswer:** For very long sequences of Java `int` values,  Radix sort provide predictable linear time complexity O(d⋅N), where d is the number of digits in the integer representation. Java uses a dual-pivot quicksort for primitive types like `int` in `Arrays.sort`, which is highly optimized and performs very well in practice. This sort generally provides excellent performance for large arrays of integers. While radix sort can theoretically outperform comparison sorts for large datasets of fixed-width integers, the actual performance gain might not be significant enough to justify using a custom implementation over Java's highly optimized `Arrays.sort`