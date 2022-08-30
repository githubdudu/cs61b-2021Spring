package deque;


import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T>  {

    private int size;
    private T[] array;
    private int headFlag;
    private int tailFlag;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
    }

    /* Return true if array is full, false otherwise. */
    private boolean arrayIsFull() {
        return size == array.length;
    }

    /* Return true if array usage is below 25%, false otherwise. */
    private boolean arrayIsSparse() {
        return (array.length >= 16) && ((float) size / array.length) < 0.25;
    }

    /* Resize the length of array */
    private void resize(int newLen) {
        T[] newarray = (T[]) new Object[newLen];
        int len = array.length;

        if (headFlag < tailFlag || size == 0) {
            /* when headFlag is before tailFlag, or empty */
            int newHead = 0;
            int newTail = size;
            System.arraycopy(array, headFlag, newarray, newHead, size);
            headFlag = newHead;
            tailFlag = newTail;
        } else {
            int newHead = newLen - len + headFlag;
            System.arraycopy(array, 0, newarray, 0, tailFlag);
            System.arraycopy(array, headFlag, newarray, newHead, len - headFlag);
            headFlag = newHead;
        }

        array = newarray;
    }

    /* expand the array as double if array is full */
    private void expandIfNeeded() {
        if (arrayIsFull()) {
            int len = array.length * 2;
            resize(len);
        }
    }

    private void shrinkIfNeeded() { //TODO
        if (arrayIsSparse()) {
            int len = array.length / 2;
            resize(len);
        }
    }

    /* Move head and tail */
    private int moveLeft(int pointer) {

        if (pointer == 0) {
            pointer = array.length - 1;
        } else {
            pointer = pointer - 1;
        }
        return pointer;
    }

    /* Move head and tail */
    private int moveRight(int pointer) {

        if (pointer == array.length - 1) {
            pointer = 0;
        } else {
            pointer = pointer + 1;
        }
        return pointer;
    }

    // Adds an item of type T to the front of the deque. You can assume that item is never null.
    @Override
    public void addFirst(T item) {
        expandIfNeeded();

        /* Move before assign */
        headFlag = moveLeft(headFlag);
        array[headFlag] = item;

        size = size + 1;
    }

    // Adds an item of type T to the back of the deque. You can assume that item is never null.
    @Override
    public void addLast(T item) {
        expandIfNeeded();

        /* AddLast is different. Assign should before move. */
        array[tailFlag] = item;
        tailFlag = moveRight(tailFlag);

        size = size + 1;
    }

    // Returns the number of items in the deque.
    @Override
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        if (!isEmpty()) {

            for (int i = 0; i < size; i += 1) {
                System.out.print(array[realIndex(i)]);
            }

            System.out.println();
        }
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T removed = array[headFlag];
        array[headFlag] = null;

        headFlag = moveRight(headFlag);
        size = size - 1;
        shrinkIfNeeded();

        return removed;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        tailFlag = moveLeft(tailFlag);
        T removed = array[tailFlag];
        array[tailFlag] = null;

        size = size - 1;
        shrinkIfNeeded();

        return removed;
    }

    /*
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (index > array.length) {
            return null;
        }
        return array[realIndex(index)];
    }

    private int realIndex(int index) {
        int realID = index + headFlag;
        if (realID < array.length) {
            return realID;
        } else {
            return realID - array.length;
        }
    }

    /* Return an iterator */
    public Iterator<T> iterator() {
        return new ArrayDeque.ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        ArrayDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T p = get(pos);
            pos = pos + 1;
            return p;
        }
    }

    /* Returns whether the parameter o is equal to the Deque. */
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }

        Deque other = (Deque) o;

        if (size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size(); i += 1) {
            if (!get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
