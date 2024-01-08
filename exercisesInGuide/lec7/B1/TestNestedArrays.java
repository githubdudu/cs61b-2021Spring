import org.junit.Test;
import static org.junit.Assert.*;
public class TestNestedArrays {
    @Test
    public void testEmptyArrays() {
        /* Two empty array should be equal;
          Empty array and a zero length array should be equal
          Empty array and a one length array should not be equal
          */
        int[] empty = {};
        int[] emptyCopy = {};
        assertArrayEquals(empty, emptyCopy);
        assertArrayEquals(new int[0], emptyCopy);
        assertNotEquals(new int[1], emptyCopy);
    }
    @Test
    public void testSimpleArrays() {
        /* Test simple int arrays */
        int[] arr = {1, 2};
        int[] arrCopy = {1, 2};
        int[] arr2 = {3, 4};

        assertArrayEquals(arr, arr);
        assertArrayEquals(arr, arrCopy);
        assertNotEquals(arr, arr2);

        /* Test arrays of different length */
        int[] arrLength3 = new int[3];
        int[] arrLength4 = new int[4];
        assertNotEquals(arrLength3, arrLength4);

        /* Test arrays with not defined index */
        int[] arrLength3Copy = new int[3];
        arrLength3[1] = 17;
        arrLength3Copy[1] = 17;
        assertArrayEquals(arrLength3, arrLength3Copy);

        assertEquals(0, arrLength3[0]);
    }
    @Test
    public void testArraysWithDefaultEmptyElements() {
        Object[] o1 = new Object[3];
        Object[] o1Copy = new Object[3];
        assertArrayEquals(o1, o1Copy);
    }
    @Test
    public void testNestedOneLayerArrays() {
        int[] arr = {1, 2};
        int[] arrCopy = {1, 2};
        int[] arr2 = {3, 4};

        Object[] o1 = {1, 2, arr};
        Object[] o2 = {1, 2, arrCopy};
        Object[] o3 = {1, 3, arr};
        Object[] o4 = {1, 2, arr2};

        assertArrayEquals(o1, o2);
        assertNotEquals(o1, o3);
        assertNotEquals(o1, o4);
    }
    @Test
    public void testNestedTwoLayerArrays() {
        int[] arr = {1, 2};
        int[] arrCopy = {1, 2};

        Object[] o1 = {1, 2, arr};
        Object[] o2 = {1, 2, arrCopy};
        Object[] o3 = {3, o2, o1};
        Object[] o4 = {3, o1, o2};

        assertArrayEquals(o3, o4);
    }
}