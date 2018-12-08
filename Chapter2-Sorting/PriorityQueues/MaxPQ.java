import java.util.Comparator;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    
    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN+1];
    }
    
    public MaxPQ(Key[] a) {
        int len = a.length;
        pq = (Key[]) new Comparable[len+1];
        for (int i = 0; i < len; i++) {
            pq[i+1] = a[i];
        }
        N = len;
        
        for (int i = N/2; i >= 1; i--) {
//            for (int j = 1; j <= N; j++) {
//                StdOut.print(pq[j] + " ");
//            }
//            StdOut.println();
            sink(i);
        }
    }
    
    public Key max() {
        if (size() <= 0) throw new RuntimeException("PQ underflow!");
        return pq[1];
    }
    
    public boolean isEmpty() {   
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }
    
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        return max;
    }
    
    public boolean isMaxPQ() {
        return isMaxPQ(1);
    }
    
    public boolean isMaxPQ(int k) {
        if (k > N) return true;
        int left = 2*k;
        int right = 2*k+1;
        if (left <= N && less(k, left)) return false;
        if (right <= N && less(k, right)) return false;
        return isMaxPQ(left) && isMaxPQ(right);
    }
    
    private void swim(int k) {
        while(k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k /= 2;
        }
    }
    
    private void sink(int k) {
         while(2*k <= N) {
            int j = 2*k;
            if ((j < N) && less(j, j+1)) j++;
            if (less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }
    
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
    
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    
    public static void main(String[] args) {
        int N = 10;
        Integer[] a = new Integer[N];
        StdOut.print("Array: ");
        for (int i = 0; i < N; i++) {
            Integer rand = StdRandom.uniform(1000);
            StdOut.print(rand + " ");
            a[i] = rand;
        }
        StdOut.println();
        
        MaxPQ pq = new MaxPQ(a);
        StdOut.print("Heap:  ");
        while(!pq.isEmpty()) {
            StdOut.print(pq.delMax() + " ");
        }
        StdOut.println();
    }
}