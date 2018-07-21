public class FractionBinarySearch {
    public static int rank(double key, double[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int N = arr.length; // How to define N is a question here. I think just use arr.length may not be good enough.
        double threshold = 1 / (N * N);
        
        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if ((arr[mid] - key) < threshold) return mid;
            else if (arr[mid] > key) lo = mid + 1;
            else                     hi = mid - 1;
        }
        return -1;
    }
}