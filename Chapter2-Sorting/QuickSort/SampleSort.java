public class SampleSort {
    public static Comparable[] getSampleArr(Comparable[] a, int k) {
        int step = (int)(Math.pow(2, k) - 1);
        if (step <= 1) throw new RuntimeException("Wrong sample step!");
        int size = a.length/step;
        Comparable[] samples = new Comparable[size];
        for (int i = 0; i < samples.length; i++) {
            samples[i] = a[step*i + step/2];
        }
        QuickSort.sort(samples);
        return samples;
    }
    
    public static void swap(Comparable[] a, Comparable t, int lo, int hi) {
        //StdOut.printf("Swap: %d --> ", t);
        //for (int i = lo; i <= hi; i++) StdOut.printf("%d ", a[i]);
        //StdOut.println();
        for (int i = lo; i <= hi; i++) {
            if (Utils.isEqual(t, a[i]))
            {
                Utils.exch(a, lo, i);
                return;
            }
        }
        //throw new RuntimeException("Wrong partition!");
    }
    
    public static void sort(Comparable[] a, int k) {
        Comparable[] samples = getSampleArr(a, k);
        //Utils.show("Samples: ", samples);
        sort(a, 0, a.length - 1, samples, 0, samples.length-1);
    }
    
    public static int getMedian(Comparable[] samples, int lo, int hi) {
        //StdOut.printf("Median: lo = %d, hi = %d\n", lo, hi);
        if (lo > hi) return -1;
        else         return (lo + hi)/2;
    }
    
    public static void sort(Comparable[] a, int lo, int hi, Comparable[] samples, int sLo, int sHi) {
        if (lo >= hi) return;
        int median = getMedian(samples, sLo, sHi);
        if (median != -1) {
            swap(a, samples[median], lo, hi);
            int i = partition(a, lo, hi);
            //Utils.show("Internal:    ", a);
            sort(a, lo, i-1, samples, sLo, median-1);
            sort(a, i+1, hi, samples, median+1, sHi);
        } else {
            QuickSort.sort(a, lo, hi);
        }

    }
    
    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while(Utils.less(a[++i], v)) {
                if (i == hi) break;
            }
            
            while(Utils.less(v, a[--j])){
                if (j == lo) break;
            }
            
            if (i >= j) break;
            Utils.exch(a, i, j);
        }
        Utils.exch(a, lo, j);
        return j;
    }
    
    public static void main(String[] args) {
//        Integer[] a = Utils.genData(21, 100);
//        Utils.show("Raw: ", a);
//        //StdOut.println();
//        sort(a, 2);
//        //StdOut.println();
//        Utils.show("Sort:", a);
        Integer[] a = Utils.genData(200000000, 10000000);
        Integer[] b = a.clone();
        //Utils.show("Before Sort:", a);
        String s1 = "QuickSort";
        String s2 = "SampleSort";
        //String s1 = "QuickBentleyMcIlroy";
        Utils.sortCompare(a, b, s1, s2);
        for (int i = 0; i < a.length; i++) {
            if (!Utils.isEqual(a[i], b[i]))
                throw new RuntimeException("Two sort results are not consistent!");
        }
    }
}