public class PrimMSTForest
{
    private static final double FLOATING_POINT_EPSILON = 1E-12;
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
    
    public boolean check(EdgeWeightedGraph G)
    {
        //1. check weight
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        if (Math.abs(weight - weight()) > FLOATING_POINT_EPSILON) {
            StdOut.printf("Graph weight %f doesn't equal weight() %f\n", weight, weight());
            return false;
        }
        
        //2. check mst is acyclic
        UF uf = new UF(G.V());
        for (Edge e : edges()) {
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                StdOut.printf("MST has a cycle for edge: %s\n", e);
                return false;
            } else {
                uf.union(v, w);
            }
        }
        
        //3. check mst is a spanning tree or not
        for (Edge e : G.edges()) {
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                StdOut.printf("%d-%d is not connected in MST\n", v, w);
                return false;
            }
        }
        
        //4. Check the cut property for minimum spanning tree
        for (Edge e : edges()) {
            UF uf2 = new UF(G.V());
            
            for (Edge ee : edges()) {
                if (ee != e) {
                    int v = ee.either();
                    int w = ee.other(v);
                    uf2.union(v, w);
                }
            }
            
            for (Edge eee : G.edges()) {
                int x = eee.either();
                int y = eee.other(x);
                if (uf2.connected(x, y)) continue;
                if (eee.weight() < e.weight()) {
                    StdOut.printf("Edge %s is not the minimum edge for cut property.\n", e);
                    return false;
                }
            }
        }
        return true;
    }
}