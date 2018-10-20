public class ImprovedMerge {
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        aux = a.clone();
        sort(aux, a, 0, a.length-1);
    }
    
    public static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo) return;
        //if (isSorted(a, lo, hi)) return;
        
        int len = hi - lo + 1;
        if (len <= 15) {
            InsertionSort(dst, lo, hi);
            return;
        }
        
        int mid = lo + (hi - lo)/2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);
        
        //The src are compeletely sorted.
        if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi-lo+1);
            return;
        }
        merge(src, dst, lo, mid, hi);
    }
    
    public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++];
            else                           dst[k] = src[i++];
        }
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (less(a[i+1], a[i])) return false;
        }
        return true;
    }
    
    public static void InsertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, i-1, i);
            }
        }
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}