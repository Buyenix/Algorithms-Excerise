public class ParallelEdgeDetection
{
    public static int getParallelEdgeCount(Graph G)
    {
        int parallelEdges = 0;
        int V = G.V();
        int[][] edges = new int[V][V]; 
        for (int i = 0; i < V; i++) {
            for (int j : G.adj(i)) {
                if (edges[i][j] > 0) parallelEdges++;
                edges[i][j]++;
            }
        }
        return parallelEdges;
    }
}