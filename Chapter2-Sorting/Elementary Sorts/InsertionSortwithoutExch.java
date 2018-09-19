public class InsertionSortwithoutExch {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int j = i;
            for (; j > 0 && less(a[i], a[j-1]); j--) {
                a[j] = a[j-1];
            }
            a[j] = a[i];
        }
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        
//    public static void main(String[] args) {
//        int N = 10;
//        Double[] a = new Double[N];
//        for (int i = 0; i < N; i++) {
//            a[i] = StdRandom.uniform();
//        }
//        InsertionSortwithoutExch obj = new InsertionSortwithoutExch();
//        obj.sort(a);
//    }
}