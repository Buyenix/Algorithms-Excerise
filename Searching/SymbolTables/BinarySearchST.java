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
    
    private void resize(int newSize) {
        Key[] newKeys = (Key[]) new Comparable[newSize];
        Value[] newVals = (Value[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            newKeys[i] = keys[i];
            newVals[i] = vals[i];
        }
        keys = newKeys;
        vals = newVals;
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
    public Value get(Key key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else                                      return null;
    }
    
    public Value get(int index)
    {
        if (isEmpty()) return null;
        if (index < N) return vals[index];
        else           return null;
    }
    
    public boolean contains(Key key)
    {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i])==0) return true;
        else                                    return false;
    }
    
    public void put(Key key, Value val)
    {
        if (N == keys.length) resize(2*N);
        int cmp = -1;
        if (!isEmpty()) cmp = key.compareTo(keys[N-1]);
        if (cmp > 0) {
            keys[N] = key;
            vals[N] = val;
            N++;
        } else if(cmp == 0) {
            vals[N-1] = val;
        } else {
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
    }
    
    public void delete(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            for (int j = i; j < N-1; j++) {
                keys[i] = keys[i+1];
                vals[i] = vals[i+1];
            }
            N--;
            if (N > 0 && N == keys.length/4) resize(keys.length/2);
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
    
    public Iterable<Key> keys()
    {
        Queue<Key> q = new Queue<Key>();
        for (int i = 0; i < N; i++) {
            q.enqueue(keys[i]);
        }
        return q;
    }
}













