//For this class, the deleteMin(), deleteMax and delete are very complicated.
//The code implementation is copied from https://algs4.cs.princeton.edu/33balanced/ST.java.html

public class ST <Key extends Comparable<Key>, Value>
{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private Node root;
    private class Node
    {
        
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        private boolean color;
        
        public Node(Key key, Value val, int N, boolean color)
        {
            this.key = key;
            this.val = val;
            this.N = 1;
            this.color = color;
        }
    }
    
    private boolean isRed(Node x)
    {
        if (x == null) return false;
        return x.color == RED;
    }
    
    public int size() { return size(root); }
    private int size(Node x)
    {
        if (x == null) return 0;
        return x.N;
    }
    public boolean isEmpty() { return size() == 0; }
    
    private Node rotateLeft(Node h)
    {
        //if (h == null) return null;
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }
    
    private Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }
    
    private void flipColor(Node h)
    {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    
    public void put(Key key, Value val)
    {
        root = put(root, key, val);
        root.color = BLACK;
    }
    
    private Node put(Node h, Key key, Value val)
    {
        if (h == null) return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;
        
        if (!isRed(h.left) && isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColor(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
    
    public Value get(Key key) { return get(root, key); }
    private Value get(Node h, Key key)
    {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp < 0)       return get(h.left, key);
        else if (cmp > 0)  return get(h.right, key);
        else return h.val;
    }
    
    public void deleteMin()
    {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }
    
    private Node deleteMin(Node h)
    {
        if (h.left == null) return null;
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }
    
    public void deleteMax()
    {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }
    
    private Node deleteMax(Node h)
    {
        if (isRed(h.left)) h = rotateRight(h);
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }
    
    private Node moveRedLeft(Node h)
    {
        flipColor(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }
    
    private Node moveRedRight(Node h)
    {
        flipColor(h);
        if (!isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }
    
    private Node balance(Node h)
    {
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColor(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
    
    
    public void delete(Key key)
    {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }
    
    private Node delete(Node h, Key key) { 
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
    
    public boolean contains(Key key) { return get(key) != null; }
    
    public Key min() { return min(root).key; }
    private Node min(Node h)
    {
        //if (h == null) return null;
        if (h.left == null) return h;
        else                return min(h.left);
    }
    
    public Key max() { return max(root).key; }
    private Node max(Node h)
    {
        //if (h == null) return null;
        if (h.right == null) return h;
        else                 return max(h.right);
    }
    
    public Key floor(Key key)
    {
        Node h = floor(root, key);
        if (h == null) return null;
        else           return h.key;
    }
    private Node floor(Node h, Key key)
    {
        if (root == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp == 0) return h;
        if (cmp < 0)  return floor(h.left, key);
        Node x = floor(h.right, key);
        if (x == null) return h;
        else           return x;
    }
    
    public Key ceiling(Key key)
    {
        Node h = ceiling(root, key);
        if (h == null) return null;
        else           return h.key;
    }
    private Node ceiling(Node h, Key key)
    {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp == 0) return h;
        if (cmp > 0)  return ceiling(h.right, key);
        Node x = ceiling(h.left, key);
        if (x == null) return h;
        else           return x;
    }
    
    public Key select(int k)
    {
        Node x = select(root, k);
        if (x == null) return null;
        else           return x.key;
    }
    private Node select(Node h, int k)
    {
        if (h == null) return null;
        int t = size(h.left);
        if      (k == t) return h;
        else if (k < t)  return select(h.left, k);
        else             return select(h.right, k-t-1);
    }
    
    public int rank(Key key) { return rank(root, key); }
    private int rank(Node h, Key key)
    {
        if (key == null) return 0;
        int cmp = key.compareTo(h.key);
        if      (cmp < 0) return rank(h.left, key);
        else if (cmp > 0) return size(h.left) + 1 + rank(h.right, key);
        else              return size(h.left);
    }
    
    public int size(Key lo, Key hi)
    {
        if (lo.compareTo(hi) > 0) return 0;
        int size = rank(hi) - rank(lo);
        if (contains(hi)) size += 1;
        return size;
    }
    
    public Iterable<Key> keys() { return keys(min(), max()); }
    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> q = new Queue<Key>();
        keys(root, q, lo, hi);
        return q;
    }
    private void keys(Node h, Queue<Key>q, Key lo, Key hi)
    {
        if (h == null) return;
        int cmpLo = lo.compareTo(h.key);
        int cmpHi = hi.compareTo(h.key);
        if (cmpLo < 0) keys(h.left, q, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) q.enqueue(h.key);
        if (cmpHi > 0) keys(h.right, q, lo, hi);
    }
    
    public int height() { return height(root); }
    private int height(Node h) {
        if (h == null) return 0;
        return 1 + Math.max(size(h.left), size(h.right));
    }
    
    private boolean isBST() { return isBST(root); }
    private boolean isBST(Node h)
    {
        if (h == null) return true;
        if (h.left != null && h.key.compareTo(h.left.key) <= 0) return false;
        if (h.right != null && h.key.compareTo(h.right.key) >= 0) return false;
        return isBST(h.left) && isBST(h.right);
    }
    
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node h)
    {
        if (h == null) return true;
        if (size(h) != size(h.left) + size(h.right) + 1) return false;
        return isSizeConsistent(h.left) && isSizeConsistent(h.right);
    }
    
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
            if (rank(select(i)) != i) {
                return false;
            }
        }
        for (Key key: keys()) {
            if (key.compareTo(select(rank(key))) != 0) {
                return false;
            }
        }
        return true;
    }
    
    private boolean is23() { return is23(root); }
    private boolean is23(Node h)
    {
        if (h == null) return true;
        if (isRed(h.right)) return false;
        if (h != root && isRed(h) && isRed(h.left)) return false;
        return is23(h.left) && is23(h.right);
    }
    
    private boolean isBalanced() {
        int black = 0;
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }
    private boolean isBalanced(Node h, int black)
    {
        if (h == null) return black == 0;
        if (!isRed(h)) black--;
        return isBalanced(h.left, black) && isBalanced(h.right, black);
    }
    
    private boolean check()
    {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        if (!is23()) StdOut.println("Not a 2-3 tree");
        if (!isBalanced()) StdOut.println("Not balanced");
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }
    
    public static void main(String[] args)
    {
        ST<String, Integer> st = new ST<String, Integer>();
        In in = new In("Tale of two cities.txt");
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }
        
        Out out = new Out("Word count of tale of two cities.txt");
        for(String s: st.keys()) {
            out.println(s + " " + st.get(s));
        }
        StdOut.println();
    }
}



