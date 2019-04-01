public class LinearProbingHashDuplicateST<Key, Value>
{
    private static final int INIT_CAPACITY = 4;
    private int N;
    private int M;
    private Key[] keys;
    private Value[] vals;
    
    public LinearProbingHashDuplicateST() { this(INIT_CAPACITY); }
    private LinearProbingHashDuplicateST(int M)
    {
        this.M = M;
        this.N = 0;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
    
    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    
    private void resize(int M)
    {
        LinearProbingHashDuplicateST<Key, Value> newHashST = new LinearProbingHashDuplicateST(M);
        for (int i = 0; i < this.M; i++) {
            if (keys[i] != null && vals[i] != null) {
                newHashST.put(keys[i], vals[i]);
            }
        }
        this.M = newHashST.M;
        this.N = newHashST.N;
        this.keys = newHashST.keys;
        this.vals = newHashST.vals;
    }
    
    public void put(Key key, Value val)
    {
        if (key == null) throw new IllegalArgumentException("Key is null for put!");
        if (val == null) {
            delete(key);
            return;
        }
        
        if (N >= M/2) resize(2*M);
        int i = hash(key);
        for (; vals[i] != null; i = (i+1)%M) {
//            if (key.equals(keys[i])) {
//                vals[i] = val;
//                return;
//            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    
    public Value get(Key key)
    {
        if (key == null) throw new IllegalArgumentException("Key is null for get!");
        for (int i = hash(key); vals[i] != null; i = (i+1)%M) {
            if (key.equals(keys[i])) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key)
    {
        if (key == null) return;
        if (!contains(key)) return;
        
        int i = hash(key);
        while( !key.equals(keys[i])) {
            i = (i + 1)%M;
        }
        while (key.equals(keys[i])) {
            keys[i] = null;
            vals[i] = null;
            i = (i + 1)%M;
        }
        
        i = (i + 1)%M;
        while (keys[i] != null) {
            Key keyToReach = keys[i];
            Value valToReach = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToReach, valToReach);
            i = (i + 1)%M;
        }
        N--;
        
        if (N > 0 && N <= 8/M) resize(M/2);
    }
    
    public Iterable<Key> keys()
    {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M ; i++) {
            if (keys[i] == null || vals[i] == null) continue;
            queue.enqueue(keys[i]);
        }
        return queue;
    }
    
    public int size() { return this.N; }
    public boolean isEmpty() { return size() == 0; }
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null for contains!");
        return get(key) != null;
    }
    
    public static void main(String[] args) { 
        LinearProbingHashDuplicateST<String, Integer> st = new LinearProbingHashDuplicateST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
    }     
}