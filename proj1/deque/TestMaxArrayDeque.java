package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class TestMaxArrayDeque {
    @Test
    public void comparatorIntTest() {
        Comparator cs = new ComparatorSize();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(cs);

        mad.addFirst(1);
        mad.addFirst(1);
        mad.addFirst(1);

        assertEquals(1, (int) mad.max(cs));
        mad.addFirst(3);
        assertEquals(3, (int) mad.max(cs));
    }

    @Test
    public void comparatorStringTest() {
        Comparator cs = new ComparatorString();
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(cs);

        mad.addFirst("a");
        mad.addFirst("b");
        mad.addFirst("c");

        assertEquals("c", mad.max(cs));
        assertEquals("c", mad.max());
        mad.addFirst("z");
        assertEquals("z", mad.max(cs));
    }

    private class ComparatorSize implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    private class ComparatorString implements Comparator<String> {
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }
}
