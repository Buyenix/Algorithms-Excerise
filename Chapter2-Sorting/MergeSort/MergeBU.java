public class MergeBU {
    private static Comparable[] aux;
    private static int visitCount = 0;
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static int sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[a.length];
        visitCount = 0;        
        int sz = 1;
        while (sz < N) {
            for (int lo = 0; lo < N - sz; lo += sz+sz) {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
            sz += sz;
        }
        return visitCount;
    }
    
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            visitCount += 2;
        }
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
            visitCount += 2;
        }
    }
}