package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }

    @Test
    /* Add number of elements to deque; check if get() is correct. */
    public void getDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            lld1.addLast(i);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals((int)lld1.get(i), i);
        }
        assertNull(lld1.get(10));
        assertNull(lld1.get(100001));

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        for (int i = 0; i < 100000; i++) {
            lld2.addLast(i);
        }

        for (int i = 0; i < 100000; i++) {
            assertEquals((int)lld2.get(i), i);
        }
        assertNull(lld2.get(100000));

    }

    @Test
    /* Add number of elements to deque; check if getRecursive() is correct. */
    public void getRecursiveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            lld1.addLast(i);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals((int)lld1.getRecursive(i), i);
        }

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        for (int i = 0; i < 1000; i++) {
            lld2.addLast(i);
        }

        for (int i = 0; i < 1000; i++) {
            assertEquals((int)lld2.getRecursive(i), i);
        }

    }

    @Test
    public void equalsTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();

        assertTrue(lld1.equals(lld2));
        assertTrue(lld2.equals(lld1));
        for (int i = 0; i < 100000; i++) {
            lld1.addLast(i);
            lld2.addLast(i);
            assertTrue("wrong equal", lld1.equals(lld2));
            assertTrue(lld2.equals(lld1));
        }
    }

    @Test
    public void iteratorTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        Iterator iter = lld1.iterator();
        assertEquals(1, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(2, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(3, iter.next());
        assertFalse(iter.hasNext());
        assertNull(iter.next());
    }
}
