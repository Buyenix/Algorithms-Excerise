//This is like roll down-hill
/*Steps:
 Look at center column -> Find global min in this column
    If local min: return it
    Else: Go to min left/right/above/below neighbor --> Recurse to test if is a local min
    Return the final row/coulumn index
*/
public class FindOneLocalMinValueForMatrix2 {
    private int[][] data;
    private int N;
    
    public FindOneLocalMinValueForMatrix2(int[][] a) {
        if (a == null || a.length == 0) throw new RuntimeException("Not valid matrix!");
        if (a.length != a[0].length) throw new RuntimeException("Not NxN maritx!");
        data = a;
        N = data.length;
    }
    
    private class Point2D {
        public int X;
        public int Y;
        public int Value;
        public Point2D(int x, int y) {
            X = x;
            Y = y;
            Value = data[x][y];
        }
    }
    

    
    public Point2D findLocalMin() {
        if (N == 2) {
            Point2D point = new Point2D(0, 0);
            for (int i = 0; i < N; i++) {
                point.X = i;
                for (int j = 0; j < N; j++) {
                    point.Y = j;
                    point.Value= data[i][j];
                    if (isLocalMinPoint(point)) return point;
                }
            }
            return null;
        }
        
        Point2D point = new Point2D(findMinIndexPerColumn(N/2), N/2);
        StdOut.printf("Point[%d, %d] = %d\n", point.X, point.Y, point.Value);
        while((point.X > 0 && point.X < N-1) && (point.Y > 0 && point.Y < N-1)) {
            StdOut.printf("Point[%d, %d] = %d\n", point.X, point.Y, point.Value);
            if (isLocalMinPoint(point)) return point;
            else updatePointToMinNeighbor(point);
        }
        return point;
    }
    
    private int findMinIndexPerColumn(int col) {
        int minIndex = 0;
        for(int i = 1; i < N; i++) {
            minIndex = data[i][col] < data[minIndex][col] ? i : minIndex;
        }
        return minIndex;
    }
    
    private boolean isLocalMinPoint(Point2D point) {
        int row = point.X;
        int col = point.Y;
        boolean left = true, right = true, above = true, below = true;
        if (row > 0) left = data[row][col] < data[row-1][col];
        if (row < N-1) right = data[row][col] < data[row+1][col];
        if (col > 0) above = data[row][col] < data[row][col-1];
        if (col < N-1) below = data[row][col] < data[row][col+1];
        return left && right && above && below;
    }
    
    private void updatePointToMinNeighbor(Point2D point) {
        for(int i=-1; i<=1; i+=2) {
            if (data[point.X+i][point.Y] < point.Value) {
                point.X += i;
                point.Value = data[point.X][point.Y];
            }
            if (data[point.X][point.Y+i] < point.Value) {
                point.Y += i;
                point.Value = data[point.X][point.Y];
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix={
                {11, 155, 15,  4,   102},
                {53, 6,   7,   18,  101},
                {12, 11,  10,  89,  100},
                {14, 1,   8,   5,   0  },
                {114,51,  58,  55,  99 }
        };
        
        //int[][] matrix = {{0, 4}, {7, 5}};
        //int[][] matrix = {{0, 8, 1}, {2, 5, 3}, {6, 4, 7}};
        FindOneLocalMinValueForMatrix2 finder = new FindOneLocalMinValueForMatrix2(matrix);
        Point2D point = finder.findLocalMin();
        StdOut.printf("Local min: data[%d][%d] = %d\n", point.X, point.Y, point.Value);
    }
}