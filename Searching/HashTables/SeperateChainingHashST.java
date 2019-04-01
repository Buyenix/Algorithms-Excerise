public class SeperateChainingHashST<Key, Value>
{
    private static final int INIT_CAPACITY = 4;
    private int N; // number of key-value pairs
    private int M; // number of sequential search st
    private SequentialSearchST<Key, Value>[] st;
    
    public SeperateChainingHashST() { this(INIT_CAPACITY); }
    private SeperateChainingHashST(int M)
    {
        this.M = M;
        this.N = 0;
        st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }
    
    private void resize(int M)
    {
        SeperateChainingHashST<Key, Value> newHashST = new SeperateChainingHashST(M);
        for (int i = 0; i < this.M; i++) {
            for (Key key : this.st[i].keys()) {
                newHashST.put(key, this.st[i].get(key));
            }
        }
        this.st = newHashST.st;
        this.M = newHashST.M;
        this.N = newHashST.N;
    }
    
    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    
    public void put(Key key, Value val)
    {
        if (key == null) throw new IllegalArgumentException("Key is null for put!");
        if (val == null) {
            delete(key);
            return;
        }
        if (N > 10*M) resize(2*M);
        int index = hash(key);
        if (!st[index].contains(key)) N++;
        st[index].put(key, val);
    }
    
    public Value get(Key key)
    {
        if (key == null) throw new IllegalArgumentException("Key is null for get!");
        int index = hash(key);
        return st[index].get(key);
    }
    
    public void delete(Key key)
    {
        if (key == null) return;
        int index = hash(key);
        if (st[index].contains(key)) this.N--;
        st[index].delete(key);
    }
    
    public Iterable<Key> keys()
    {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key :st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }
    
    public int size() { return this.N; }
    public boolean isEmpty() { return size() == 0; }
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null for contains!");
        return get(key) != null;
    }
    
    public static void main(String[] args)
    {
        SeperateChainingHashST<String, Integer> st = new SeperateChainingHashST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
    }
}