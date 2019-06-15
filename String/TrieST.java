public class TrieST<Value>
{
    private static int R = 256;
    private Node root;
    
    private static class Node {
        private Object val;
        private Node[] next;
        private int N;
        
        public Node() { val = null; next = new Node[R]; N = 0; }
    }
    
    public void put(String s, Value v)
    {
        if (s == null) throw new IllegalArgumentException("argument to put() is null");
        root = putLow(root, s, v, 0);
    }
    
    private Node putLow(Node x, String s, Value v, int index)
    {
        if (x == null) x = new Node();
        if (index == s.length()) {
            if (x.val == null) x.N++;
            x.val = v;
            return x;
        }

        char c = s.charAt(index);
        int origN = (x.next[c] == null) ? 0 : x.next[c].N;
        x.next[c] = putLow(x.next[c], s, v, index+1);
        x.N += (x.next[c].N - origN);
        return x;
    }
    
    // No implementation for N
    public void nonRecursivePut(String s, Value v)
    {
        if (s == null) throw new IllegalArgumentException("argument to put() is null");
        Stack<Node> nodes = new Stack<Node>();
        Stack<Integer> indices = new Stack<Integer>();
        nodes.push(root);
        indices.push(0);
        
        while(!nodes.isEmpty()) {
            Node x = nodes.pop();
            int index = indices.pop();
            
            if (x == null) x = new Node();
            if (index == s.length()) { x.val = v; return; }
            char c = s.charAt(index);
            nodes.push(x.next[c]);
            indices.push(index+1);
        }
    }
    
    public Value get(String s)
    {
        if (s == null) throw new IllegalArgumentException("argument to get() is null");
        Node x = getLow(root, s, 0);
        if (x == null) return null;
        else           return (Value) x.val;
    }
    
    private Node getLow(Node x, String s, int index)
    {
        if (x == null) return null;
        if (index == s.length()) return x;
        char c = s.charAt(index);
        return getLow(x.next[c], s, index+1);
    }
    
    public Iterable<String> keys()
    {
        return keysWithPrefix("");
    }
    
    public Iterable<String> keysWithPrefix(String prefix)
    {
        Queue<String> q = new Queue<String>();
        collect(getLow(root, prefix, 0), prefix, q);
        return q;
    }
    
    private void collect(Node x, String s, Queue<String> q)
    {
        if (x == null) return;
        if (x.val != null) q.enqueue(s);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], s+c, q);
        }
    }
    
    public Iterable<String> keysThatMatch(String that)
    {
        Queue<String> q = new Queue<String>();
        match(root, that, "", q);
        return q;
    }
    
    private void match(Node x, String pattern, String s, Queue<String> q)
    {
        if (x == null) return;
        int index = s.length();
        if (index == pattern.length()) {
            if (x.val != null) q.enqueue(s);
            return;
        }
        
        char c = pattern.charAt(index);
        if (c == '.') {
            for (char cc = 0; cc < R; cc++) {
                match(x.next[cc], pattern, s+cc, q);
            }
        } else {
            match(x.next[c], pattern, s+c, q);
        }
    }
    
    public String longestPrefixOf(String s)
    {
        int length = longestPrefixOf(root, s, 0, 0);
        //StdOut.println("length = " + length);
        return s.substring(0, length);
    }
    
    private int longestPrefixOf(Node x, String s, int index, int length)
    {
        if (x == null) return length;;
        if (x.val != null) length = index;
        if (index == s.length()) return length;
        char c = s.charAt(index);
        return longestPrefixOf(x.next[c], s, index+1, length);
    }
    
    public void delete(String s)
    {
        root = deleteLow(root, s, 0);
    }
    
    private Node deleteLow(Node x, String s, int index)
    {
        if (x == null) return null;
        //StdOut.printf("Delete %s#%d\n", s, index);
        if (index == s.length()) {
            if (x.val != null) {
                //StdOut.printf("##1, %s#%d, length=%d\n", s, index, x.N);
                x.N--;
            }
            x.val = null;
        } else {
          char c = s.charAt(index);
          int origN = (x.next[c] == null) ? 0 : x.next[c].N;
          x.next[c] = deleteLow(x.next[c], s, index+1);
          //StdOut.printf("##2, %s#%d, length=%d, next.N = %d, origN = %d\n", s, index, x.N, x.next[c] == null ? 0 : x.next[c].N, origN);
          x.N -= (x.next[c] == null ? origN :  origN - x.next[c].N);
        }
        
        if (x.val != null) return x;
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        return null;
    }
    
    public boolean contains(String s)
    {
        return get(s) != null;
    }
    
    public int size()
    {
        return root == null ? 0 : root.N;
    }
    
    public boolean isEmpty()
    {
        return size() == 0;
    }
    
    public static void main(String[] args) {
        String[] a = {"abc", "rhfowhf", "woehfawo", "aohgowh", "aourhfrhg", "awug", "aorhg", "aweoeg", "eohre"};
        // build symbol table from standard input
        TrieST<Integer> st = new TrieST<Integer>();
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        
        // print results
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }
        
        StdOut.println("longestPrefixOf(\"awugxx\"):");
        StdOut.println(st.longestPrefixOf("awugxx"));
        StdOut.println();
        
        StdOut.println("keysWithPrefix(\"a\"):");
        for (String s : st.keysWithPrefix("a"))
            StdOut.println(s);
        StdOut.println();
        
        StdOut.println("keysThatMatch(\".....\"):");
        for (String s : st.keysThatMatch("....."))
            StdOut.println(s);
        
        StdOut.printf("Current size = %d\n", st.size());
        
        st.delete("abc");
        st.delete("awug");
        StdOut.printf("Current size = %d\n", st.size());
        st.put("oaerhg", 3);
        st.put("soeitjhejs", 4);
        StdOut.printf("Current size = %d\n", st.size());
        st.delete("oaerhg");
        StdOut.printf("Current size = %d\n", st.size());
    }
}