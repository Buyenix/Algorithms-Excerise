public class DepthFirstSearch
{
    private boolean[] marked;
    private int count;
    
    public DepthFirstSearch(Graph G, int s)
    {
        marked = new boolean[G.V()];
        dfs(G, s);
    }
    
    private void dfs(Graph G, int v)
    {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (marked[w]) continue;
            dfs(G, w);
        }
    }
    
    public boolean marked(int w) { return marked[w]; }
    public int count() { return count; }
    
    private void dfs(Graph G, int v, int e) // e is the exclusive one
    {
        if (v == e) return;
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (marked[w]) continue;
            dfs(G, w);
        }
    }
    
    private void resetMark() {
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
    }
    
    public int getRemoveableVertice(Graph G) {
        int V = G.V();
        marked = new boolean[V];
        for (int v = 0; v < V; v++) {
            resetMark();
            Integer start = (v + 1)%V; //Any start point is OK.
            dfs(G, start, v);
            
            boolean allAdjMarked = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    allAdjMarked = false;
                    break;
                }
            }
            
            if (allAdjMarked) return start;
        }
        return -1;
    }
}