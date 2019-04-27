public class GraphProperties
{
    private Graph G;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] disTo;
    private int[] eccentricity;
    private int[] girth;
    //private final int s;
    private int radius;
    private int diameter;
    
    private void resetMark()
    {
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
    }
    
    private void resetDis()
    {
        for (int i = 0; i < disTo.length; i++) {
            disTo[i] = 0;
        }
    }
    
    private void resetEdgeTo()
    {
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = -1;
        }
    }
    private int getMinOrMax(int[] a, boolean max)
    {
       if (a.length <= 0) return -1;
       int val = a[0];
       for (int i = 1; i < a.length; i++) {
           int tmp = a[i];
           if (max) val = (tmp > val) ? tmp : val;
           else     val = (tmp < val) ? tmp : val;
       }
       return val;
    }
    
    public GraphProperties(Graph G)
    {
        this.G = G;
        int V = G.V();
        marked = new boolean[V];
        disTo = new int[V];
        eccentricity = new int[V];
        //this.s = s;
        boolean checkConnectivity = false;
        for (int s = 0; s < V; s++) {
            Queue<Integer> q = new Queue<Integer>();
            q.enqueue(s);
            marked[s] = true;
            disTo[s] = 0;
            
            // Do BFS traversal
            while(!q.isEmpty()) {
                int v = q.dequeue();
                //StdOut.println("v = " + v);
                for (int w : G.adj(v)) {
                    if (marked[w]) continue;
                    marked[w] = true;
                    //edgeTo[w] = v;
                    disTo[w] = disTo[v] + 1;
                    q.enqueue(w);
                }
            }
            
            if (checkConnectivity) {
                for (int i = 0; i < V; i++) {
                    if (!marked[i]) {
                        throw new RuntimeException("Graph not connected!");
                    }
                }
                checkConnectivity = false;
            }
            
            //StdOut.println("s = " + s);
            //Utils.show("disTo["+s+"] = ", disTo);
            eccentricity[s] = getMinOrMax(disTo, true); // Find the shortest distance
            resetMark();
            resetDis();
        }
        
        radius = getMinOrMax(eccentricity, false);
        diameter = getMinOrMax(eccentricity, true);
        
        // To calcuate girth
        girth = new int[V];
        edgeTo = new int[V];
        for (int i = 0; i < V; i++) girth[i] = -1;
       //resetMark();
        
        for (int s1 = 0; s1 < V; s1++) {
            for (int s2 = 0; s2 < V; s2++) {
                //StdOut.printf("s1 = %d, s2 = %d\n", s1, s2);
                if (s1 == s2) continue;
                resetMark();
                resetDis();
                resetEdgeTo();
                
                marked[s1] = true;
                disTo[s1] = 0;
                boolean breakFlag = false;
                int dis1 = 0;
                int dis2 = 0;
                
                // Do BFS traversal from s1
                Queue<Integer> q1 = new Queue<Integer>();
                q1.enqueue(s1);
                while(!q1.isEmpty()) {
                    int v = q1.dequeue();
                    for (int w : G.adj(v)) {
                        if (w == s2) {
                            breakFlag = true;
                            dis1 = disTo[v] + 1;
                            edgeTo[w] = v;
                            break; // If reach s2, just stop.
                        }
                        if (marked[w]) continue;
                        marked[w] = true;
                        disTo[w] = disTo[v] + 1;
                        edgeTo[w] = v;
                        q1.enqueue(w);
                    }
                    if (breakFlag) break;
                }
                
                if (dis1 == 0) continue; // From s1, s2 is not reached.
                resetMark();
                //StdOut.printf("s1 = %d, s2 = %d\n", s1, s2);
                //Utils.show("edgeTo["+s2+"] = ", edgeTo);
                for (int i = s2; i != s1; i = edgeTo[i]) {
                    marked[i] = true;
                }
                marked[s1] =true;
                
                //Do BFS traversal from s2
                breakFlag = false;
                Queue<Integer> q2 = new Queue<Integer>();
                q2.enqueue(s2);
                //Utils.show("marked["+s1+"] = ", marked);
                marked[s2] = true;
                disTo[s2] = 0;
                while(!q2.isEmpty()) {
                    int v = q2.dequeue();
                    //StdOut.println("dequeue: " + v);
                    for (int w : G.adj(v)) {
                        if (w == s1 && v != s2) {
                            breakFlag = true;
                            dis2 = disTo[v] + 1;
                            break;
                        }
                        if (marked[w]) continue;
                        marked[w] = true;
                        disTo[w] = disTo[v] + 1;
                        q2.enqueue(w);
                        //StdOut.println("enqueue: " + w);
                    }
                    if (breakFlag) break;
                }
                if (dis2 == 0) continue; // No cycle
                //StdOut.printf("dis1 = %d, dis2 = %d, disTo[s1] = %d, disTo[s2] = %d\n", dis1, dis2, disTo[s1], disTo[s2]);
                int tempGirth = dis1 + dis2;
                if (girth[s1] == -1 || girth[s1] > tempGirth) girth[s1] = tempGirth;
            }
        }
    }
    
    public int eccentricity(int v) { return eccentricity[v]; }
    public int radius() { return radius; }
    public int diameter() { return diameter; }
    public int girth(int v) { return girth[v]; }
    
    public static void main(String[] args)
    {
        In in = new In("tinyG.txt");
        Graph g = new Graph(in);
        //StdOut.println(g.toString());
        GraphProperties p = new GraphProperties(g);
        int radius = p.radius();
        int diameter = p.diameter();
        int girth = p.girth(7);
        int eccentricity = p.eccentricity(7);
        StdOut.println("radius = " + radius);
        StdOut.println("diameter = " + diameter);
        StdOut.println("girth = " + girth);
        StdOut.println("eccentricity = " + eccentricity);
    }
}