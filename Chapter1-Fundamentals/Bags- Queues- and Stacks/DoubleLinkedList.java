public class DoubleLinkedList<Item> {
    private Node first;
    private Node last;
    
    private class Node {
        Item item;
        Node prev;
        Node next;
    }
    
    public DoubleLinkedList() {
        first = last = null;
    }
    
    public void insertAtHead(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        newNode.prev = null;
        first = newNode;
        if (last == null) last = first;
    }
    
    public void insertAtTail(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = last;
        newNode.next = null;
        last = newNode;
        if (first == null) first = last;
    }
    
    
    public Item removeFromHead() {
        if (first == null) return null;
        Item item = first.item;
        if (first.next != null) {
            first = first.next;
            first.prev = null;
        } else {
            first = last = null;
        }
        return item;
    }
    
    public Item removeFromTail() {
        if (last == null) return null;
        Item item = last.item;
        if (last.prev != null) {
            last = last.prev;
            last.next = null;
        } else {
            first = last = null;
        }
        return item;
    }
    
    public void insertBeforeNode(Node node, Item item) {
        if (node == null) throw new RuntimeException("Node not found!");
        if (node == first) {
            insertAtHead(item);
        } else {
            Node newNode = new Node();
            newNode.next = node;
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
        }
    }
    
    public void insertAfterNode(Node node, Item item) {
        if (node == null) throw new RuntimeException("Node not found!");
        if (last == node) {
            insertAtTail(item);
        } else {
            Node newNode = new Node();
            newNode.next = node.next;
            newNode.prev = node;
            node.next = newNode;
            newNode.next.prev = newNode;
        }
    }
    
    public Item delete(Node node) {
        if (node == null) return null;
        if (node == first) return removeFromHead();
        else if (node == last) return removeFromTail();
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node.item;
        }
    }
    
}