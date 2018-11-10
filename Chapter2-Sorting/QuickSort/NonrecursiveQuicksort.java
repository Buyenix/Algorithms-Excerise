public class NonrecursiveQuicksort {
    public static void sort(Comparable[] a) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        stack.push(a.length-1);
        while(!stack.isEmpty()) {
            sort(a, stack);
        }
    }
    
    public static void sort(Comparable[] a, Stack<Integer> stack) {
        int hi = stack.pop();
        int lo = stack.pop();
        if (lo >= hi) return;
        int i = partition(a, lo, hi);
        //Push the larger subarray first
        if (i-lo > hi-i) {
            stack.push(lo);
            stack.push(i-1);
            
            stack.push(i+1);
            stack.push(hi);
        } else {
            stack.push(i+1);
            stack.push(hi);
            
            stack.push(lo);
            stack.push(i-1);
        }
    }
    
    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while(Utils.less(a[++i], v)) {if (i == hi) break;} 
            while(Utils.less(v, a[--j])) {if (j == lo) break;}
            if (i >= j) break;
            Utils.exch(a, i, j);
        }
        Utils.exch(a, lo, j);
        return j;
    }
    
    private static Integer[] genData(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, 1000);
        return a;
    }
    
    public static void main(String[] args) {
        Integer[] a = genData(10);
        Utils.show("Raw: ", a);
        sort(a);
        Utils.show("Sort:", a);
    }
}