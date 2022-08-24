package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        int START_N = 1000;// 1000, 2000, 4000, 8000, 16000, 32000, 64000
        int END_N = 128000; // 64000 or 128000; depends on Hardware
        int ITEM_ADDED = 1;
        AList<Double> times = new AList<>();
        for (int k = START_N; k <= END_N; k = k * 2) {
            Ns.addLast(k);

            AList<Integer> lst = new AList<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < k; j += 1) {
                lst.addLast(ITEM_ADDED);
            }
            times.addLast(sw.elapsedTime());
        }
        printTimingTable(Ns, times, Ns);
    }
}
