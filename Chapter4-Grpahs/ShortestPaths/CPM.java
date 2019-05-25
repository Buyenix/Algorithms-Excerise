public class CPM
{
    public static void main(String[] args)
    {
        int N = StdIn.readInt();
        int source = 2*N;
        int sink = 2*N + 1;
        
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*N+2);
        for (int i = 0; i < N; i++) {
            G.addEdge(new DirectedEdge(source, i, 0.0));
            G.addEdge(new DirectedEdge(i+N, sink, 0.0));
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(i, i+N, duration));
            for (int j = 1; j < a.length; j++) {
                int m = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i+N, m, 0.0));
            }
        }
        AcyclicLP lp = new AcyclicLP(G, source);
        StdOut.println("Start times:");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%4d: %5.1f\n", i, lp.disTo(i));
        }
        StdOut.printf("Finish time: %5.1f\n", lp.disTo(sink));
    }
}