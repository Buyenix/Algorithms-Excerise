public class BellmanFordSP
{
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    private Queue<Integer> queue;
    private  boolean[] onQ;
    private Iterable<DirectedEdge> cycle;
    private int cost;
    
    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        int V = G.V();
        edgeTo = new DirectedEdge[V];
        
        disTo  = new double[V];
        for (int i = 0; i < V; i++) {
            disTo[i] = Double.POSITIVE_INFINITY;
        }
        disTo[s] = 0.0;
        
        onQ = new boolean[V];
        queue = new Queue<Integer>();
        queue.enqueue(s);
        onQ[s] = true;
        while(!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }
    }
    
    public void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (disTo[w] > disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
        }
        if (cost++%G.V() == 0) {
            findNegativeCycle();
        }
    }
    
    public void findNegativeCycle()
    {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) {
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedCycleFinder cycleFinder = new EdgeWeightedCycleFinder(spt);
        if (cycleFinder.hasCycle()) {
            cycle = cycleFinder.cycle();
        }
    }
    
    public boolean hasNegativeCycle() {
        return cycle != null;
    }
    
    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}