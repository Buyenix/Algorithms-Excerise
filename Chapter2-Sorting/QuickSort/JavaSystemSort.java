public class JavaSystemSort {
    // cutoff to insertion sort, must be >= 1
    private static final int INSERTION_SORT_CUTOFF = 8;
    // cutoff to median-of-3 partitioning
    private static final int MEDIAN_OF_3_CUTOFF = 40;
    private JavaSystemSort() { }
//    private static int median3(Comparable[] a, int lo, int mid, int hi) {
//        return Utils.less(a[lo], a[mid])  ?
//               (Utils.less(a[mid], a[hi]) ? mid : Utils.less(a[lo], a[hi]) ? hi : lo) :
//               (Utils.less(a[hi], a[mid]) ? mid : Utils.less(a[hi], a[lo]) ? hi : lo) ;
//    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }
    
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (Utils.less(a[i], a[j]) ?
               (Utils.less(a[j], a[k]) ? j : Utils.less(a[i], a[k]) ? k : i) :
               (Utils.less(a[k], a[j]) ? j : Utils.less(a[k], a[i]) ? k : i));
    }    
    private static void sort(Comparable[] a, int lo, int hi) {
        int N = hi - lo + 1;
        if (N <= INSERTION_SORT_CUTOFF) {
            Utils.InsertionSort(a, lo, hi);
            return;
        } else if (N <= MEDIAN_OF_3_CUTOFF) {
            int m = median3(a, lo, lo+N/2, hi);
            Utils.exch(a, lo, m);
            //Utils.show("MMMM:", a);
        } else {
            int eps = N/8;
            int mid = lo + N/2;
            int m1 = median3(a, lo, lo+eps, lo+eps+eps);
            int m2 = median3(a, mid-eps, mid, mid+eps);
            int m3 = median3(a, hi-eps-eps, hi-eps, hi);
            int m = median3(a, m1, m2, m3);
            Utils.exch(a, lo, m);
        }
        
        int p = lo, i = lo;
        int q = hi + 1, j = hi + 1;
        Comparable v = a[lo];
        while(true) {
            while(Utils.less(a[++i], v)) if (i == hi) break;
            while(Utils.less(v, a[--j])) if (j == lo) break;
            
            if ((i==j) && Utils.isEqual(v, a[i])) {
                Utils.exch(a, ++p, i);
            }
            
            if (i >= j) break;
            Utils.exch(a, i, j);
            
            if (Utils.isEqual(a[i], v)) Utils.exch(a, ++p, i);
            if (Utils.isEqual(a[j], v)) Utils.exch(a, --q, j);
        }
        
        //Utils.show("XXXX:", a);
        // For now, j is the last index whose value is less than a[lo].
        // j + 1 is the first index whose value is more than a[lo].
        //int lastLessIndex = j;
        i = j + 1;
        for (int k = lo; k <= p; k++) {
            Utils.exch(a, k, j--);
        }
        for (int k = hi; k >= q; k--) {
            Utils.exch(a, k, i++);
        }
        
        sort(a, lo, j);
        sort(a, i, hi);
    }
    
    public static void main(String[] args) {
        Integer[] a = Utils.genData(20000000, 10000000);
        Integer[] b = a.clone();
        //Utils.show("Before Sort:", a);
        String s1 = "QuickSort";
        String s2 = "JavaSystemSort";
        //String s1 = "QuickBentleyMcIlroy";
        Utils.sortCompare(a, b, s1, s2);
        
        //Utils.show("After QuickSort:     ", a);
        //Utils.show("After JavaSystemSort:", b);
    }
}