import java.util.Iterator;

public class QueueWithCircularLinkedList<Item> implements Iterable<Item> {
    private Node last = null;
    
    private class Node {
        Item item;
        Node next;
    }
    
    public boolean isEmpty() { return last == null;}
    public int size() {
        if (isEmpty()) return 0;
        int i = 1;
        Node curNode = last.next;
        while(curNode != last) {
            i++;
            curNode = curNode.next;
        }
        return i;
    }
    
    public void enqueue(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if (isEmpty()) {
            newNode.next = newNode;
        } else {
            newNode.next = last.next;
            last.next = newNode;
        }
        last = newNode;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue underflow!");
        Item item = last.next.item;
        if (last == last.next) {
            last = null;
        } else {
            last.next = last.next.next;
        }
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new LinkedListQueueIterator();
    }
    
    private class LinkedListQueueIterator implements Iterator<Item> {
        Node cur = last;
        public boolean hasNext() { return cur != null && cur.next != cur; }
        public Item next() {
            Item item = cur.next.item;
            cur = cur.next;
            return item;
        }
        public void remove() {}
    }
}