import java.util.Comparator;

public class FastMinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    
    public FastMinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN+1];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public Key min() {
        if (size() <= 0) throw new RuntimeException("PQ underflow!");
        return pq[1];
    }
    
    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }
    
    public void fastInsert(Key v) {
        pq[++N] = v;
        fastSwim(N);
    }
    
    public Key delMin() {
        Key min = pq[1];
        exch(1, N--);
        sink(1);
        return min;
    }
    
    public boolean isMinPQ() {
        return isMinPQ(1);
    }
    
    public boolean isMinPQ(int k) {
        if (k > N) return true;
        int left = 2*k;
        int right = 2*k+1;
        if (left <= N && less(left, k)) return false;
        if (right <= N && less(right, k)) return false;
        return isMinPQ(left) && isMinPQ(right);
    }
    
    private void swim(int k) {
        while(k > 1 && less(k, k/2)) {
            exch(k, k/2);
            k /= 2;
        }
    }
    
    private void fastSwim(int k) {
        int n  = (int)(Math.log(k)/Math.log(2));
        int left = n, right = 0;
        int mid = -1;
        while(left >= right) {
            mid = left + (right-left)/2;
            int pid = (int)(k/Math.pow(2, mid));
            if (less(k, pid)) right = mid+1;
            else if (less(pid, k)) left = mid-1;
            else break;
        }
        
        int i = 0;
        Key t = pq[k];
        //StdOut.print("left = " + left);
        while (i++ < left) {
            pq[k] = pq[k/2];
            k /= 2;
        }
        //StdOut.println(", k = " + k);
        pq[k] = t;
    }
    
    private void sink(int k) {
         while(2*k <= N) {
            int j = 2*k;
            if ((j < N) && less(j+1, j)) j++;
            if (less(k, j)) break;
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
        MinPQ pq = new MinPQ(N);
        StdOut.print("Insert: ");
        for (int i = 0; i < N; i++) {
            Integer rand = StdRandom.uniform(1000);
            //StdOut.print("Insert: ");
            StdOut.print(rand + " ");
            pq.fastInsert(rand);
        }
        StdOut.println();
        
        StdOut.print("DelMin: ");
        while(!pq.isEmpty()) {
            StdOut.print(pq.delMin() + " ");
        }
        StdOut.println();
    }
}