public class Quick3string
{
    public static void sort(String[] a)
    {
        int N = a.length;
        sort(a, 0, N-1, 0);
    }
    
    private static int charAt(String a, int d) {
        if (a.length() <= d) return -1;
        else               return a.charAt(d);
    }
    
    private static void sort(String[] a, int lo, int hi, int d)
    {
        if (hi <= lo) return;
        int lt = lo;
        int gt = hi;
        int i = lo+1;
        int v = charAt(a[lo], d);
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) {
                exch(a, i++, lt++);
            } else if (t > v) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt-1, d);
        if (v >= 0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }
    
    private static void exch(String[] a, int i, int j)
    {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}