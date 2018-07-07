import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
    private Node leftBd;  //left boundary
    private Node rightBd; //right boundary
    private int  N;
    
    private class Node {
        Item item;
        Node left;
        Node right;
    }
    
    public Deque() {
        leftBd = rightBd = null;
        N = 0;
    }
    
    public boolean isEmpty() { return N == 0;}
    public int size() { return N; }
    
    public void pushLeft(Item item) {
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            leftBd = rightBd = node;
        } else {
            node.left = null;
            node.right = leftBd;
            leftBd.left = node;
            leftBd = node;
        }
        N++;
    }
    
    public void pushRight(Item item) {
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            leftBd = rightBd = node;
        } else {
            node.left = rightBd;
            node.right = null;
            rightBd.right = node;
            rightBd = node;
        }
        N++;
    }
    
    public Item popLeft() {
        if (isEmpty()) throw new RuntimeException("Deque underflow!");
        Item item = leftBd.item;
        N--;
        
        leftBd = leftBd.right;
        leftBd.left = null;
        if (isEmpty()) rightBd = leftBd;
        return item;
    }
    
    public Item popRight() {
        if (isEmpty()) throw new RuntimeException("Deque underflow!");
        Item item = rightBd.item;
        N--;
        
        rightBd = rightBd.left;
        rightBd.right = null;
        if (isEmpty()) leftBd = rightBd;
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        Node curNode = leftBd;
        public boolean hasNext() { return curNode != null; }
        public Item next() {
            Item item = curNode.item;
            curNode = curNode.right;
            return item;
        }
        public void remove() {}
    }
    
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.pushLeft("1");
        deque.pushRight("2");
        deque.pushLeft("3");
        deque.pushRight("4");
        //StdOut.println(steque.size());
        Iterator<String> itr = deque.iterator();
        while(itr.hasNext()) {
            String str = itr.next();
            StdOut.println(str);
        }
    }
}