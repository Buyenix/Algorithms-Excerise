//import java.util.Iterator;

public class LinkedList<Item> {
    private Node first;
    
    private class Node {
       Item item;
       Node next;
    }
    
    public LinkedList() {
        first = null;
    }
    
    // Ex 1.3.20
    public Item delete(int k) {
        if (k <= 0) return null;
        
        Node prevNode = null;
        Node curNode  = first;
        while(curNode != null && --k > 0) {
            prevNode = curNode;
            curNode  = curNode.next;
        }
        if (curNode != null && k == 0) {
            if (prevNode != null) prevNode.next = curNode.next;
            else          first = curNode.next;
            return curNode.item;
        }
        return null;
    }
    
    //Ex 1.3.21
    public boolean find(Item key) {
        Node curNode = first;
        while(curNode != null) {
            if (key.equals(curNode.item)) return true;
            curNode = curNode.next;
        }
        return false;
    }
    
    // Ex 1.3.24
    public Item removeAfter(Node node) {
        Item item = null;
        if (node != null && node.next != null) {
            item = node.next.item;
            node.next = node.next.next;
        }
        return item;
    }
    
    //Ex 1.3.25
    public void insertAfter(Node node1, Node node2) {
        if (node1 == null || node2 == null) return;
        node2.next = node1.next;
        node1.next = node2;
    }
    
    //Ex 1.3.26
    public void remove(Item key) {
        Node prevNode = null;
        Node curNode = first;
        while(curNode != null) {
            if (key.equals(curNode.item)) {
                if (prevNode != null)  prevNode.next = curNode.next;
                else  first = curNode.next;
            } else {
                prevNode = curNode;
            }
            curNode = curNode.next;
        }
    }
}