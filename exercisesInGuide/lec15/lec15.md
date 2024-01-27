# Lec15: Asymptotics II Study Guide - Exercises

Ref: https://sp21.datastructur.es/materials/lectures/lec15/lec15

## Recommended Problems

### C level

1. Prove that O(N+N2+N4+….2+1)=O(N) (hand wavy proof is okay as long as you gain the intuition)

2. What would the runtime of `modified_fib` be. Assume that values is an array of size n. If a value in an int array is not initialized to a number, it is automatically set to 0.

   ```
    public void modified_fib(int n, int[] values){
      if(n <= 1){
        values[n] = n;
        return n;
      }
      else{
        int val = values[n];
        if(val == 0){
          val = modified_fib(n-1, values) + modified_fib(n-2, values);
          values[n] = val;
        }
        return val;
      }
    }  
   ```

3. Prove to yourself that Θ(log2(n))=Θ(log3(n))

### B level

1. Find the runtime of running print_fib with for arbitrary large n.

   ```java
    public void print_fib(int n){
      for(int i = 0; i < n; i++i){
          System.out.println(fib(i));
      }
    }
   
    public int fib(int n){
      if(n <= 0){
        return 0;
      }
      elif(n == 1){
        return 1;
      }
      else{
        return fib(n-1) + fib(n-2);
      }
    }
   ```

2. Do problem 5 again, but change the body of the for loop in `print_fib` to be

   ```
    System.out.println(fib(n));
   ```

3. Find the runtime of this function

   ```java
    public void melo(int N){
      for(int i = 0; i < N*N; i++){
        System.out.println("Gelo is fruit pudding");
      }
      for(int i = 0; i < N*N*N; i++){
        System.out.println("Zo Two the Warriors");
      }
    }
   ```

4. Find the runtime of this function

   ```java
    public void grigobreath(int N){
        if(N==0){
          return;
        }
        for(int i  = 0; i < N; i++){
          System.out.println("Gul-great")
        }
        grigobreath(N * 1/2);
        grigobreath(N * 1/4);
        grigobreath(N * 1/4);
    }
   ```

5. [Problem 8](https://tbp.berkeley.edu/exams/6137/download/) from Spring 2018 midterm #2

6. [Problem 4](https://tbp.berkeley.edu/exams/5773/download/) from Spring 2017 midterm #2