package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class DLNode {
        private T item;
        private DLNode prev;
        private DLNode next;

        private DLNode(T item) {
            this.item = item;
        }

        private DLNode(T item, DLNode prev, DLNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private DLNode sentinel;

    public LinkedListDeque() {
        sentinel = new DLNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // Adds an item of type T to the front of the deque. You can assume that item is never null.
    @Override
    public void addFirst(T item) {
        DLNode added = new DLNode(item, sentinel, sentinel.next);

        added.prev.next = added;
        added.next.prev = added;
        size = size + 1;
    }

    // Adds an item of type T to the back of the deque. You can assume that item is never null.
    @Override
    public void addLast(T item) {
        DLNode added = new DLNode(item, sentinel.prev, sentinel);

        added.prev.next = added;
        added.next.prev = added;
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
        DLNode pointer = sentinel.next;
        while (pointer != sentinel) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        DLNode rmv = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return rmv.item;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        DLNode rmv = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size - 1;
        return rmv.item;
    }

    /*
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        }

        DLNode pointer = sentinel.next;
        while (index != 0) {
            pointer = pointer.next;
            index = index - 1;
        }
        return pointer.item;
    }

    /*
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, DLNode node) {
        if (index > size) {
            return null;
        }
        if (index == 0) {
            return node.item;
        } else {
            return getRecursive(index - 1, node.next);
        }
    }

    /* Return an iterator */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private DLNode pointer;

        LinkedListDequeIterator() {
            pointer = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return pointer != sentinel;
        }

        @Override
        public T next() {
            T p = pointer.item;
            pointer = pointer.next;
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
