//import java.util.Arrays;
public class ArrayST<Key, Value>
{
    private Key[] keys;
    private Value[] vals;
    private int N;
    
    public ArrayST() {
        int initialSize = 1;
        keys = (Key[]) new Object[initialSize];
        vals = (Value[]) new Object[initialSize];
        N = 0;
    }
    
    private void resize(int newSize) {
        Key[] newKeys = (Key[]) new Object[newSize];
        Value[] newVals = (Value[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            newKeys[i] = keys[i];
            newVals[i] = vals[i];
        }
        keys = newKeys;
        vals = newVals;
    }
    
    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }
    
    private int index(Key key) {
        if (N < 1) return -1;
        for (int i = 0; i < N; i++) {
            if (key.equals(keys[i]))
                return i;
        }
        return -1;
    }
    
    public boolean contains(Key key) {
        return index(key) != -1;
    }
    
    public void put(Key key, Value val) {
        if (N == keys.length) resize(2*N);
        int idx = index(key);
        if (idx != -1) {
            keys[idx] = key;
            vals[idx] = val;
        } else {
            N++;
            keys[N-1] = key;
            vals[N-1] = val;
        }
    }
    
    public Value get(Key key) {
        int idx = index(key);
        if (idx != -1) return vals[idx];
        else           return null;
    }
    
    public Value moveToFrontGet(Key key) {
        int idx = index(key);
        Value val;
        if (idx != -1) {
            val = vals[idx];
            for (int i=idx; i>0; i--) {
                keys[i] = keys[i-1];
                vals[i] = vals[i-1];
            }
            keys[0] = key;
            vals[0] = val;
        } else {
            val = null;
        }
        return val;
    }
    
    public void delete(Key key) {
        int idx = index(key);
        int lastIdx = N-1;
        if (idx == -1) return;
        else if (idx == lastIdx) {
            keys[idx] = null;
            vals[idx] = null;
        } else {
            keys[idx] = keys[lastIdx];
            vals[idx] = vals[lastIdx];
            keys[lastIdx] = null;
            vals[lastIdx] = null;
        }
        N--;
        if (N > 0 && N == keys.length/4) resize(keys.length/2);
    }
    
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }
}