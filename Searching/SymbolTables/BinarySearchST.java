public class BinarySearchST<Key extends Comparable<Key>, Value>
{
    private Key[] keys;
    private Value[] vals;
    private int N;
    
    public BinarySearchST(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    
    public int rank(Key key)
    {
        int lo = 0, hi = N-1;
        while(lo <= hi) {
            int mid = lo + (hi-lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
    
    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }
    public Key get(Key key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return keys[i];
        else                                      return null;
    }
    
    public boolean contains(Key key)
    {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i])==0) return true;
        else                                    return false;
    }
    
    public void put(Key key, Value val)
    {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0 ) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    
    public void delete(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            for (int j = i; j < N-1; j++) {
                keys[i] = keys[i+1];
                vals[i] = vals[i+1];
            }
            N--;
        }
        return;
    }
    
    public Key min()
    {
        return keys[0];
    }
    
    public Key max()
    {
        return keys[N-1];
    }
    
    public Key select(int k)
    {
        return keys[k];
    }
    
    public Key ceiling(Key key)
    {
        int i = rank(key);
        return keys[i];
    }
    
    public Key floor(Key key)
    {
        int i = rank(key);
        if (i >= N) return null;
        if (key.compareTo(keys[i]) == 0) {
            return key;
        } else {
            // key.compareTo(keys[i]) < 0
            if (i >= 1) return keys[i-1];
            else return null;
        }
    }
    
    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> q = new Queue<Key>();
        int lo_idx = rank(lo);
        int hi_idx = rank(hi);
        for (int i = lo_idx; i < hi_idx; i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[hi_idx]);
        }
        return q;
    }
}













