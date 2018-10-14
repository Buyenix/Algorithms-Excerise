public class Merge {
    private static Comparable[] aux;
    private static int visitCount = 0;
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static int sort(Comparable[] a) {
        aux = new Comparable[a.length];
        visitCount = 0;
        sort(a, 0, a.length-1);
        return visitCount;
    }
    
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
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
    
    public static int getVisitCount() { return visitCount; }
}