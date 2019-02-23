public class BST<Key extends Comparable<Key>, Value>
{
    private Node root;
    private class Node
    {
        private Key key;
        private Value val;
        private Node left, right;
        private int N; // size
        private int H; // height
        
        public Node(Key key, Value val, int N)
        {
            this.key = key;
            this.val = val;
            this.N = N;
            this.H = 0;
        }
    }
    
    public int size() { return size(root); }
    private int size(Node x)
    {
        if (x == null) return 0;
        else           return x.N;
    }
    
    public boolean isEmpty() { return size() == 0; }
    private int height(Node x)
    {
       if (x == null) return 0;
       else           return x.H;
    }
    
    public Value get(Key key) { return get(root, key); }
    private Value get(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp == 0) return x.val;
        else if (cmp < 0)  return get(x.left, key);
        else               return get(x.right, key);
    }
    
    public boolean contains(Key key) { return get(key) != null; }
    
    public void put(Key key, Value val) { root = put(root, key, val); }
    private Node put(Node x, Key key, Value val)
    {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp == 0) x.val = val;
        else if (cmp < 0)  x.left = put(x.left, key, val);
        else               x.right = put(x.right, key, val);
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    
    public Key min()
    {
        if (isEmpty()) throw new RuntimeException("calls min() with empty symbol table");
        return min(root).key;
    }
    private Node min(Node x)
    {
        if (x.left == null) return x;
        else                return min(x.left);
    }
    
    public Key max()
    {
        if (root == null) return null;
        return max(root).key;
    }
    private Node max(Node x)
    {
        if (x.right == null) return x;
        else                 return max(x.right);
    }
    
    public Key floor(Key key)
    {
        Node x = floor(root, key);
        if (x == null) return null;
        else           return x.key;
    }
    private Node floor(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if     (cmp == 0) return x;
        else if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t == null) return x;
        else           return t;
    }
 
    public Key floor2(Key key) {
        return floor2(root, key, null);
    }

    private Key floor2(Node x, Key key, Key best) {
        if (x == null) return best;
        int cmp = key.compareTo(x.key);
        if      (cmp  < 0) return floor2(x.left, key, best);
        else if (cmp  > 0) return floor2(x.right, key, x.key);
        else               return x.key;
    }
    
    public Key ceiling(Key key)
    {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else           return x.key;
    }
    private Node ceiling(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if     (cmp == 0) return x;
        else if (cmp > 0) return ceiling(x.right, key);
        Node t = floor(x.left, key);
        if (t == null) return x;
        else           return t;
    }
    
    public Key select(int k)
    {
        return select(root, k).key;
    }
    private Node select(Node x, int k)
    {
        if (x == null) return null;
        int t = size(x.left);
        if (t == k) return x;
        else if (t > k) return select(x.left, k);
        else return select(x.right, k-t-1);
    }
    
    public int rank(Key key) { return rank(root, key); }
    private int rank(Node x, Key key)
    {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return size(x.left);
        else if (cmp < 0) return rank(x.left, key);
        else return size(x.left) + 1 + rank(x.right, key);
    }
    
    public int size(Key lo, Key hi)
    {
        if (lo.compareTo(hi) > 0) return 0;
        int rankL = rank(lo);
        int rankH = rank(hi);
        if (contains(hi)) return rankH - rankL + 1;
        else              return rankH - rankL;
    }
    
    public void deleteMin() { root = deleteMin(root); }
    private Node deleteMin(Node x)
    {
        //if (x == null) return null;
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    
    public void delete(Key key) { root = delete(root, key); }
    private Node delete(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.left = t.left;
            x.right = deleteMin(t.right);
        }
        x.N = size(x.left) + size(x.right) + 1;
        //StdOut.println("size left = " + size(x.left));
        //StdOut.println("size right = " + size(x.right));
        x.H = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    
    public Iterable<Key> keys() { return keys(min(), max()); }
    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> q = new Queue<Key>();
        keys(root, q, lo, hi);
        return q;
    }
    
    private void keys(Node x, Queue<Key> q, Key lo, Key hi)
    {
        if (x == null) return;
        int loCmp = lo.compareTo(x.key);
        int hiCmp = hi.compareTo(x.key);
        if (loCmp < 0) keys(x.left, q, lo, hi);
        if (loCmp <= 0 && hiCmp >= 0) q.enqueue(x.key);
        if (hiCmp > 0) keys(x.right, q, lo, hi);
    }
    
    public int height1() { return height(root); }
    public int height2() { return height2(root); }
    private int height2(Node x)
    {
        if (x == null) return -1;
        int lHeight = height(x.left);
        int rHeight = height(x.right);
        return 1 + Math.max(lHeight, rHeight);
    }
    
    public double avgCompares() {
        int size = size();
        if (size == 0) return 0;
        else {
            int internalPath = internalPath(root);
            return (double)(internalPath/size) + 1;
        }
    }
    private int internalPath(Node x)
    {
        if (x == null) return -1;
        else return 2 + internalPath(x.left) + internalPath(x.right);
    }
}








