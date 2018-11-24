public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] data;
    int N;
    
    public OrderedArrayMaxPQ() {
        data = (Key[]) (new Comparable[1]);
        N = 0;
    }
    
    public OrderedArrayMaxPQ(int max) {
        if (max <= 0) throw new RuntimeException("Not valid size!");
        data = (Key[]) (new Comparable [max]);
        N = 0;
    }
    
    public void insert(Key v) {
        if (N == data.length) {
            Key[] newData = (Key[]) (new Comparable[2 * N]);
            for (int i = 0; i < N; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        
        if (!isSorted(data, 0, N-1, true/*isIncr*/)) {
            throw new RuntimeException("Current data is not sorted!");
        }
        
        int i = N-1;
        while (i >= 0 && less(v, data[i])) {
            data[i+1] = data[i];
            i--;
        }
        data[i+1] = v;
        N++;
    }
    
    public Key max() {
        if (isEmpty()) throw new RuntimeException("Empty!");
        return data[N-1];
    }
    
    public Key delMax() {
        if (isEmpty()) throw new RuntimeException("Empty!");
        if (N <= data.length/4) {
            Key[] newData = (Key[]) (new Comparable[data.length/2]);
            for (int i = 0; i < N; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        return data[--N];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private boolean less(Key v, Key w) {
        if (v == w) return false;
        return v.compareTo(w) < 0;
    }
    
    private void exch(Key[] a, int i, int j) {
        if (i == j) return;
        Key t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    private boolean isSorted(Key[] a, int lo, int hi, boolean isIncr) {
        for (int i = lo+1; i <= hi; i++) {
            if ((isIncr && less(a[i], a[i-1])) ||
                (!isIncr && less (a[i-1], a[i]))) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
}