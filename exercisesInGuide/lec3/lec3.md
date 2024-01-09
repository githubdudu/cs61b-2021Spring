# Lec3: Lists1 Study Guide - Exercises
Ref: https://sp21.datastructur.es/materials/lectures/lec3/lec3.html

## C Level
### 1

Complete the exercises from the online textbook.  

Exercise 2.1.1: Suppose we have the code below:
```shell
public class PassByValueFigure {
    public static void main(String[] args) {
           Walrus walrus = new Walrus(3500, 10.5);
           int x = 9;

           doStuff(walrus, x);
           System.out.println(walrus);
           System.out.println(x);
    }

    public static void doStuff(Walrus W, int x) {
           W.weight = W.weight - 100;
           x = x - 5;
    }
}
```
Does the call to doStuff have an effect on walrus and/or x? Hint: We only need to know the GRoE to solve this problem.

**Answer:**  
```walrus.weight = 3400``` changed  
```x = 9``` unaffected

### 2
If doubles are more versatile than ints, why don’t we always use them? Are there any disadvantages to doing this?  

**Answer:**  
Disadvantages:
1. Use as double memory as ints.
2. Use more computational resources.
3. May cause inaccurate math answer when representing ints.

### 3
How much does the memory cost differ between the storing of an address of a 32 entry int array and a 300 entry int array?

**Answer:**  
If we are talking about the storing of an address, most of the time they are same.  
The address maybe 64 bits or 32 bits in a device, every address may use same memory.
If the system apply a strategy that storing a shorter/ smaller address with less memory,
it might be different. Anyway, it has nothing to do with the length/ entry of
the array.

## B Level
### 1
Rewrite the size, iterativeSize, and get methods from lecture by using this the starter code for the IntList class.

**Answer:**  
Just tricks of iterative and recursive, no big deal. These two can be transformed mutually.  
See the code.

### 2
Write methods incrList and dincrList as described in Lists1Exercises. If your solution uses size, iterativeSize, or get, you’ll need to complete the previous exercise first.

**Answer:**  
Implementing first method by using ```new``` keyword. The second bans ```new```, so I changed the original ```L```.  
See the code.
