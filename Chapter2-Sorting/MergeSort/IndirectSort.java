public class IndirectSort {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static int[] sort(Comparable[] a) {
        int N = a.length;
        int[] index = new int[N];
        for (int i=0; i < N; i++) index[i] = i;
        sort(a, index, 0, N-1);
        return index;
    }
    
    public static void sort(Comparable[] a, int[] index, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, index, lo, mid);
        sort(a, index, mid+1, hi);
        merge(a, index, lo, mid, hi);
    }
    
    public static void merge(Comparable[] a, int[] index, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        int[] cloneIndex = index.clone();
        for (int k = lo; k <= hi; k++) {
            if      (i > mid) {index[k] = cloneIndex[j++];}
            else if (j > hi)  {index[k] = cloneIndex[i++];}
            else if (less(a[cloneIndex[j]], a[cloneIndex[i]])) 
                              {index[k] = cloneIndex[j++];}
            else              {index[k] = cloneIndex[i++];}
        }
    }
    
    public static void main(String[] args) {
        int N = 10;
        Integer[] a = new Integer[N];
        for(int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(1, 1000);
        }
        
        int[] finalIndex = new int[N];
        // Initialize the index
        for (int i=0; i < N; i++) finalIndex[i] = i;
        // Show the original array
        StdOut.print("Raw: ");
        show(a, finalIndex);
        // Start to sort...
        finalIndex = sort(a);
        // Show the final sorted array
        StdOut.print("Sort:");
        show(a, finalIndex);
    }
    
    private static void show(Comparable[] a, int[] index) {
        if (a.length != index.length) throw new RuntimeException("Bad result!");
        for(int i=0; i<index.length; i++) {
            StdOut.print(a[index[i]] + " ");
        }
        StdOut.println();
    }
}