public class EdgeWeightedCycleFinder
{
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;
    private boolean[] onStack;
    
    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G)
    {
        int V = G.V();
        marked = new boolean[V];
        edgeTo = new DirectedEdge[V];
        onStack = new boolean[V];
        
        for (int v = 0; v < V; v++) {
            if (!marked[v]) dfs(G, v);
        }
    }
    
    private void dfs(EdgeWeightedDigraph G, int v)
    {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();
                DirectedEdge ee;
                for (ee = edgeTo[w]; ee.from() != w; ee = edgeTo[ee.from()]) {
                    cycle.push(ee);
                }
                cycle.push(ee);
            }
        }
        onStack[v] = false;
    }
    
    public boolean hasCycle() { return cycle != null; }
    public Iterable<DirectedEdge> cycle() { return cycle; }
}