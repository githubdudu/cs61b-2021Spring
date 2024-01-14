package C1;

/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    private int[] arr;
    private int size;
    /** Creates an empty list. */
    public AList() {
        arr = new int[100];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    // addLast() without resizing
    /*
    public void addLast(int x) {
        arr[size] = x;
        size += 1;
    }
     */

    // addLast() with resizing
    public void addLast(int x) {
        if(size == arr.length) {
            int[] newArr = new int[size * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }
        arr[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return arr[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return arr[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        size -= 1;
        return arr[size];
    }
} 