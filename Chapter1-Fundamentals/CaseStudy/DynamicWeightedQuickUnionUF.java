public class DynamicWeightedQuickUnionUF {
    private Node first;
    private int count;
    
    private class Node {
        int index;
        int size;
        Node parent;
        Node next;
        
        public Node() {
            index = -1;
            size = 1;
            parent = null;
            next = null;
        }
    }
    
    public DynamicWeightedQuickUnionUF() {
        count = 0;
        first = null;
    }
    
    public int getMax() { return (first == null) ? -1 : first.index; }
    public boolean isEmpty() { return count == 0; }
    public int count() { return count; }
    public boolean connected(int p, int q) { return find(p) == find(q); }
    public int newSite(int val) {
        Node current = first;
        first = new Node();
        first.index = val;
        first.parent = first;
        first.next = current;
        count++;
        return first.parent.index;
    }
    
    public Node find(int p) {
        //StdOut.println("p: " + p);
        Node current = first;
        //StdOut.println("Current index: " + current.index);
        while (p != current.index) {
            current = current.next;
            //StdOut.println("Current index: " + current.index);
        }
        
        while (p != current.parent.index) {
            //StdOut.println("Current index: " + current.index);
            current = current.parent;
            p = current.index;
        }
        
        return current;
    }
    
    public void union(int p, int q) {
        Node rootP = find(p);
        Node rootQ = find(q);
        if (rootP.index == rootQ.index) return;
        if (rootP.size < rootQ.size) {
            rootP.parent = rootQ;
            rootQ.size += rootP.size;
        } else {
            rootQ.parent = rootP;
            rootP.size += rootQ.size;
        }
        count--;
    }
    
    public void print() {
        Node current = first;
        while(current.next != null) {
            StdOut.print("->" + current.index);
            current = current.next;
        }
        StdOut.printf("-> %d\n", current.index);
    }
    
    public static int max(int p, int q) { return p > q ? p : q; }
    public static void main(String[] args) {
        In file = new In(args[0]);
        DynamicWeightedQuickUnionUF uf = new DynamicWeightedQuickUnionUF();
        while (!file.isEmpty()) {
            int p = file.readInt();
            int q = file.readInt();
            for (int i = uf.getMax() + 1; i <= max(p, q); i++) {
                uf.newSite(i);    
            }
            if (uf.connected(p, q)) {
                StdOut.println(p + " and " + q + " are already connected.");
                continue;
            }
            uf.union(p, q);
            StdOut.println("Just connected " + p + " and " + q);
        }
    }   
}