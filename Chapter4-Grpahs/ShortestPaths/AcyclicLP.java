public class AcyclicLP
{
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    
    public AcyclicLP(EdgeWeightedDigraph G, int s)
    {
        int V = G.V();
        edgeTo = new DirectedEdge[V];
        
        disTo  = new double[V];
        for (int i = 0; i < V; i++) {
            disTo[i] = Double.NEGATIVE_INFINITY;
        }
        disTo[s] = 0.0;
        
        EdgeWeightedTopological topo = new EdgeWeightedTopological(G);
        for (int v : topo.order()) {
            relax(G, v);
        }
    }
    
    private void relax(EdgeWeightedDigraph G, int v)
    {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (disTo[w] < disTo[v] + e.weight()) {
                edgeTo[w] = e;
                disTo[w] = disTo[v] + e.weight();
            }
        }
    }
    
    public double disTo(int v) {
        return disTo[v];
    }
    
    public boolean hasPathTo(int v) {
        return disTo[v] > Double.NEGATIVE_INFINITY;
    }
    
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}