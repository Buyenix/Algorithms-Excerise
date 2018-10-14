public class PlotVisitCount {
    public static void main (String[] args) {
        int N = 512 * 8;
        StdDraw.setXscale(-0.5, N);
        StdDraw.setYscale(0, 250000);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i += 8) {
            Double[] a = new Double[i];
            for (int j = 0; j < i; j++) {
                a[j] = StdRandom.uniform(0.0, 10.0);
            }
            int mergeVisitCount = Merge.sort(a);
            StdOut.println(mergeVisitCount);
            StdDraw.point(i, mergeVisitCount);
        }
        
        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < N; i += 8) {
            Double[] a = new Double[i];
            for (int j = 0; j < i; j++) {
                a[j] = StdRandom.uniform(0.0, 10.0);
            }
            int mergeBUVisitCount = MergeBU.sort(a);
            StdOut.println(mergeBUVisitCount);
            StdDraw.point(i, mergeBUVisitCount);
        }
        
        StdDraw.setPenColor(StdDraw.GREEN);
        for (int i = 0; i < N; i += 8) {
            double nLgn = 6 * i * Math.log(i)/Math.log(2);
            StdOut.println(nLgn);
            StdDraw.point(i, nLgn);
        }
    }
}