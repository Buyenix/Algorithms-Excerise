public class DirectedCycle
{
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;
    
    public DirectedCycle(Digraph G)
    {
        int V = G.V();
        marked = new boolean[V];
        edgeTo = new int[V];
        onStack = new boolean[V];
        
        for (int v = 0; v < V; v++) {
            if (!marked[v]) dfs(G, v);
        }
    }
    
    private void dfs(Digraph G, int v)
    {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int i = v; i != w; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }
    
    public boolean hasCycle() { return cycle != null; }
    public Iterable<Integer> cycle() { return cycle; }
}