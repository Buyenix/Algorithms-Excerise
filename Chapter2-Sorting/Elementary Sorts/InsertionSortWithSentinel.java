public class InsertionSortWithSentinel {
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    public static void sort(Integer[] a) {
        int N = a.length;
        int minPos = 0;
        for (int i = 1; i < N; i++) {
            if (less(a[i], a[minPos])) minPos = i;
        }
        exch(a, 0, minPos);
        
        for (int i = 1; i < N; i++) {
            for (int j = i; less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }
}