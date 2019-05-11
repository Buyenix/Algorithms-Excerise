public class Digraph
{
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    
    public Digraph(int V)
    {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < this.V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    
    public Digraph(In in)
    {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }
    
    public Digraph(In in, String sp)
    {
        this(in.readInt());
        int E = in.readInt();
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            if (a.length <= 0) continue;
            int v = Integer.parseInt(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = Integer.parseInt(a[i]);
                addEdge(v, w);
            }
        }
    }
    public Digraph(Digraph G)
    {
        this(G.V());
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                addEdge(v, w);
            }
        }
    }
    
    public int V() { return V; }
    public int E() { return E; }
    
    public void addEdge(int v, int w)
    {
        if (v == w) {
            throw new RuntimeException("Self cycle!");
        }
        for (int i : adj[v]) {
            if (i == w) {
                throw new RuntimeException("Parallel edge!");
            }
        }
        adj[v].add(w);
        E++;
    }
    
    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }
    
    public Digraph reverse()
    {
        Digraph reverseG = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverseG.addEdge(w, v);
            }
        }
        return reverseG;
    }
    
    public boolean hasEdge(int v, int w)
    {
        for (int i : adj[v]) {
            if (w == i) {
                return true;
            }
        }
        return false;
    }
}