public class Sentinels {
    public static void sort(Comparable[] a) {
        int lo = 0, hi = a.length-1;
        setMax(a);
        sort(a, lo, hi);
    }
    
    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = partition(a, lo, hi);
        sort(a, lo, i-1);
        sort(a, i+1, hi);
    }
    
    public static int partition(Comparable[] a, int lo, int hi) {
        Comparable v = a[lo];
        int i = lo, j = hi+1;
        while(true) {
            while(Utils.less(a[++i], v)) {};
            while(Utils.less(v, a[--j])) {};
            if (i >= j) break;
            Utils.exch(a, i, j);
        }
        Utils.exch(a, lo, j);
        return j;
    }
    
    public static void setMax(Comparable[] a) {
        int max = 0;
        for (int i = 1; i < a.length; i++) {
            if (Utils.less(a[max], a[i]))
                max = i;
        }
        Utils.exch(a, max, a.length-1);
    }
   
    private static Integer[] genData(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, 1000);
        return a;
    }
    
    public static void main(String[] args) {
        Integer[] a = genData(1000);
        Utils.show("Raw: ", a);
        sort(a);
        Utils.show("Sort:", a);
    }
}