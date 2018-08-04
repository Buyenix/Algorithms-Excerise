public class QuickUnionUFWithPathCompression {
    private int[] parent;  // parent[i] = parent of i
    private int count;     // number of components
    
    public QuickUnionUFWithPathCompression(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int count() {
        return count;
    }
  
    public int find(int p) {
        validate(p);
        int currentP = p;
        while (p != parent[p]) {
            p = parent[p];
        }
        while(currentP != p) {
            int NextP = parent[currentP];
            parent[currentP] = p;
            currentP = NextP;
        }
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            parent[rootP] = rootQ; 
            count--;
        }
    }
    
    public static void main(String[] args) {
        QuickUnionUF uf = new QuickUnionUF(10);
        In in = new In("1_5_1.txt");
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            StdOut.printf("(%d, %d):\n", p, q);
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }
        StdOut.println(uf.count() + " components");
    }


}