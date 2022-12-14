package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class RandomDequeTest {

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();


        int N = 500000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = L2.size();
                assertEquals(size, size2);
            } else if (operationNumber == 2) {
                // get
                int randVal = StdRandom.uniform(0, 100);
                if (L.get(randVal) != null) {
                    int last = L.get(randVal);
                    int last2 = L2.get(randVal);
                    assertEquals(last, last2);

                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {

                    int last = L.removeLast();
                    int last2 = L2.removeLast();
                    assertEquals(last, last2);
                }
            } else if (operationNumber == 4) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);

                L.addFirst(randVal);
                L2.addFirst(randVal);
            } else if (operationNumber == 5) {
                // removeFirst
                if (L.size() > 0) {
                    int first = L.removeFirst();
                    int first2 = L2.removeFirst();
                    assertEquals(first, first2);
                }

            }
        }
    }
}
