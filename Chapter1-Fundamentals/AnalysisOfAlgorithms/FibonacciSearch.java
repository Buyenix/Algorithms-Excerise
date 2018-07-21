import java.util.Arrays;
public class FibonacciSearch {
    private static void makeFibonacciArray(int[] arr) {
        //Suppose arr.length > 2
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
    }
    
    private static int rank(int[] arr, int key) {
        int[] FbArr = new int[10];
        makeFibonacciArray(FbArr);
        //for(int ii = 0; ii < FbArr.length; ii++) StdOut.println(FbArr[ii]);
        int k = 0;
        while(FbArr[k] < arr.length) k++;
        int[] newArr = Arrays.copyOf(arr, FbArr[k] - 1);
        for(int i = arr.length; i < newArr.length; i++) {
            if (i > arr.length) newArr[i] = arr[arr.length-1];
        }
        
        int lo = 0; 
        int hi = newArr.length - 1;
        // Similar with binary search which is lo + (hi-lo)/2 + (hi-lo)/2 division, 
        // Fibonacci search is lo + Fb[k-2] + Fb[k-1]. So the initial [mid] should
        // be Fb[k-2] + lo or Fb[k-1] + lo.
        while(lo <= hi) {
            int mid = lo + FbArr[k-2] - 1;
            if (newArr[mid] > key) {
                hi = mid - 1;
                k -= 2;
            } else if (newArr[mid] < key) {
                lo = mid + 1;
                k -= 1;
            } else {
                if (mid < arr.length) return mid;
                else return arr.length - 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] a = {1, 5, 15, 22, 25, 31, 39, 42, 47, 49, 59, 68, 88, 88, 88, 88, 88};
        int i = rank(a, 49);
        StdOut.println(i);
    }
}