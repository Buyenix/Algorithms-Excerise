public class MinMaxPQ<Key extends Comparable<Key>>{
    MaxPQ<Key> max;
    MinPQ<Key> min;
    int N;
    
    public MinMaxPQ(int maxN) {
        max = new MaxPQ<Key>(maxN);
        min = new MinPQ<Key>(maxN);
        N = 0;
    }
    
    public boolean isEmpty() {   
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void insert(Key v) {
        max.insert(v);
        min.insert(v);
        N++;
    }
    
    public Key delMax() {
        if (size() <= 0) {
            throw new RuntimeException("PQ underflow!");
        }
        Key v = max.delMax();
        N--;
        return v;
    }
    
    public Key delMin() {
        if (size() <= 0) {
            throw new RuntimeException("PQ underflow!");
        }
        Key v = min.delMin();
        N--;
        return v;
    }
    
    public Key max() {
        if (size() <= 0) {
            throw new RuntimeException("PQ underflow!");
        }
        return max.max();
    }
    
    public Key min() {
        if (size() <= 0) {
            throw new RuntimeException("PQ underflow!");
        }
        return min.min();
    }
    
    public static void main(String[] args) {
        int N = 10;
        MinMaxPQ pq = new MinMaxPQ<Integer>(N);
        StdOut.print("Raw:  ");
        for (int i = 0; i < N; i++) {
            Integer rand = StdRandom.uniform(1000);
            StdOut.print(rand + " ");
            pq.insert(rand);
        }
        StdOut.println();
        
        StdOut.print("Sort: ");
        while(!pq.isEmpty()) {
            StdOut.print(pq.max() + " ");
            StdOut.print(pq.delMax() + " ");
            StdOut.print(pq.min() + " ");
            StdOut.print(pq.delMin() + " ");
        }
        StdOut.println();
    }
}