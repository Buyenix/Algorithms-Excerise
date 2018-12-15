public class RecursiveSelect
{
    public static Comparable NonRecursiceSelect(Comparable[] a, int k) {
        int lo = 0, hi = a.length;
        while(lo < hi) {
            int i = partition(a, lo, hi);
            if (i == k) break;
            else if (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
        }
        return a[k];
    }
    
    private static Comparable RecursiveSelectLow(Comparable[] a, int lo, int hi, int k) {
        int i = partition(a, lo, hi);
        if (i == k) return a[k];
        else if (i > k) hi = i - 1;
        else if (i < k) lo = i + 1;
        return RecursiveSelectLow(a, lo, hi, k);
    }
        
    public static Comparable RecursiveSelect(Comparable[] a, int k) {
        return RecursiveSelectLow(a, 0, a.length, k);
    }
    
    private static int partition(Comparable[] a, int lo, int hi) {
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
}