public class MoveToFront<Item> {
    private Node first = null;
    private int N = 0;
    
    private class Node {
        Item item;
        Node next;
    }
    
    public boolean isEmpty() { return N == 0; }
    
    public void delete(Item item) {
        if (isEmpty()) return;
        Node prevNode = first;
        Node curNode = first.next;
        while(curNode != null) {
            if (curNode.item.equals(item)) {
                prevNode.next = curNode.next;
                N--;
                return;
            }
            prevNode = curNode;
            curNode = curNode.next;   
        }
    }
    
    public void insert(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if (isEmpty()) {
            first = newNode;
            N++;
            return;
        }
        delete(item);
        if (!first.item.equals(item)) {
            newNode.next = first;
            first = newNode;
            N++;
        }
    }
    
    public void show() {
        Node curNode = first;
        while(curNode != null) {
            StdOut.print(curNode.item + " ");
            curNode = curNode.next;
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        MoveToFront<String> data = new MoveToFront<String>();
        data.insert("1");
        data.show();
        data.insert("2");
        data.show();
        data.insert("3");
        data.show();
        data.insert("4");
        data.show();
        data.insert("1");
        data.show();
        data.insert("3");
        data.show();
        data.insert("6");
        data.show();
        data.insert("5");
        data.show();
        data.insert("1");
        data.show();    
    }
}