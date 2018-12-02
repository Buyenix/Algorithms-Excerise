public class SelectionFilter {
    private int M = 0;
    private MaxPQ<Double> pq;
    
    private class Point {
        int X = 0;
        int Y = 0;
        int Z = 0;
        public Point(int x, int y, int z) {
            X = x;
            Y = y;
            Z = z;
        }
    }
    
    public SelectionFilter(int m) {
        M = m;
        pq = new MaxPQ<Double>(M+1);
    }
    
    public void update(Point point) {
        Double dist = CalcEuclideanDistance(point);
        if (pq.size() > M) pq.delMax();
        pq.insert(dist);
    }
    
    public Double delMax() {
       return pq.delMax(); 
    }
    
    private Double CalcEuclideanDistance(Point p) {
        return Math.sqrt(p.X*p.X + p.Y*p.Y+p.Z*p.Z);
    }
    
    public static void main(String[] args) {
        int M = 10;
        int N = 100;
        SelectionFilter sf = new SelectionFilter(M);
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            int z = StdRandom.uniform(100);
            Point point = sf.new Point(x, y, z);
            sf.update(point);
        }
        
        Double[] result  = new Double[M];
        int i = 0;
        while(i < M-1) {
            result[i++] = sf.delMax();
        }
        
        StdOut.print("Closest: ");
        for(int j = result.length-2; j >= 1; j--) {
            StdOut.printf("%.3f ", result[j]);
        }
        StdOut.println();
    }
}