public class NaturalMergesort {
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static int findSubSortedEnd(Comparable[] a, int start) {
        int N = a.length;
        int end = start + 1;
        while (end < N && less(a[end-1], a[end])) {
            end++;
        }
        return end-1;
    }
    
    public static void show(Comparable[] a) {
        for (int i=0; i<a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
    
    public static void sort(Comparable[] a) {
        StdOut.print("Raw: ");
        show(a);
        
        int N = a.length;
        Comparable[] aux = new Comparable[a.length];
        int lo = 0, mid = 0, hi = 0;
        while (true) {
            mid = findSubSortedEnd(a, lo);
            if (lo == 0 && mid == N-1) break; // Sorting is finished here!
            hi  = (mid == N-1) ? findSubSortedEnd(a, mid+1) : N-1;
            StdOut.println("lo= " + lo + ", mid= " + mid + ", hi= " + hi);
            merge(a, aux, lo, mid, hi);
            
            StdOut.print("Merge: ");
            show(a);
            
            lo = (hi == N-1) ? 0 : hi+1;
        }
        
        StdOut.print("Sort:");
        show(a);
    }
    
    
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }
    
    public static void main(String[] args) {
        int N = 10;
        Integer[] a = new Integer[N];
        for(int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(1, 1000);
        }
        sort(a);
    }
}