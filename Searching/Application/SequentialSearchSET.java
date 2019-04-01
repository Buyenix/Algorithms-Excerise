public class SequentialSearchSET<Key>
{
    private Node first;
    private int N;
    private class Node
    {
        Key key;
        Node next;
        public Node(Key key, Node next) {
            this.key = key;
            this.next = next;
        }
    }
    
    
    public void add(Key key) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (contains(key)) return;
        first = new Node(key, first);
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
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isEmpty() {
       return  N == 0; 
    }
    
    public int size() {
        return N;     
    }
    
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        for (Node x =first; x != null; x = x.next) {
            q.enqueue(x.key);
        }
        return q;
    }
    
    public static void main(String[] args) {
        SequentialSearchSET<String> st = new SequentialSearchSET<String>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.add(key);
        }
        for (String s : st.keys())
            StdOut.println(s + " ");
        
    }
}