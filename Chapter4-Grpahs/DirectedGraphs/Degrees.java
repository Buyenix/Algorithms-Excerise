public class Degrees
{
    private int[] inDegree;
    private int[] outDegree;
    private Bag<Integer> sink;
    private Bag<Integer> source;
    private boolean isMap;
    
    public Degrees(Digraph G)
    {
        int V = G.V();
        inDegree = new int[V];
        outDegree = new int[V];

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                inDegree[w]++;
                outDegree[v]++;
            }
        }
        
        source = new Bag<Integer>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                source.add(i);
            }
        }
        
        sink = new Bag<Integer>();
        isMap = true;
        for (int i = 0; i < V; i++) {
            if (outDegree[i] == 1) continue;
            isMap = false;
            if (outDegree[i] == 0) {
                sink.add(i);
            }
        }
    }
    
    public int inDegree(int v) { return inDegree[v]; }
    public int outDegree(int v) { return outDegree[v]; }
    
    public Iterable<Integer> sources() { return source; }
    public Iterable<Integer> sinks() { return sink; }
    
    public boolean isMap() { return isMap; }
}