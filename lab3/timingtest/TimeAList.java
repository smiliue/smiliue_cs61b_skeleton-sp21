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
        // TODO: YOUR CODE HERE
        AList<Integer> n_list = new AList<>();
        n_list.addLast(1000);
        n_list.addLast(2000);
        n_list.addLast(4000);
        n_list.addLast(8000);
        n_list.addLast(16000);
        n_list.addLast(32000);
        n_list.addLast(64000);
        n_list.addLast(128000);

        AList<Double> time_list = new AList<>();

        for (int i=0; i<n_list.size(); i++)
        {
            int cnt = n_list.get(i);
            AList<Integer> list = new AList<>();
            Stopwatch sw = new Stopwatch();
            for (int j=0; j<cnt; j++)
            {
                list.addLast(j);
            }
            time_list.addLast(sw.elapsedTime());
        }

        printTimingTable(n_list, time_list, n_list);
    }
}
