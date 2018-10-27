public class SortTwoDistinctKeys {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int lt = 0, ht = N-1;
        int i = 0;
        while(i <= ht) {
            if(less(a[lt], a[i])) {
                exch(a, i, ht--);
            } else if (less(a[i], a[lt])) {
                exch(a, i++, lt++);
            } else {
                i++;
            }
        }
    }
    
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void exch(Comparable[] a, int v, int w) {
        Comparable t = a[v];
        a[v] = a[w];
        a[w] = t;
    }
    
    private static void show(String preString, Comparable[] a) {
        StdOut.print(preString);
        for(int i=0; i<a.length; i++) {
            StdOut.print(a[i]+ " ");
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        int N = 10;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            int t = StdRandom.uniform(0, 10);
            a[i] = (t >= 5) ? 1 : 0;
        }
        show("Raw:  ", a);
        sort(a);
        show("Sort: ", a);
    }
}