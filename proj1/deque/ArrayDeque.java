package deque;


public class ArrayDeque<T> {

    private int size;
    private T[] array;
    private int headFlag;
    private int tailFlag;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(T item) {
        array = (T[]) new Object[8];
        array[0] = item;
        size = size + 1;
    }

    /* Return true if array is full, false otherwise. */
    private boolean arrayIsFull() {
        return size == array.length;
    }

    /* Resize the length of array */
    private void resize(int newLen) {
        T[] newarray = (T[]) new Object[newLen];
        int len = array.length;

        if (headFlag > tailFlag) {
            /* only when shrunk. When expand, headFlag == tailFlag */
            System.arraycopy(array, headFlag, newarray, 0, len);
            headFlag = 0;
            tailFlag = size;
        } else {
            System.arraycopy(array, 0, newarray, 0, tailFlag);
            System.arraycopy(array, headFlag, newarray, len + headFlag, len - headFlag);
            headFlag = len + headFlag;
        }

        array = newarray;
    }

    /* expand the array as double if array is full */
    private void expandIfNeeded() {
        if (arrayIsFull()) {
            int len = array.length;
            resize(len * 2);

        }
    }

    private void shrinkIfNeeded() {

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
    public void addFirst(T item) {
        expandIfNeeded();

        /* Move before assign */
        headFlag = moveLeft(headFlag);
        array[headFlag] = item;

        size = size + 1;
    }

    // Adds an item of type T to the back of the deque. You can assume that item is never null.
    public void addLast(T item) {
        expandIfNeeded();

        /* AddLast is different. Assign should before move. */
        array[tailFlag] = item;
        tailFlag = moveRight(tailFlag);

        size = size + 1;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        if (!isEmpty()) {

            for (int i = 0; i < size; i += 1) {
                System.out.print(array[realIndex(i)]);
            }

            System.out.println();
        }
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
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
    public T get(int index) {
        return array[realIndex(index)];
    }

    public int realIndex(int index) {
        int realID = index + headFlag;
        if (realID < array.length) {
            return realID;
        } else {
            return realID - array.length;
        }
    }

}
