public class PrimMSTForest
{
    private boolean[] marked;
    private Edge[] edgeTo;
    private double[] disTo;
    private IndexMinPQ<Double> pq;
    
    public void PrimMSTForest(EdgeWeightedGraph G)
    {
        int V = G.V();
        int E = G.E();
        marked = new boolean[V];
        edgeTo = new Edge[V];
        disTo  = new double[V];
        pq = new IndexMinPQ<Double>(V);
        
        for (int s = 0; s < V; s++) {
            if (marked[s]) continue;
            process(G, s);
        }
    }
    
    private void process(EdgeWeightedGraph G, int v)
    {
        disTo[v] = 0.0;
        pq.insert(v, 0.0);
        while (!pq.isEmpty()) {
            int w = pq.delMin();
            if (marked[w]) continue; // For safety...
            visit(G ,w);
        }
    }
    
    private void visit(EdgeWeightedGraph G, int v)
    {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue; // w is already in MST.
            double weight = e.weight();
            if (weight < disTo[w]) {
                disTo[w] = weight;
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, weight);
                else                pq.insert(w, weight);
            }
        }
    }
    
    public Iterable<Edge> edges()
    {
        Queue<Edge> edges = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            if (edgeTo[v] != null) {
                edges.enqueue(edgeTo[v]);
            }
        }
        return edges;
    }
    
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }
}