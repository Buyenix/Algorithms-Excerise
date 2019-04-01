// The key type is primitive int
public class LinearProbingHashSTint<Value>
{
    private static final int INIT_CAPACITY = 4;
    private int N;
    private int M;
    private int[] keys;
    private Value[] vals;
    
    public LinearProbingHashSTint() { this(INIT_CAPACITY); }
    private LinearProbingHashSTint(int M)
    {
        this.M = M;
        this.N = 0;
        keys = new int[M];
        vals = (Value[]) new Object[M];
    }
    
    private int hash(int key)
    {
        return key % M;
    }
    
    private void resize(int M)
    {
        LinearProbingHashSTint<Value> newHashST = new LinearProbingHashSTint(M);
        for (int i = 0; i < this.M; i++) {
            if (vals[i] != null) {
                newHashST.put(keys[i], vals[i]);
            }
        }
        this.M = newHashST.M;
        this.N = newHashST.N;
        this.keys = newHashST.keys;
        this.vals = newHashST.vals;
    }
    
    public void put(int key, Value val)
    {
        //if (key == null) throw new IllegalArgumentException("Key is null for put!");
        if (val == null) {
            delete(key);
            return;
        }
        
        if (N >= M/2) resize(2*M);
        int i = hash(key);
        for (; vals[i] != null; i = (i+1)%M) {
            if (key == keys[i]) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    
    public Value get(int key)
    {
        //if (key == null) throw new IllegalArgumentException("Key is null for get!");
        for (int i = hash(key); vals[i] != null; i = (i+1)%M) {
            if (key == keys[i]) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(int key)
    {
        //if (key == null) return;
        if (!contains(key)) return;
        
        int i = hash(key);
        while( key != keys[i]) {
            i = (i + 1)%M;
        }
        //keys[i] = null;
        vals[i] = null;
        
        i = (i + 1)%M;
        while (vals[i] != null) {
            int keyToReach = keys[i];
            Value valToReach = vals[i];
            //keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToReach, valToReach);
            i = (i + 1)%M;
        }
        N--;
        
        if (N > 0 && N <= 8/M) resize(M/2);
    }
    
    public Iterable<Integer> keys()
    {
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i < M ; i++) {
            if (vals[i] == null) continue;
            queue.enqueue(keys[i]);
        }
        return queue;
    }
    
    public int size() { return this.N; }
    public boolean isEmpty() { return size() == 0; }
    public boolean contains(int key) {
        //if (key == null) throw new IllegalArgumentException("Key is null for contains!");
        return get(key) != null;
    }
    
    public static void main(String[] args) { }     
}