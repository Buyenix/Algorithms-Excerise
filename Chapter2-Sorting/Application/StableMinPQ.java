public class StableMinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int[] time;
    private int N = 0;
    private int timestamp = 1;
    
    public StableMinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN+1];
        time = new int[maxN+1];
        N = 0;
    }
    
    public StableMinPQ() {
        this(1);
    }
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private void resize(int size) {
        Key[] newPQ = (Key[]) new Comparable[size + 1];
        int[] newTime = new int[size + 1];
        for (int i = 1; i <= N; i++) {
            newPQ[i] = pq[i];
            newTime[i] = time[i];
        }
        pq = newPQ;
        time = newTime;       
    }
    
    public void insert(Key v) {
        if (N == pq.length - 1) {
            resize(2*N);
        }
        pq[++N] = v;
        time[N] = ++timestamp;
        swim(N);
        assert(isMinPQ());
    }
    
    public Key delMin() {
        Key min = min();
        exch(1, N--);
        StdOut.printf("Delete " + min.toString() + ", time %d\n", time[N+1]);
        pq[N+1] = null;
        time[N+1] = 0;
        sink(1);
        if (N > 0 && N == (pq.length-1)/4) {
            resize(2*N);
        }
        assert(isMinPQ());
        return min;
    }
    
    public Key min() {
        if (size() <= 0) throw new RuntimeException("PQ underflow!");
        return pq[1];
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
        int cmp = pq[i].compareTo(pq[j]);
        if (cmp < 0)      return true;
        else if (cmp > 0) return false;
        else              return time[i] < time[j];
    }
    
    private void exch(int i, int j) {
        Key t1 = pq[i];
        pq[i] = pq[j];
        pq[j] = t1;
        
        int t2 = time[i];
        time[i] = time[j];
        time[j] = t2;
    }
    
    public static void main(String[] args) {
        StableMinPQ<Integer> pq = new StableMinPQ<Integer>();
        StdOut.print("Insert: ");
        for (int i = 0; i < 10; i++) {
            Integer rand1 = StdRandom.uniform(10);
            StdOut.printf(rand1.toString() + " ");
            pq.insert(rand1);
        }
        StdOut.println();
        
        //StdOut.print("DelMin: ");
        while(!pq.isEmpty()) {
            pq.delMin();
            //StdOut.print(pack.toString() + " ");
        }
        StdOut.println();
    }
}