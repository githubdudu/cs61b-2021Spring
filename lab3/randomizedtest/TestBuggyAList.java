package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> alist = new AListNoResizing<>();
        BuggyAList<Integer> blist = new BuggyAList<>();
        alist.addLast(4);
        alist.addLast(5);
        alist.addLast(6);
        blist.addLast(4);
        blist.addLast(5);
        blist.addLast(6);

        for (int i = 0; i < 3; i += 1) {
            int result1 = alist.removeLast();
            int result2 = blist.removeLast();
            assertEquals(result1, result2);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();


        int N = 5000;
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
                // getLast
                if (L.size() > 0) {
                    int last = L.getLast();
                    int last2 = L2.getLast();
                    assertEquals(last, last2);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {
                    int last = L.removeLast();
                    int last2 = L2.removeLast();
                    assertEquals(last, last2);
                }
            }
        }
    }
}
