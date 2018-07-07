public class ReverseLinkedList<Item> {
    private Node first;
    private class Node {
        Item item;
        Node next;
    }
    public Node reverse(Node first) {
        Node reverse = null;
        while (first != null) {
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }
    
    public Node recursiveReverse(Node first) {
        if (first == null || first.next == null) return first;
        Node second = first.next;
        Node reverse = recursiveReverse(second);
        second.next = first;
        first.next = null;
        return reverse;
    }
}