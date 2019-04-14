public class Graph
{
    private static final String NEWLINE = System.getProperty("line.separator");
    private Bag<Integer>[] adj;
    private final int V;
    private int E;
    
    public Graph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    
    public Graph(In in)
    {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }
    
    public Graph(In in, String sp)
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
    
    public Graph(Graph G)
    {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            //Use stack here to keep the order of elements in adj exactly same with G
            Stack<Integer> stack = new Stack<Integer>();
            for (Integer w : G.adj(v)) {
                stack.push(w);
            }
            while(!stack.isEmpty()) {
                adj[v].add(stack.pop());
            }
        }
    }
    
    public int V() { return this.V; }
    public int E() { return this.E; }
    
    void addEdge(int v, int w)
    {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    
    public Iterable<Integer> adj(int v) { return adj[v]; }
    
    public boolean hasEdge(int v, int w) {
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
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}