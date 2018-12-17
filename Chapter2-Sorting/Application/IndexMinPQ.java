public class IndexMinPQ<Key extends Comparable<Key>> {
    private Key[] keys;
    private int[] pq;
    private int[] qp;
    private int N;
    
    public IndexMinPQ(int maxN) {
        keys = (Key[]) new Comparable[maxN+1];
        pq   = new int[maxN+1];
        qp   = new int[maxN+1];
        N    = 0;
        for (int i = 0; i < maxN + 1; i++) {
            qp[i] = -1;
        }
    }
    
    public boolean isEmpty() {   
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void insert(int k, Key v) {
        keys[k] = v;
        pq[++N] = k;
        qp[k] = N;
        swim(N);
    }
    
//    public Key delMin() {
//        Key value = keys[pq[1]];
//        exch(1, N--);
//        sink(1);
//        keys[pq[N+1]] = null;
//        qp[pq[N+1]] = -1;
//        return value;
//    }

    public int delMin() {
        int value = pq[1];
        exch(1, N--);
        sink(1);
        keys[pq[N+1]] = null;
        qp[pq[N+1]] = -1;
        return value;
    }
    public boolean contains(int k) {
        return qp[k] != -1;
    }
    
    public Key min() {
        return keys[pq[1]];
    }
    
    public int minIndex() {
        return pq[1];
    }
    
    public void change(int k, Key v) {
        keys[k] = v;
        swim(qp[k]);
        sink(qp[k]);
    }
    
    public void delete(int k) {
        exch(qp[k], N--);
        swim(qp[k]);
        sink(qp[k]);
        keys[pq[N+1]] = null;
        qp[pq[N+1]] = -1;
    }
    
    private void swim(int k) {
        while(k > 1 && less(k, k/2)) {
            exch(k, k/2);
            k /= 2;
        }
    }
    
    private void sink(int k) {
        while(2*k <= N) {
            int i = 2*k;
            if (i < N && less(i+1, i)) i++;
            if (less(k, i)) break;
            exch(k, i);
            k = i;
        }
    }
    
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }
    
    private void exch(int i, int j) {
        exch(qp, pq[i], pq[j]);
        exch(pq, i, j);
    }
    
    private void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    public static void main(String[] args) {
        int N = 10;
        IndexMinPQ pq = new IndexMinPQ(N);
        StdOut.print("Insert: ");
        for (int i = 0; i < N; i++) {
            Integer rand = StdRandom.uniform(1000);
            StdOut.print(rand + " ");
            pq.insert(i, rand);
        }
        StdOut.println();
        
        StdOut.print("DelMin: ");
        while(!pq.isEmpty()) {
            StdOut.print(pq.delMin() + " ");
        }
        StdOut.println();
    } 
}