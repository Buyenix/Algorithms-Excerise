public class FastThreeWayPartition {
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        StdOut.printf("####Sort a[%d:%d]\n", lo, hi);
        int p = lo + 1, i = lo + 1;
        int q = hi, j = hi;
        Comparable v = a[lo];
        while (i <= j) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                i++;
            } else if (cmp > 0) {
                int cmp2 = a[j].compareTo(v);
                if (cmp2 < 0) {
                    Utils.exch(a, i++, j--);
                } else if (cmp2 > 0) {
                    j--;
                } else {
                    Utils.exch(a, j--, q--);
                }
            } else {
                Utils.exch(a, p++, i++);
            }
        }
        SwapArr(a, lo, hi, p, q, i);
        
        sort(a, lo, lo+i-p-1);
        sort(a, hi-q+i, hi);
    }
    
    public static void SwapArr(Comparable[] a, int lo, int hi, int p, int q, int i) {
        StdOut.printf("lo = %d, hi = %d, p = %d, q = %d, i = %d\n", lo, hi, p, q, i);
        Comparable[] t = new Comparable[a.length];
        Utils.show("Before swap:", a);
        for (int m = lo; m <= hi; m++) t[m] = a[m];
        for (int m = p; m < i; m++) a[lo + m - p] = t[m];
        for (int m = i; m <= q; m++) a[hi - q + m] = t[m];
        for (int m = lo + i - p; m < hi + i - q; m++) a[m] = t[lo];
        Utils.show("After swap:", a);
    }
    

    public static void main(String[] args) {
        Integer[] a = Utils.genData(10);
        Utils.show("Raw: ", a);
        StdOut.println();
        sort(a, 0, a.length-1);
        StdOut.println();
        Utils.show("Sort:", a);
    }
}