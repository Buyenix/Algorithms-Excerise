public class KrusalMST
{
    //private boolean[] marked;
    //private UF union;
    private Queue<Edge> mst;
    
    public KrusalMST(EdgeWeightedGraph G) {
        int V = G.V();
        int E = G.E();
        //marked = new boolean[V];
        mst = new Queue<Edge>();
        UF uf = new UF(V);
        MinPQ<Edge> pq = new MinPQ<Edge>(E);
        for (Edge e : G.edges()) {
          pq.insert(e);
        }
        
        while (!pq.isEmpty() && mst.size() < V-1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }
    
    public Iterable<Edge> edges() { return mst; }
    
    public double weight() {
        double w = 0.0;
        while (!mst.isEmpty()) {
            Edge e = mst.dequeue();
            w += e.weight();
        }
        return w;
    }
}