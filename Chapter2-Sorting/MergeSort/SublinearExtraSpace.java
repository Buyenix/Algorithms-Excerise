public class SublinearExtraSpace {
    
    private static Comparable[] genRandomData(int M, int N) {
        Comparable[] a = new Comparable[M*N];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(0, 10000);
        }
        return a;
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void sort(int M, int N) {
        Comparable[] a = genRandomData(M, N);
        for (int i = 0; i < N; i++) {
            selectionSort(a, i*M, (i+1)*M-1);
        }
        for (int i = 0; i < N-1; i++) {
            merge(a, 0, (i+1)*M-1, (i+2)*M-1);
        }
    }
    
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux = new Comparable[hi-lo+1];
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }
    
    public static void selectionSort(Comparable[] a, int lo, int hi) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            Comparable temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }
}