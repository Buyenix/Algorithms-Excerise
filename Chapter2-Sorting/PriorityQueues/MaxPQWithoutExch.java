import java.util.Comparator;

public class MaxPQWithoutExch<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    
    public MaxPQWithoutExch(int maxN) {
        pq = (Key[]) new Comparable[maxN+1];
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
        Key value = pq[k];
        while(k > 1 && less(pq[k/2], value)) {
            pq[k] = pq[k/2];
            k /= 2;
        }
        pq[k] = value;
    }
    
    private void sink(int k) {
        Key value = pq[k];
         while(2*k <= N) {
            int j = 2*k;
            if ((j < N) && less(pq[j], pq[j+1])) j++;
            if (less(pq[j], value)) break;
            pq[k] = pq[j];
            k = j;
        }
         pq[k] = value;
    }
    
    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }
        
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    
    public static void main(String[] args) {
        int N = 40;
        MultiwayMaxPQ pq = new MultiwayMaxPQ(N, 3);
        StdOut.print("Insert: ");
        for (int i = 0; i < N; i++) {
            Integer rand = StdRandom.uniform(40);
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