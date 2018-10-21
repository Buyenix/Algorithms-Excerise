public class Triplicates {
    private static Integer[] genOneList(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(1, 100);
        return a;
    }
    
    public static int searchCommonElement(Comparable[] a1, Comparable[] a2, Comparable[] a3) {
        // Sort two arrays. Compare complexity is linearithmic.
        Merge.sort(a1);
        Merge.sort(a2);
        for(int i=0; i<a3.length; i++) {
            Comparable element = a3[i];
            if (binarySearch(a1, element) && binarySearch(a2, element)) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean binarySearch(Comparable[] a, Comparable key) {
        int N = a.length;
        int lo = 0;
        int hi = N-1;
        while(lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (less(a[mid], key)) {lo = mid+1;}
            else if (less(key, a[mid])) {hi = mid-1;}
            else return true;
        }
        return false;
    }
    
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void main(String[] args) {
        int N = 10;
        Integer[] a1 = genOneList(N);
        Integer[] a2 = genOneList(N);
        Integer[] a3 = genOneList(N);
        //a1[5] = a2[1] = a3[9] = 1;
        show("A1: ", a1);
        show("A2: ", a2);
        show("A3: ", a3);
        int p = searchCommonElement(a1, a2, a3);
        show("Sorted A1: ", a1);
        show("Sorted A1: ", a2);
        if (p >= 0) {
            StdOut.println("Success! The common element is " + a3[p]);
        } else {
            StdOut.println("Failed!");
        }
    }
    
    private static void show(String preString, Comparable[] a) {
        StdOut.print(preString);
        for(int i=0; i<a.length; i++) {
            StdOut.print(a[i]+ " ");
        }
        StdOut.println();
    }
}