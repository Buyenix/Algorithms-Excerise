/*Steps:
 Look at center column -> Find global min in this column
    If local min: return it
    Else: Go to samller left/right neighbor --> Find the min in that column --> Recurse in left/right half
    Return the final row/coulumn index
*/

public class FindOneLocalMinValueForMatrix {
    private int[][] data;
    private int N;
    
    public FindOneLocalMinValueForMatrix(int[][] a) {
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
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isLocalMin(i, j)) return new Point2D(i, j);
                }
            }
            return null;
        }
        
        int curRow = N/2, curCol = N/2;
        while((curRow > 0 && curRow < N-1) && (curCol > 0 && curCol < N-1)) {
            curRow = findMinIndexPerColumn(curCol);
            //StdOut.printf("Find min row: %d\n", curRow);
            if (isLocalMin(curRow, curCol)) return new Point2D(curRow, curCol);
            curCol = getMinCoulumnNeighbor(curRow, curCol);
        }
        return new Point2D(curRow, curCol);
    }
    
    private int findMinIndexPerColumn(int col) {
        int minIndex = 0;
        for(int i = 1; i < N; i++) {
            minIndex = data[i][col] < data[minIndex][col] ? i : minIndex;
        }
        return minIndex;
    }
    
    private boolean isLocalMin(int row, int col) {
        boolean left = true, right = true, above = true, below = true;
        if (row > 0) left = data[row][col] < data[row-1][col];
        if (row < N-1) right = data[row][col] < data[row+1][col];
        if (col > 0) above = data[row][col] < data[row][col-1];
        if (col < N-1) below = data[row][col] < data[row][col+1];
        return left && right && above && below;
    }
    
    private int getMinCoulumnNeighbor(int row, int col) {
        //StdOut.printf("Find Neighbor: row = %d, col = %d\n", row, col);
        //if (data[row][col-1] < data[row][col]) return col - 1;
        //if (data[row][col+1] < data[row][col]) return col + 1;
        //throw new RuntimeException("Cannot get min column neighbor!");
        return data[row][col-1] < data[row][col+1] ? col-1 : col+1;
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
        FindOneLocalMinValueForMatrix finder = new FindOneLocalMinValueForMatrix(matrix);
        Point2D point = finder.findLocalMin();
        StdOut.printf("Local min: data[%d][%d] = %d\n", point.X, point.Y, point.Value);
    }
}