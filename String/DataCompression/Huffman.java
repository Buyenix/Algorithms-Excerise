public class Huffman
{
    private static int R = 256;
    
    private static class Node implements Comparable<Node>
    {
        public final Node left;
        public final Node right;
        public char c;
        public int freq;
        
        public Node(char c, int freq, Node left, Node right)
        {
            this.c = c;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
        
        public boolean isLeaf() {
            return (this.left != null) && (this.right != null);
        }
    }
    
    public static void compress()
    {
        // Read input.
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        // Tabulate frequency counts.
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }

        Node root = buildTrie(freq);
        String[] codeWord = new String[R];
        buildCodeWord(root, codeWord, "");       
        writeTrie(root);
        BinaryStdOut.write(input.length);
        
        for (int i = 0; i < input.length; i++) {
            String code = codeWord[input[i]];
            for (int j = 0; j < code.length(); j++) {
                BinaryStdOut.write(code.charAt(j) == '1');
            }
        }
        BinaryStdOut.close();
    }
    
    public static void expand()
    {
        Node root = readTrie();
        int N = BinaryStdIn.readInt();
        for (int i = 0; i < N; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                if (BinaryStdIn.readBoolean()) {
                    x = x.right;
                } else {
                    x = x.left;
                }
            }
            BinaryStdOut.write(x.c);
        }
        BinaryStdOut.close();
    }
    
    private static Node buildTrie(int[] freq)
    {
        MinPQ<Node> pq = new MinPQ<Node>(R);
        for (int i = 0; i < R; i++) {
            if (freq[i] <= 0) continue;
            Node node = new Node((char)i, freq[i], null, null);
            pq.insert(node);
        }
        
        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node newNode = new Node('\0', left.freq+right.freq, left, right);
            pq.insert(newNode);
        }
        
        return pq.delMin();
    }
    
    private static void buildCodeWord(Node x, String[] codeWord, String s)
    {
        if (x == null) return;
        if (x.isLeaf()) {
            codeWord[x.c] = s;
        }  else {
            buildCodeWord(x.left, codeWord, s + "0");
            buildCodeWord(x.right, codeWord, s + "1");
        }
    }
    
    private static void writeTrie(Node x)
    {
        if (x == null) return;
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.c);
        } else {
            BinaryStdOut.write(false);
            writeTrie(x.left);
            writeTrie(x.right);
        }
    }
    
    private static Node readTrie()
    {
        if (BinaryStdIn.readBoolean()) {
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        } else {
            return new Node('\0', 0, readTrie(), readTrie());
        }
    }
}