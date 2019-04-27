public class Utils {
    
    public static void show(String preString, int[] a) {
        StdOut.print(preString);
        for(int i=0; i<a.length; i++) {
            //StdOut.print(a[i]+ " ");
            StdOut.printf("%d  ", a[i]);
        }
        StdOut.println();
    }
    
    public static void show(String preString, boolean[] a) {
        StdOut.print(preString);
        for(int i=0; i<a.length; i++) {
            //StdOut.print(a[i]+ " ");
            StdOut.printf(a[i] ? "T " : "F ");
        }
        StdOut.println();
    }
    
}