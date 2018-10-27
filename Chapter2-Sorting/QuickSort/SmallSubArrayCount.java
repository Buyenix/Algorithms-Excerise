public class SmallSubArrayCount {
    private static int size0 = 0;
    private static int size1 = 0;
    private static int size2 = 0;
    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        StdRandom.shuffle(a);
        size0 = size1 = size2 = 0;
        sort(a, lo, hi);
    }
    
    public static int getCount(int N) {
        if (N==0)      return size0;
        else if (N==1) return size1;
        else if (N==2) return size2;
        else           return 0;
    }
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi - lo < 0)  size0++;
        if (hi - lo == 0) size1++;
        if (hi - lo == 1) size2++;
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
                if (i == hi) break;
            }
            
            while(Utils.less(v, a[--j])){
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
            int N = trial[k];
            Integer[] a = genData(N);
            sort(a);
            
            double c0 = getCount(0);
            double c1 = getCount(1);
            double c2 = getCount(2);
            StdOut.printf("N = %d\n    c0: %f, c1: %f, c2: %f\n", N, c0, c1, c2);
        }
    }
}