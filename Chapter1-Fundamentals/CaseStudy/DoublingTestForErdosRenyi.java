public class DoublingTestForErdosRenyi {
    public static void main(String[] args) {
        int trialCount = 1000;
        for (int N = 100; N < 40000; N += N) {
            int[] connections = new int[trialCount];
            double[] time = new double[trialCount];
            for (int i = 0; i < trialCount; i++) {
                Stopwatch timer = new Stopwatch();
                connections[i] = ErdosRenyi.count(N);
                time[i] = timer.elapsedTime();
            }
            double i = StdStats.mean(connections);
            double j = StdStats.mean(time);
            StdOut.printf("%d: %.2f %.3fs\n", N, i, j);
        }
    }
}