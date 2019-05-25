public class EdgeWeightedDigraph
{
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    
    public EdgeWeightedDigraph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
          adj[i] = new Bag<DirectedEdge>();
        }
    }
    
    public EdgeWeightedDigraph(In in)
    {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++)
        {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }
    
    private void addEdge(DirectedEdge e)
    {
        adj[e.from()].add(e);
        this.E++;
    }
    
    public int V() { return V; }
    public int E() { return E; }
    
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }
    
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edges = new Bag<DirectedEdge>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge e : adj(i)) {
                edges.add(e);
            }
        }
        return edges;
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}