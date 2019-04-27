public class OnePassSymbolGraph
{
    private RedBlackBST<String, Integer> st;
    private String[] keys;
    private RedBlackBST<Integer, Bag<Integer>> G;
    
    public OnePassSymbolGraph(String stream, String sp)
    {
        st = new RedBlackBST<String, Integer>();
        G = new RedBlackBST<Integer, Bag<Integer>>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            int v = 0;
            String[] a = in.readLine().split(sp);
            Bag<Integer> bag = new Bag<Integer>();
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                  st.put(a[i], st.size());
                }
                bag.add(st.get(a[i]));
            }
            G.put(st.get(a[0]), bag);
        }
        
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
    }
    
    public Iterable<Integer> adj(int v) { return G.get(v); }
    
    public boolean hasEdge(int v, int w) {
        int V = st.size();
        if (v >= V || v < 0) {
            return false;
        }
        for (int i : adj(v)) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }  
    public boolean contains(String s) { return st.contains(s); }
    public int index(String s) { return st.get(s); }
    public String name(int v) { return keys[v]; }
    public RedBlackBST<Integer, Bag<Integer>> G() { return G; }
}