public class PrimMST
{
    private boolean[] marked;
    private Edge[] edgeTo;
    private double[] disTo;
    private IndexMinPQ<Double> pq;
    
    public PrimMST(EdgeWeightedGraph G)
    {
        int V = G.V();
        int E = G.E();
        marked = new boolean[V];
        edgeTo = new Edge[V];
        disTo = new double[V];
        for (int i = 0; i < V; i++) {
            disTo[i] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ(V);
        disTo[0] = 0.0;
        
        pq.insert(0, 0.0);
        while(!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }
    
    private void visit(EdgeWeightedGraph G, int v)
    {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            double weight = e.weight();
            if (weight < disTo[w]) {
                disTo[w] = weight;
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, disTo[w]);
                else                pq.insert(w, disTo[w]);
            }
        }
    }
    
    public Iterable<Edge> edges()
    {
        Queue<Edge> edges = new Queue<Edge>();
        for (int i = 1; i < edgeTo.length; i++) {
            edges.enqueue(edgeTo[i]);
        }
        return edges;
    }
    
    public double weight()
    {
        double weight = 0.0;
        for (int i = 1; i < edgeTo.length; i++) {
            weight += edgeTo[i].weight();
        }
        return weight;
    }
}