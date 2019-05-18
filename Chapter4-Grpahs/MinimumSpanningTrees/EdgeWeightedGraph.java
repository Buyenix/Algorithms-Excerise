public class EdgeWeightedGraph
{
    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    
    public EdgeWeightedGraph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    
    public EdgeWeightedGraph(In in)
    {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++)
        {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }
    
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    
    public int V() { return V; }
    public int E() { return E; }
    
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
    
    public Iterable<Edge> edges() {
        Bag<Edge> edges = new Bag<Edge>();
        for (int i = 0; i < V; i++) {
            for (Edge e : adj(i)) {
                if (i >= e.other(i)) continue; 
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
            for (Edge e : adj[v]) {
                s.append(e + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}