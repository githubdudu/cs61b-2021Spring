https://sp21.datastructur.es/materials/lectures/lec7/lec7

# Lec7: Testing Study Guide

## C Level  
### 1
In general, is it good to write tests that test your entire program? How about for specific functions?

Answer:  
What the question is really asking about is that the pros and cons of Integrated Testing and Unit Testing.  
Of course it is good to write tests that test the whole program to basically know it works well.  
Unit testing helps us in developing which firstly we make sure many fundamental parts run well and then we
glue them together. In the course, Joshua Hug mentioned an interesting perspective that many programmers
like unit testing because it may like solving many mini-game, and it is addictive.

Cons:
Both these are time-consuming, and may sometimes be tedious.  
The difficulty of writing solid and reliable unit tests and integrated tests.

## B Level
### 1
Write a testing method that will take in 2 arrays and see if they are equal. These arrays can have nested arrays and those nested arrays can have nested arrays and so forth.

Answer:   
```org.junit.Assert.assertArrayEquals``` is what we want.  
See the corresponding code. The code shows that this methods can cover every cases.
### 2
If we have 2 objects, Object o1 and Object o2, that have identical qualities, will assertEquals(o1, o2) assert true or false?

Answer:  It will throw an error which means they are not equal. Like the golden rule said, the equality is verified by value that is physical address which is not equal.