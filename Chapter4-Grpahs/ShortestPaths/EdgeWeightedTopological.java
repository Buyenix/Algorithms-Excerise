public class EdgeWeightedTopological
{
    private Iterable<Integer> order;
    
    public EdgeWeightedTopological(EdgeWeightedDigraph G)
    {
        EdgeWeightedCycleFinder cycleFinder = new EdgeWeightedCycleFinder(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }
    
    public Iterable<Integer> order() { return order; }
    public boolean isDAG() { return order != null; }
}