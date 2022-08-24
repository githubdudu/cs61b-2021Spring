package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        int START_N = 1000;// 1000, 2000, 4000, 8000, 16000, 32000, 64000
        int END_N = 128000; // 64000 or 128000; depends on Hardware
        int ITEM_ADDED = 99;
        int M = 10000;
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCount = new AList<>();
        for (int k = START_N; k <= END_N; k = k * 2) {
            Ns.addLast(k);
            opCount.addLast(M);
            /* 1. Create an SLList */
            SLList<Integer> lst = new SLList<>();
            /* 2. Add N items */
            for (int j = 0; j < k; j += 1) {
                lst.addFirst(ITEM_ADDED);
            }
            /* 3. Start the timer */
            Stopwatch sw = new Stopwatch();
            /* 4. Perform addLast M times */
            for (int j = 0; j < M; j += 1) {
                lst.getLast();
            }
            /* 5. Record timer */
            times.addLast(sw.elapsedTime());
        }
        printTimingTable(Ns, times, opCount);
    }

}
