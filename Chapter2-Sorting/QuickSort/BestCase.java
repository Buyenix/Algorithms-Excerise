public class BestCase {
    public static Integer[] genData(int N) {
        Integer[] data = new Integer[N];
        for (int i = 0; i < N; i++)
            data[i] = i;
        int lo = 0, hi = N-1;
        setMiddle(data, lo, hi);
        return data;
    }
    
    public static void setMiddle(Integer[] a, int lo, int hi) {
        StdOut.printf("lo = %d, hi = %d\n", lo, hi);
        if (lo >= hi) return;
        int mid = lo + (hi - lo)/2;
        setMiddle(a, lo, mid-1);
        setMiddle(a, mid+1, hi);
        Utils.exch(a, lo, mid);
        StdOut.printf("a[%d] = %d\n", lo, a[lo]);
    }
    
    public static void main(String[] args) {
        Integer[] data = genData(9);
        Utils.show("", data);
    }
}