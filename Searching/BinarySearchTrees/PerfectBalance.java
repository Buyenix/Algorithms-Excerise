import java.util.Arrays;
public class PerfectBalance
{
    private static void perfect(String[] a, BST bst)
    {
        Arrays.sort(a);
        perfect(a, bst, 0, a.length-1);
        StdOut.println();
    }
    
    private static void perfect(String[] a, BST bst, int lo, int hi)
    {
        if (lo > hi) return;
        int mid = lo + (hi - lo)/2;
        String s = a[mid];
        bst.put(s, mid);
        StdOut.print(a[mid] + " ");
        perfect(a, bst, lo, mid-1);
        perfect(a, bst, mid+1, hi);
    }
    
    public static void main(String[] args)
    {
        String s = "E A S Y Q U E S T I O N";
        String[] strs = s.split(" ");
        BST<String, Integer> bst = new BST<String, Integer>();
        perfect(strs, bst);
    }
}