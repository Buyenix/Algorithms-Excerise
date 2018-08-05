public class ErdosRenyi {
    public static int count(int N) {
        UF uf = new UF(N);
        int count = 0;
        while(uf.count() != 1) {
            int p = StdRandom.uniform(N);
            int q = StdRandom.uniform(N);
            //StdOut.printf("(%d, %d): ", p, q);
//            if (uf.connected(p, q)) {
//                //StdOut.print("Connected\n");
//                continue;
//            }
            uf.union(p, q);
            //StdOut.print("Union\n");
            count++;
        }
        return count;
    }
    
    public static void main(String[] args) {
//        int N = Integer.parseInt(args[0]);
//        int cCount = count(N);
//        StdOut.println("Connection count: " + cCount);
        int n = Integer.parseInt(args[0]);          // number of vertices
        int trials = Integer.parseInt(args[1]);     // number of trials
        int[] edges = new int[trials];

        // repeat the experiment trials times
        for (int t = 0; t < trials; t++) {
            edges[t] = count(n);
        }

        // report statistics
        StdOut.println("1/2 n ln n = " + 0.5 * n * Math.log(n));
        StdOut.println("mean       = " + StdStats.mean(edges));
        StdOut.println("stddev     = " + StdStats.stddev(edges));
    }
}