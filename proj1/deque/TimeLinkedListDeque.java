package deque;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeLinkedListDeque {
    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
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

        TimeAddFirstConstruction();
        TimeAddLastConstruction();
        TimeRemoveFirstConstruction();
        TimeRemoveLastConstruction();

    }

    static int START_N = 1000;// 1000, 2000, 4000, 8000, 16000, 32000, 64000
    static int END_N = 128000; // 64000 or 128000; depends on Hardware
    static int ITEM_ADDED = 1;

    public static void TimeAddFirstConstruction() {
        LinkedListDeque<Integer> Ns = new LinkedListDeque<>();
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        for (int k = START_N; k <= END_N; k = k * 2) {
            Ns.addLast(k);

            LinkedListDeque<Integer> lst = new LinkedListDeque<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < k; j += 1) {
                lst.addFirst(ITEM_ADDED);
            }
            times.addLast(sw.elapsedTime());
        }
        System.out.println("AddFirst: ");
        printTimingTable(Ns, times, Ns);
    }

    public static void TimeAddLastConstruction() {
        LinkedListDeque<Integer> Ns = new LinkedListDeque<>();
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        for (int k = START_N; k <= END_N; k = k * 2) {
            Ns.addLast(k);

            LinkedListDeque<Integer> lst = new LinkedListDeque<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < k; j += 1) {
                lst.addLast(ITEM_ADDED);
            }
            times.addLast(sw.elapsedTime());
        }
        System.out.println("AddLast: ");
        printTimingTable(Ns, times, Ns);
    }

    public static void TimeRemoveFirstConstruction() {
        LinkedListDeque<Integer> Ns = new LinkedListDeque<>();
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        for (int k = START_N; k <= END_N; k = k * 2) {
            Ns.addLast(k);

            LinkedListDeque<Integer> lst = new LinkedListDeque<>();
            for (int j = 0; j < k; j += 1) {
                lst.addFirst(ITEM_ADDED);
            }

            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < k; j += 1) {
                lst.removeFirst();
            }
            times.addLast(sw.elapsedTime());
        }
        System.out.println("RemoveFirst: ");
        printTimingTable(Ns, times, Ns);
    }

    public static void TimeRemoveLastConstruction() {
        LinkedListDeque<Integer> Ns = new LinkedListDeque<>();
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        for (int k = START_N; k <= END_N; k = k * 2) {
            Ns.addLast(k);

            LinkedListDeque<Integer> lst = new LinkedListDeque<>();
            for (int j = 0; j < k; j += 1) {
                lst.addFirst(ITEM_ADDED);
            }

            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < k; j += 1) {
                lst.removeLast();
            }
            times.addLast(sw.elapsedTime());
        }
        System.out.println("RemoveLast: ");
        printTimingTable(Ns, times, Ns);
    }

}
