public class FindOneLocalMinValueForArray {
    private static int find(int[] a) {
        if (a == null || a.length == 0) return -1;
        if (a.length == 1) return 0;
        if (a.length == 2) return a[0] > a[1] ? 1 : 0;
        
        int lo = 0, hi = a.length-1;
        while(lo < hi) {
            int mid = (lo + hi)/2;
            if (a[mid-1] > a[mid] && a[mid] < a[mid+1]) return mid;
            else if (a[mid-1] > a[mid+1]) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }
    
    public static void main(String[] args) {
        int[] a = {9, 8, 7, 11, 12, 2, 13};
        int localMinPos = find(a);
        StdOut.printf("Local min pos: %d\n", localMinPos);
    }
}