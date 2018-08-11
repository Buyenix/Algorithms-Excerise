public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        StdOut.printf("size: %d\n", N);
        while(h < N/3) h = 3*h + 1;
        while (h >= 1) {
            int count = 0;
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                    count++;
                }
            }
            StdOut.printf("  h:%5d, %.4f\n", h, (double)count/N);
            h /= 3;
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
