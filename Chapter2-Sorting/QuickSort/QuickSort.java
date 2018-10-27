public class QuickSort {
    private static int compareCount = 0;
    public static int sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        StdRandom.shuffle(a);
        compareCount = 0;
        sort(a, lo, hi);
        return compareCount;
    }
    
    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = partition(a, lo, hi);
        sort(a, lo, i-1);
        sort(a, i+1, hi);
    }
    
    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while(Utils.less(a[++i], v)) {
                compareCount++;
                if (i == hi) break;
            }
            
            while(Utils.less(v, a[--j])){
                compareCount++;
                if (j == lo) break;
            }
            
            if (i >= j) break;
            Utils.exch(a, i, j);
        }
        Utils.exch(a, lo, j);
        return j;
    }
    
    private static Integer[] genData(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, 100000000);
        return a;
    }
    
    public static void main(String[] args) {
        int[] trial = {100, 1000, 10000};
        for (int k=0; k < trial.length; k++) {
            int count = 0;
            int N = trial[k];
            for (int i=0; i< 100; i++) {
                Integer[] a = genData(N);
                count += sort(a);
            }
            double c1 = count/100.0;
            double c2 = 2.0*N*Math.log(N)/Math.log(2);
            //StdOut.printf("N = %d\n    Actual: %f\n", N, count/100);
            StdOut.printf("N = %d\n    Actual: %f\n    2NlnN:  %f\n", N, c1, c2);
        }
    }
}