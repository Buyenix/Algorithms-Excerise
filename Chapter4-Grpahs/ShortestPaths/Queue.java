import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int N = 0;
    
    private class Node {
        Item item;
        Node next;
    }
    
    public boolean isEmpty() { return N == 0;}
    public int size() { return N; }
    
    public void enqueue(Item item) {
//        Node oldLast = last;
//        last = new Node();
//        last.item = item;
//        last.next = null;
//        if (isEmpty()) first = last;
//        else oldLast.next = last;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        N++;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue underflow!");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) first = last = null;
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new LinkedListQueueIterator();
    }
    
    private class LinkedListQueueIterator implements Iterator<Item> {
        Node cur = first;
        public boolean hasNext() { return cur != null; }
        public Item next() {
            Item item = cur.item;
            cur = cur.next;
            return item;
        }
        public void remove() {}
    }
}