import java.util.Comparator;
import java.util.Arrays;
public class Stability
{
    private boolean less(Comparator c, Pack v, Pack w) {
        return c.compare(v, w) < 0;
    }
    
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
    
    public static void main(String[] args) {
        int n = 10;
        Pack[] data = new Pack[10];
        for (int i = 0; i < n; i++) {
            int v1 = StdRandom.uniform(0, 10);
            int v2 = StdRandom.uniform(0, 10);
            data[i] = new Pack(v1, v2);
        }
        //Arrays.sort(data, Stability.Pack.INT_ORDER2);
        Selection.sort(data, Pack.INT_ORDER2);
        StdOut.println("Round1:");
        for (int i = 0; i < n; i++) {
            StdOut.printf("  %d, %d\n", data[i].c1, data[i].c2);
        }
        //Arrays.sort(data, Stability.Pack.INT_ORDER1);
        Selection.sort(data, Pack.INT_ORDER1);
        StdOut.println("Round2:");
        for (int i = 0; i < n; i++) {
            StdOut.printf("  %d, %d\n", data[i].c1, data[i].c2);
        }
        Stability stabler = new Stability();
        boolean result = stabler.check(data);
        StdOut.println("The array is " + (result ? "stable" : "not stable"));
    }
}