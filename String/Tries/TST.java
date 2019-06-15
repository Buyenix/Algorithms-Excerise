public class TST<Value>
{
    private Node root;
    
    private class Node
    {
        private Node mid;
        private Node left;
        private Node right;
        private char c;
        private Value val;
        public Node() {
            char c = 0;
            mid = left = right = null;
            val = null;
        }
    }
    
    public void put(String s, Value v)
    {
        root = putLow(root, s, v, 0);
    }
    
    private Node putLow(Node x, String s, Value v, int index)
    {
        char c = s.charAt(index);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        
        if (x.c < c) {
            x.right = putLow(x.right, s, v, index);
        } else if(x.c > c) {
            x.left = putLow(x.left, s, v, index);
        } else {
            if (index < s.length()-1) {
                x.mid = putLow(x.mid, s, v, index+1);
            } else {
                x.val = v;
            }
        }
        return x;
    }
    
    public Value get(String s)
    {
        Node x = getLow(root, s, 0);
        if (x == null) return null;
        else           return x.val;
    }
    
    private Node getLow(Node x, String s, int index)
    {
        if (x == null) return null;
        
        char c = s.charAt(index);
        if (x.c < c) {
            return getLow(x.right, s, index);
        } else if(x.c > c) {
            return getLow(x.left, s, index);
        } else {
            if (index < s.length()-1) {
                return getLow(x.mid, s, index+1);
            } else {
                return x;
            }
        }
    }
    
    public Iterable<String> keys()
    {
        Queue<String> q = new Queue<String>();
        collect(root, "", q);
        return q;
    }
    
    public Iterable<String> keysWithPrefix(String prefix)
    {
        Queue<String> q = new Queue<String>();
        Node x = getLow(root, prefix, 0);
        if (x == null) return q;
        if (x.val != null) q.enqueue(prefix);
        collect(x.mid, prefix, q);
        return q;
    }
    
    private void collect(Node x, String s, Queue<String> q)
    {
        if (x == null) return;
        collect(x.left, s, q);
        if (x.val != null) q.enqueue(s + x.c);
        collect(x.mid, s + x.c, q);
        collect(x.right, s, q);
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
        char c = pattern.charAt(index);
        if (c == '.' || c < x.c) {
            match(x.left, pattern, s, q);
        }
        if (c == '.' || c == x.c) {
            if (index == pattern.length()-1 && x.val != null) {
                q.enqueue(s+x.c);
            }
            if (index < pattern.length()-1) {
                match(x.mid, pattern, s+x.c, q);
            }
        }
        if (c == '.' || c > x.c) {
            match(x.right, pattern, s, q);
        }
    }
    
    public String longestPrefixOf(String prefix)
    {
        int length = longestPrefixOf(root, prefix, 0, 0);
        //StdOut.println("length = "+length);
        return prefix.substring(0, length+1);
    }
    
    private int longestPrefixOf(Node x, String prefix, int index, int length)
    {
        if (x == null) return length;
        if (index >= prefix.length()) return length;
        char c = prefix.charAt(index);
        if (c < x.c) return longestPrefixOf(x.left, prefix, index, length);
        else if (c > x.c) return longestPrefixOf(x.right, prefix, index, length);
        else {
            if (x.val != null) length = index;
            return longestPrefixOf(x.mid, prefix, index+1, length);
        }
    }
    
    public static void main(String[] args) {
        String[] a = {"abc", "rhfowhf", "woehfawo", "aohgowh", "aourhfrhg", "awug", "aorhg", "aweoeg", "eohre"};
        // build symbol table from standard input
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        
        // print results

        StdOut.println("keys(\"\"):");
        for (String key : st.keys()) {
            StdOut.println(key + " " + st.get(key));
        }
        StdOut.println();

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
    }
}