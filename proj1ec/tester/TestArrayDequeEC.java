package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> L = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> L2 = new ArrayDequeSolution<>();

        int N = 5000;
        String msg = "";
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                msg += "\naddLast(" + randVal + ")";
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = L2.size();
                msg += "\nsize()";
                assertEquals(msg, size, size2);
            } else if (operationNumber == 2) {
                // get
                if (!L.isEmpty()) {
                    int randVal = StdRandom.uniform(0, L.size());
                    Integer last = L.get(randVal);
                    Integer last2 = L2.get(randVal);
                    msg += "\nget(" + randVal + ")";
                    assertEquals(msg, last, last2);

                }

            } else if (operationNumber == 3) {
                // removeLast
                if (!L.isEmpty()) {
                    Integer last = L.removeLast();
                    Integer last2 = L2.removeLast();
                    msg += "\nremoveLast()";
                    assertEquals(msg, last, last2);

                }
            } else if (operationNumber == 4) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);

                msg += "\naddFirst(" + randVal + ")";
                L.addFirst(randVal);
                L2.addFirst(randVal);
                ;
            } else if (operationNumber == 5) {
                // removeFirst
                if (!L.isEmpty()) {
                    Integer last = L.removeFirst();
                    Integer last2 = L2.removeFirst();

                    msg += "\nremoveFirst()";
                    assertEquals(msg, last, last2);

                }
            }
        }
    }

}
