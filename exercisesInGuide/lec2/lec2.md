https://sp21.datastructur.es/materials/lectures/lec2/lec2.html

# Intro2 Study Guide Exercises
## C Level
### 1
**Exercise 1.2.1**: What would the following method do? If you're not sure, try it out.

```java
public static Dog maxDog(Dog d1, Dog d2) {
    if (weightInPounds > d2.weightInPounds) {
    return this;
    }
    return d2;
}
```
Solution:  
Here is the command line info when try "javac dog.java".
```shell
$ javac dog.java
>>dog.java:19: error: non-static variable weightInPounds cannot be referenced from a static context
if (weightInPounds > d2.weightInPounds) {
    ^                                    
dog.java:20: error: non-static variable this cannot be referenced from a static context
return this;
       ^
2 errors
```
<br/><br/>
**Exercise 1.2.2**: Complete this exercise:

Question: Will this program compile? If so, what will it print?
```java
public class DogLoop {
    public static void main(String[] args) {
        Dog smallDog = new Dog(5);
        Dog mediumDog = new Dog(25);
        Dog hugeDog = new Dog(150);
 
        Dog[] manyDogs = new Dog[4];  
        manyDogs[0] = smallDog;
        manyDogs[1] = hugeDog;    	   
        manyDogs[2] = new Dog(130);
 
        int i = 0;
        while (i < manyDogs.length) {
            Dog.maxDog(manyDogs[i], mediumDog).makeNoise();
            i = i + 1;
        }
    }
}
```
Solution:  
Compile successfully with ```javac dog.java dogloop.java```.  
Run with error.
```shell
$ java DogLoop
>>bark. bark.
woof!
woof!
Exception in thread "main" java.lang.NullPointerException: Cannot read field "weightInPounds" because "<parameter1>" is null
        at Dog.maxDog(dog.java:19)
        at DogLoop.main(dogloop.java:14)

```
<br/>

**Exercise 1.2.3** : Try to write a program that sums up the command line arguments, 
assuming they are numbers. For a solution, see the webcast or the code provided on GitHub.

See the corresponding code in directory.

### 2 
In the below code what would the blank variable name have to be in order for the code to compile
```java 
 public class Human{
   int __________;
   public Human(int ________){
     eyes = ___________;
   }
 }
```
Solution:
```java 
 public class Human{
   int eyes;
   public Human(int initEyes){
     eyes = initEyes;
   }
 }
```
## B Level
### 1
Below is the Dog class.
```java
public class Dog{
    public void bark(){
        System.out.println("Moo");
    }
    public static void runFast(){
        System.out.println("Ruff Run");
    }
}
```
Which of the following lines, if any, would cause an error.
```java
Dog poppa = new Dog();
poppa.bark();
Dog.bark(); // error
poppa.runFast();
Dog.runFast();
```
Solution:  
Only an instance of a class can call not-static methods.  
Both class and instances can call static methods.
```shell
$ javac DogLauncher.java
>>doglauncher.java:5: error: non-static method bark() cannot be referenced from a static context
        Dog.bark();
           ^
1 error
```
Comment the error line, then compile and run, the command line will print:
```shell
$java DogLauncher
>>Moo
Ruff Run
Ruff Run
```