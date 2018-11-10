public class MedianOfThreePartitioning {
    public static void sort(Comparable[] a) {
        int lo = 0, hi = a.length-1;
        sort(a, lo, hi);
    }
    
    public static void sortHead3Ones(Comparable[] a, int lo, int hi) {
        if (hi-lo != 2) throw new RuntimeException("Not 3 ones!");
        for (int i = lo + 1; i <= hi; i++) {
            for (int j=i; j > lo && Utils.less(a[j], a[j-1]); j--) {
                Utils.exch(a, j, j-1);
            }
        }
    }
    
    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        //If subarray length is 2, just sort and return.
        if (hi == lo + 1) {
            if (Utils.less(a[hi], a[lo])) {
                Utils.exch(a, lo, hi);
            }
            return;
        }
        
        int i = partition(a, lo, hi);
        sort(a, lo, i-1);
        sort(a, i+1, hi);
    }
    
    public static int partition(Comparable[] a, int lo, int hi) {
        if (hi-lo < 2) throw new RuntimeException("Partition is less than 3!");
        //Sort the head 3 elements, and move the largest one to the end of this subarray to be sentinel.
        sortHead3Ones(a, lo, lo+2);
        Utils.exch(a, lo+2, hi);
        
        // Since a[lo] is less than a[lo+1], we can skip to sort lo+1 directly.
        int i = lo+1, j = hi+1;
        Comparable v = a[lo+1];
        while(true) {
            while(Utils.less(a[++i], v)) {};
            while(Utils.less(v, a[--j])) {};
            if (i >= j) break;
            Utils.exch(a, i, j);
        }
        Utils.exch(a, lo+1, j);
        return j;
    }
   
    private static Integer[] genData(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, 1000);
        return a;
    }
    
    public static void main(String[] args) {
        Integer[] a = genData(100);
        Utils.show("Raw: ", a);
        sort(a);
        Utils.show("Sort:", a);
    }
}