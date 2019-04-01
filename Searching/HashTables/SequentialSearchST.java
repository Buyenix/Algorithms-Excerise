public class SequentialSearchST<Key, Value>
{
    private Node first;
    private int N;
    private class Node
    {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val;
        }
        return null;
    }
    
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        N++;
    }
    
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        for (Node x = first, p = null; x != null; p = x, x = x.next) {
            if (key.equals(x.key)) {
                if (p == null) first = x.next;
                else           p.next = x.next;
                N--;
                return;
            }
        }
    }
    
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    
    public boolean isEmpty() {
       return  N == 0; 
    }
    
    public int size() {
        return N;
//        int length = 0;
//        Node x = first;
//        while(x != null) {
//            x = x.next;
//            length++;
//        }
//        return length;      
    }
    
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        for (Node x =first; x != null; x = x.next) {
            q.enqueue(x.key);
        }
        return q;
    }
    
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        
    }
}