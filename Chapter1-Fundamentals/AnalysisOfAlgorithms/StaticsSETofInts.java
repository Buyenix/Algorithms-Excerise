import java.util.Arrays;

public class StaticsSETofInts {
    private int[] a;
    public StaticsSETofInts(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i<keys.length; i++) {
            a[i] = keys[i];
        }
        Arrays.sort(a);
    }
    
    public boolean contains(int key) {
        return rank(key) != -1;
    }
    
    public int howMany(int key) {
        int upperBD = rankBD(key, true);
        int lowerBD = rankBD(key, false);
        StdOut.println("UpperBD: "+ upperBD + " ,LowerBD: " + lowerBD);
        if (upperBD < 0 || lowerBD < 0) return 0;
        else return upperBD - lowerBD + 1;
    }
    
    public int howManyBetter(int key) {
        int lowerBD = rankBD(key, false);
        if (lowerBD < 0) return 0;
        int cnt = 0;
        while (a[lowerBD++] == key) cnt++;
        return cnt;
    }
    
    private int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while(lo < hi) {
            int mid = (lo+hi)/2;
            if      (a[mid] > key) hi = mid-1;
            else if (a[mid] < key) lo = mid+1;
            else                   return mid;
        }
        return -1;
    }  
    
    //Get the upper/lower boundary
    private int rankBD(int key, boolean isUpper) {
        int lo = 0;
        int hi = a.length - 1;
        while(lo < hi) {
            int mid = (lo+hi)/2;
            if      (a[mid] > key) hi = mid-1;
            else if (a[mid] < key) lo = mid+1;
            else if (!isUpper && mid > 0 && a[mid-1]==key) hi = mid;
            else if (isUpper && mid < a.length - 1 && a[mid+1]==key) lo = mid;
            else return mid;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        In in1 = new In(args[0]);
        int[] vals = in1.readAllInts();
        StaticsSETofInts set = new StaticsSETofInts(vals);
        int v1 = 5, v2 = 2;
        int c1 = set.howMany(v1);
        int c2 = set.howMany(v2);
        StdOut.println("Int : " + v1 + ", count: " + c1);
        StdOut.println("Int : " + v2 + ", count: " + c2);
    }
}
