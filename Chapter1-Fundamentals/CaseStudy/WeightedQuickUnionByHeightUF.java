public class WeightedQuickUnionByHeightUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] height;     // height[i] = height of subtree rooted at i
    private int count;      // number of components

    public WeightedQuickUnionByHeightUF(int n) {
        count = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 0;
        }
    }

    public int count() {
        return count;
    }
  
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            p = parent[p];
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
            if (height[rootP] > height[rootQ]) parent[rootQ] = rootP;
            else if (height[rootP] < height[rootQ]) parent[rootP] = rootQ;
            else {
                height[rootP] = rootQ;
                height[rootQ]++;
            }
            count--;
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(10);
        In in = new In("1_5_1.txt");
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            StdOut.printf("(%d, %d):\n", p, q);
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            //StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}