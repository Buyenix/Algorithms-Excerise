//import java.util.Comparator;

public class ShellWithNewSequence {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int[] incrs = {1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289, 64769, 146305, 260609};
        int len = incrs.length;
        while (--len >= 0) {
            int h = incrs[len];
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
        }
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }
    
    public static void main (String[] args) {
        for (int N = 100; N <= 10000000; N *= 10) {
            Double[] a = new Double[N];
            for (int i = 0; i < N; i++) a[i] = StdRandom.uniform();
            sort(a);
        }
    }
}