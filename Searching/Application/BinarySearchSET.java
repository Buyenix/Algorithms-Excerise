public class BinarySearchSET<Key extends Comparable<Key>, Value>
{
    private Key[] keys;
    private int N;
    
    public BinarySearchSET(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
    }
    
    private void resize(int newSize) {
        Key[] newKeys = (Key[]) new Comparable[newSize];
        for (int i = 0; i < N; i++) {
            newKeys[i] = keys[i];
        }
        keys = newKeys;
    }
    public int rank(Key key)
    {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
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
    
    public boolean contains(Key key)
    {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        int i = rank(key);
        if (i < N && key.compareTo(keys[i])==0) return true;
        else                                    return false;
    }
    
    public void add(Key key)
    {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (N == keys.length) resize(2*N);
        int cmp = -1;
        if (!isEmpty()) cmp = key.compareTo(keys[N-1]);
        if (cmp > 0) {
            keys[N] = key;
            N++;
        } else if(cmp == 0) {
          return;
        } else {
            int i = rank(key);
            if (i < N && key.compareTo(keys[i]) == 0 ) {
                return;
            }
            for (int j = N; j > i; j--) {
                keys[j] = keys[j-1];
            }
            keys[i] = key;
            N++;
        }
        assert check();
    }
    
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            for (int j = i; j < N-1; j++) {
                keys[i] = keys[i+1];
            }
            N--;
            if (N > 0 && N == keys.length/4) resize(keys.length/2);
        }
        assert check();
    }
    
    public Key min()
    {
        if (isEmpty()) throw new IllegalArgumentException("called min() with empty symbol table");
        return keys[0];
    }
    
    public Key max()
    {
        if (isEmpty()) throw new IllegalArgumentException("called max() with empty symbol table");
        return keys[N-1];
    }
    
    public Key select(int k)
    {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }
    
    public Key ceiling(Key key)
    {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        int i = rank(key);
        return keys[i];
    }
    
    public Key floor(Key key)
    {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
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
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null"); 
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
    
        private boolean check() {
        return isSorted() && rankCheck();
    }

    // are the items in the array in ascending order?
    private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i-1]) < 0) return false;
        return true;
    }

    // check that rank(select(i)) = i
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }
}













