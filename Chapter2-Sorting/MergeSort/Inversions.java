public class Inversions {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static int calc(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        int inv = calc(a, aux, 0, a.length-1);
        return inv;
    }
    
    public static int calc(Comparable[] a, Comparable[] aux, int lo, int hi) {
        int inv = 0;
        if (hi <= lo) return inv;
        int mid = lo + (hi-lo)/2;
        inv += calc(a, aux, lo, mid);
        inv += calc(a, aux, mid+1, hi);
        inv += merge(a, aux, lo, mid, hi);
        return inv;
    }

    
    public static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int inv = 0;
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {inv += mid-i+1; a[k] = aux[j++];}
            else                           a[k] = aux[i++];
        }
        return inv;
    }
}