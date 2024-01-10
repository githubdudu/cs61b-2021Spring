# Lists2 Study Guide - Exercises
## C Level
### 1
Complete the exercises from the online textbook.

**Exercise 2.2.1**: 

The curious reader might object and say that the IntList would be just as easy to use if we simply wrote an addFirst method. Try to write an addFirst method to the IntList class. You'll find that the resulting method is tricky as well as inefficient.

**Answer:**

See the code. It IS tricky as well as inefficient.

**Exercise 2.2.2** 

Delete the word static as few times as possible so that this program compiles (Refresh the page after clicking the link and making sure the url changed). Make sure to read the comments at the top before doing the exercise.

**Answer:**

See the code. Leave the code as it is. The answer will show up as long as try to compile the file.

**Exercise 2.2.3** 

Fix the addLast method. Starter code here.

**Answer:**
Add one more if condition.
See the code.

### 2
Reexplain what the sentinel node is and why it’s important. Ask yourself if your code would error if you removed the sentinel. Is the sentinel a necessary component of your IntList?

**Answer:**

It works like an observer and a pointer. The existence of sentinel make it possible that the ```SLList``` itself can be null. Hence, there is no special case in ```addLast()```, the data structure becomes more unified and code becomes more clean.

Like what in the course said, remove ```sentinel``` would cause ```null``` error in ```addLast()```. However, if we remove ```sentinel``` and clear all the bug, the code can have no error.

I assume the "my ```IntList```" refer to that in exercise 2.2.1. The sentinel is not a necessary component since ```this``` in ```this.rest``` will not be null like ```first``` in ```SLList```.  Unless ```IntList L = null```, but in this case, it is unreasonable to call ```L.addFirst()```. 

```java
public SLList() {
    first = null;
    size = 0;
}
```

### 3
What is the downside of not having a size variable and rather just calculate the size each time?

**Answer:**

Then we will get a very slow ```size()``` method for large list. It is the difference between O(1) and O(M).

## B Level
### 1
Starting from the copy of SLList.java provided to you in the lecture code repository, implement the method deleteFirst, which deletes the first element in your SLList. Don’t forget to maintain the three invariants discussed above.

**Answer:**

Code.

### 2
Starting from the copy of SLList.java provided to you in the lecture code repository, implement a second constructor that takes in an array of integers, and creates an SLList with those integers. Again, remember to maintain your invariants.

**Answer:**

Code.

### 3
If the sentinel node was a null node, would it change anything or would the Intlist be able to function?

**Answer:**

Quite not sure what this question is asking. It is the SSList has sentinel, or the IntNode connects with sentinel. 

If the question is asking about the SSList: It would change everything. The SLList would not be able to function.

## A level
### 1
Do problem 5 from midterm 1 in Kartik’s textbook

**Answer:**

### 2
Modify the Intlist class so that every time you add a value you “square” the IntList. For example, upon the insertion of 5, the below IntList would transform from:

1 => 2 to 1 => 1 => 2 => 4 => 5

and if 7 was added to the latter IntList, it would become

1 => 1 => 1 => 1 => 2 => 4 => 4 => 16 => 5 => 25 => 7

Additionally, you are provided the constraint that you can only access the size() function one time during the entire process of adding a node.

**Answer:**

