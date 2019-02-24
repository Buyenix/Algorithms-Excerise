public class NonrecursiveBST <Key extends Comparable<Key>, Value>
{
    private Node root;
    private class Node
    {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        
        public Node(Key key, Value val)
        {
            this.key = key;
            this.val = val;
        }
        
        public Value get(Key key)
        {
            Node x = root;
            while (x != null) {
                int cmp = key.compareTo(x.key);
                if      (cmp < 0) x = x.left;
                else if (cmp > 0) x = x.right;
                else              return x.val;
            }
            return null;
        }
        
        public void put(Key key, Value val)
        {
            if (root == null) {
                root = new Node(key, val);
                return;
            }
            Node x = root;
            while (x != null) {
                int cmp = key.compareTo(x.key);
                if (cmp < 0) {
                    if (x.left == null) {
                        x.left = new Node(key, val);
                        return;
                    } else {
                        x = x.left;
                    }
                } else if (cmp > 0) {
                    if (x.right == null) {
                        x.right = new Node(key, val);
                        return;
                    } else {
                        x = x.right;
                    }
                } else {
                    x.val = val;
                }
            }
        }
        
        public Iterable<Key> keys()
        {
            Stack<Node> stack = new Stack<Node>();
            Queue<Key> queue = new Queue<Key>();
            Node x = root;
            while ( x != null && !stack.isEmpty()) {
                if (x != null) {
                    stack.push(x);
                    x = x.left;
                } else {
                    x = stack.pop();
                    queue.enqueue(x.key);
                    x = x.right;
                }
            }
            return queue;
        }
    }
}