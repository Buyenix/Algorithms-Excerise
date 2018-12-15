public class Utils {
    public static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;
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
            //StdOut.print(a[i]+ " ");
            StdOut.printf("%d  ", a[i]);
        }
        StdOut.println();
    }
    
    public static Integer[] genData(int N, int range) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, range);
        return a;
    }
    
    public static void InsertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                Utils.exch(a, j-1, j);
            }
        }
    }
    
    public static boolean isEqual(Comparable v, Comparable w) {
        if (v == w) return true;
        return v.compareTo(w) == 0;
    }
    
//    public static double time(String alg, Comparable[] a) {
//        Utils util = new Utils();
//        Stopwatch timer = util.new Stopwatch();
//        if (alg.equals("JavaSystemSort"))           JavaSystemSort.sort(a);
//        else if (alg.equals("QuickSort"))           QuickSort.sort(a);
//        else if (alg.equals("QuickBentleyMcIlroy")) QuickBentleyMcIlroy.sort(a);
//        else if (alg.equals("SampleSort"))          SampleSort.sort(a, 7);
//        else {throw new RuntimeException("No matched sort method!");}
//        return timer.elapsedTime();
//    }
    
    private class Stopwatch {
        private long start;
        public Stopwatch() { start = System.currentTimeMillis(); }
        public double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start);
        }
        public void reset() {start = System.currentTimeMillis();}
    }
    
//    public static void sortCompare(Comparable[] a1, Comparable[] a2, String s1, String s2) {
//        if (a1.length != a2.length) throw new RuntimeException("Two array have different size!");
//        double t1 = time(s1, a1);
//        double t2 = time(s2, a2);
//        StdOut.printf("%s: %.1f\n", s1, t1);
//        StdOut.printf("%s: %.1f\n", s2, t2);
//        StdOut.printf("%s is %.1f times faster than %s\n", s1, t2/t1, s2);        
//    }
}