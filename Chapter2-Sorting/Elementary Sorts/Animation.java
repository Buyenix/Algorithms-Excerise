public class Animation {
    ////////////////////////////
    ////// Insertion sort //////
    ////////////////////////////
    private class Insertion {
        public void sort(Comparable[] a) {
            int N = a.length;
            show(a);
            for (int i = 1; i < N; i++) {
                for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                    exch(a, j, j-1);
                    show(a);
                }
            }
        }
    }
    ////////////////////////////
    ////// Selection sort //////
    ////////////////////////////    
    private class Selection {
        public void sort(Comparable[] a) {
            int N = a.length;
            show(a);
            for (int i = 0; i < N; i++) {
                int min = i;
                for (int j = i+1; j < N; j++) {
                    if (less(a[j], a[min])) min = j;
                }
                exch(a, i, min);
                show(a);
            }
        }
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        int N = a.length;
        StdDraw.setXscale(-0.5, N);
        StdDraw.setYscale(0, 1);
        for (int i = 0; i < N; i++) {
            double x = i;
            double y = ((Double)a[i]).doubleValue()/2;
            double rw = 0.2;
            double rh = ((Double)a[i]).doubleValue()/2;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
        StdDraw.show(1000);
        StdDraw.clear();
    }
    
    private static void sort(String alg, Double[] a) {
        Animation animation = new Animation();
        if (alg.equals("Selection")) {
            Selection Ssort = animation.new Selection();
            Ssort.sort(a);
        }
        if (alg.equals("Insertion")) {
            Insertion Isort = animation.new Insertion();
            Isort.sort(a);
        }
    }
    
    public static void RandomInputSort(String alg, int N) {
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        sort(alg, a);
    }
    
    public static void main(String[] args) {
        RandomInputSort("Selection", 10);
        RandomInputSort("Insertion", 10);
    }
}