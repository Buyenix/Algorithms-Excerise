public class MSD
{
    private static int M = 15;
    private static int R = 256;
    public static void sort(String[] a)
    {
        int N = a.length;
        String[] aux = new String[N];
        msd(a, 0, N-1, 0, aux);
    }
    
    private static int charAt(String a, int d) {
        if (a.length() <= d) return -1;
        else               return a.charAt(d);
    }
    
    private static void msd(String[] a, int lo, int hi, int d, String[] aux)
    {
        if (hi <= lo + M) {
            InsertionSort(a, lo, hi, d);
            return;
        }
        
        int count[] = new int[R+2];
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d)+2]++;
        }
        for (int i = 0; i < R+1; i++) {
            count[i+1] += count[i];
        }
        for (int i = lo; i <= hi; i++) {
            int idx = charAt(a[i], d) + 1;
            aux[count[idx]++] = a[i];
        }
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i-lo];
        }
        
        for (int i = 0; i < R; i++) {
            msd(a, lo+count[i], lo+count[i+1]-1, d+1, aux);
        }
    }
    
    private static void InsertionSort(String[] a, int lo, int hi, int d)
    {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
                String t = a[j];
                a[j] = a[j-1];
                a[j-1] = t;
            }
        }
    }
    
    private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) > w.charAt(i)) return false;
            if (v.charAt(i) < w.charAt(i))  return true;
        }
        return v.length() < w.length();
    }
}