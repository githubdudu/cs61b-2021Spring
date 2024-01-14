package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.print("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        int START_N = 1000;// 1000, 2000, 4000, 8000, 16000, 32000, 64000
        int END_N = 128000; // 64000 or 128000; depends on Hardware
        int ITEM_ADDED = 1;
        // These ALists are used for print method as counters
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCount = Ns;
        for (int k = START_N; k <= END_N; k = k * 2) {
            Ns.addLast(k); // Record the N column
            /* 1. Create an empty AList */
            AList<Integer> lst = new AList<>();
            /* 2. Start the timer */
            Stopwatch sw = new Stopwatch();
            /* 3. Perform addLast k times */
            for (int j = 0; j < k; j += 1) {
                lst.addLast(ITEM_ADDED);
            }
            /* 4. Record times */
            times.addLast(sw.elapsedTime());
        }
        System.out.println("Timing table for AList addLast:");
        printTimingTable(Ns, times, opCount);
    }
}
