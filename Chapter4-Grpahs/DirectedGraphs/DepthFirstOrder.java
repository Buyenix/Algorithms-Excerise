public class DepthFirstOrder
{
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;
    
    public DepthFirstOrder(Digraph G)
    {
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];
        
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) dfs(G, s);
        }
    }
    
    private void dfs(Digraph G, int v)
    {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : G.adj(v)) {
            if(!marked[w]) dfs(G, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }
    
    public Iterable<Integer> pre() { return pre; }
    public Iterable<Integer> post() { return post; }
    public Iterable<Integer> reversePost() { return reversePost; }
}