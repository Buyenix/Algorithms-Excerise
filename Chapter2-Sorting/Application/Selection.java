import java.util.Comparator;

public class Selection{
    public static void sort(Pack[] a, Comparator c) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(c, a[min], a[j])) min = j;
            }
            exch(a, i, min);
        }
    }
    
    private static boolean less(Comparator c, Pack v, Pack w) {
        //return v.compareTo(w) < 0;
        return c.compare(v, w) < 0;
    }
    
    private static void exch(Pack[] a, int i, int j) {
        Pack t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}