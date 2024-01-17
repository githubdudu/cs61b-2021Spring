# Lec11: Exceptions, Iterators, Iterables - Exercise

Ref: https://sp21.datastructur.es/materials/lectures/lec11/lec11

## Past Exam Questions

[Spring2018MT2Q7](https://tbp.berkeley.edu/exams/6137/download/#page=8)

a)

```java
public class IterableUtils {
	public static <T> List<T> toList(Iterable<T> iterable) {
		List<T> list = new ArrayList<T>();
        for (T t: iterable) {
 			if (t == null) {
                throw new IllegalAugumentException();
			 }
 			list.add(t);
 		}
 		return list;
	}
} // assume any classes you need from java.util have been imported
```

b)

```java
public class TestRODI() {
    @Test
    public void testRODI() {
        ReverseOddDigitIterator odi = new ReverseOddDigitIterator(12345770);
        List<int> reversedList = IterableUtils.toList(odi);
        List<int> expectedList = List.of(7,7,5,3,1);
        assertArrayEquals(reversedList, expectedList);
    }
}
```

c)

```java
public class ReverseOddDigitIterator implements Iterable<Integer>,
    Iterator<Integer> {
    private int value;
    public ReverseOddDigitIterator(int v) {
        value = v;
    }
    public boolean hasNext() {
        if (value == 0) {
        	return false; 				// hint: this class should
        } 								// be implemented
        if (value % 2 != 0) {  			// so that the example
    	   	return true; 				// code that prints
        } else { 						// 77531 on the previous
            value = value / 10; 		// page works.
            return hasNext();
        }
    }
    public Integer next() {
        int d = value % 10;
        value = value / 10;
        return d;
    }
	public Iterator<Integer> iterator() {
		return this;
	}
} // assume any classes you need from java.util have been imported
```



d)
TestRODI (Iterable interface is used and needed with other class)
ReverseOddDigitIterator (iterator() need it to have Iterator interface)
ReverseOddDigitIterator (declared implements Iterator, hasNext() method must be implemented)
ReverseOddDigitIterator (declared implements Iterable, iterator() method must be implemented)

