public class Utils {
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void exch(Comparable[] a, int v, int w) {
        Comparable t = a[v];
        a[v] = a[w];
        a[w] = t;
    }
    
    public static void show(String preString, Comparable[] a) {
        StdOut.print(preString);
        for(int i=0; i<a.length; i++) {
            StdOut.print(a[i]+ " ");
        }
        StdOut.println();
    }
}