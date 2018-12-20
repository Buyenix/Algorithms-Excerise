import java.util.Comparator;
import java.util.Arrays;
public class Stability
{
    public boolean check(Pack[] a) {
        if (a.length < 2) return true;
        for (int i = 1; i < a.length; i++) {
            if (Pack.INT_ORDER1.compare(a[i-1], a[i]) > 0) {
                return false;
            } else if (Pack.INT_ORDER1.compare(a[i-1], a[i]) == 0) {
                if (Pack.INT_ORDER2.compare(a[i-1], a[i]) > 0)
                    return false;
            }
        }
        return true;
    }
    
    public static void sort(Pack[] a, Comparator c) {
        int N = a.length;
        int[] index = new int[N];
        for (int i = 0; i < N; i++) {
            index[i] = i;
        }
        
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(c, a[j], a[min], index[j], index[min])) min = j;
            }
            exch(a, i, min, index);
        }
    }
    
    private static boolean less(Comparator c, Pack v, Pack w, int vIndex, int wIndex) {
        int cmp = c.compare(v, w);
        if (cmp != 0) return cmp < 0;
        else          return vIndex < wIndex;
    }
    
    private static void exch(Pack[] a, int i, int j, int[] index) {
        Pack t = a[i];
        a[i] = a[j];
        a[j] = t;
        
        int tIndex = index[i];
        index[i] = index[j];
        index[j] = tIndex;
    }
    
    public static void main(String[] args) {
        int n = 10;
        Pack[] data = new Pack[10];
        for (int i = 0; i < n; i++) {
            int v1 = StdRandom.uniform(0, 10);
            int v2 = StdRandom.uniform(0, 10);
            data[i] = new Pack(v1, v2);
        }
        //Arrays.sort(data, Pack.INT_ORDER2);
        //Selection.sort(data, Pack.INT_ORDER2);
        Stability.sort(data, Pack.INT_ORDER2);
        StdOut.println("Round1:");
        for (int i = 0; i < n; i++) {
            StdOut.printf("  %d, %d\n", data[i].c1, data[i].c2);
        }
        //Arrays.sort(data, Pack.INT_ORDER1);
        //Selection.sort(data, Pack.INT_ORDER1);
        Stability.sort(data, Pack.INT_ORDER1);
        StdOut.println("Round2:");
        for (int i = 0; i < n; i++) {
            StdOut.printf("  %d, %d\n", data[i].c1, data[i].c2);
        }
        Stability stabler = new Stability();
        boolean result = stabler.check(data);
        StdOut.println("The array is " + (result ? "stable" : "not stable"));
    }
}