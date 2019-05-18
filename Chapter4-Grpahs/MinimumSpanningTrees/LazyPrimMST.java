public class LazyPrimMST
{
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;
    private double weight;
    
    public LazyPrimMST(EdgeWeightedGraph G)
    {
        int V = G.V();
        marked = new boolean[V];
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>(G.E());
        weight = 0.0;
        
        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            weight += e.weight();
            //Below two visit, only one can be called.
            if (marked[v]) visit(G, w);
            if (marked[w]) visit(G, v);
        }  
    }
    
    private void visit(EdgeWeightedGraph G, int v)
    {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            pq.insert(e);
        }
    }
    
    public Iterable<Edge> edges() { return mst; }
    public double weight1() {
        double w = 0.0;
        while (!mst.isEmpty()) {
            Edge e = mst.dequeue();
            w += e.weight();
        }
        return w;
    }
    public double weight2() {
        return weight;
    }
}