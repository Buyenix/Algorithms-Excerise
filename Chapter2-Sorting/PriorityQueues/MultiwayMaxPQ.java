import java.util.Comparator;

public class MultiwayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    private int T = 2;
    
    public MultiwayMaxPQ(int maxN, int t) {
        pq = (Key[]) new Comparable[maxN+1];
        N = 0;
        T = t;
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
    
    private void swim(int k) {
        while(true) {
            int idx = getRootIdx(k);
            if (k <= 1 || !less(idx, k)) break;
            exch(k, idx);
            k = idx;
        }
    }
    
    private void sink(int k) {
         while(getLowerIdx(k) <= N) {
            int j = findCmpIdx(k);
            if (j == -1) throw new RuntimeException("Idx isn't valid!");
            if (less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }
    
    private int getRootIdx(int k) {
        return (k + T - 2)/T;
    }
    
    private int getLowerIdx(int k) {
        return T*k-T+2;
    }
    
    private int findCmpIdx(int k) {
        int lowerIdx = getLowerIdx(k);
        int upperIdx = lowerIdx + T - 1;
        int idx = lowerIdx;
        if (idx > N) return -1;
        for (int i = idx + 1; (i <= upperIdx) && (i <= N); i++) {
            if (less(idx, i)) {
                idx = i;
            }
        }
        return idx;
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
        int N = 20;
        MultiwayMaxPQ pq = new MultiwayMaxPQ(N, 3);
        StdOut.print("Insert: ");
        for (int i = 0; i < N; i++) {
            Integer rand = StdRandom.uniform(20);
            StdOut.print(rand + " ");
            pq.insert(rand);
        }
        StdOut.println();
        
        StdOut.print("DelMax: ");
        while(!pq.isEmpty()) {
            StdOut.print(pq.delMax() + " ");
        }
        StdOut.println();
    }
}