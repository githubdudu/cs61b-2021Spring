package deque;

public class LinkedListDeque<T> {
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

    public LinkedListDeque(T item) {
        DLNode node = new DLNode(item);
        sentinel = new DLNode(null, node, node);
        node.prev = sentinel;
        node.next = sentinel;
        size = size + 1;
    }

    // Adds an item of type T to the front of the deque. You can assume that item is never null.
    public void addFirst(T item) {
        DLNode added = new DLNode(item, sentinel, sentinel.next);

        added.prev.next = added;
        added.next.prev = added;
        size = size + 1;
    }

    // Adds an item of type T to the back of the deque. You can assume that item is never null.
    public void addLast(T item) {
        DLNode added = new DLNode(item, sentinel.prev, sentinel);

        added.prev.next = added;
        added.next.prev = added;
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
        DLNode pointer = sentinel.next;
        while (pointer != sentinel) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
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
}
