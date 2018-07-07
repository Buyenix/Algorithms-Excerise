public class Steque<Item> {
    private Node first;
    private Node last;
    private int N;
    
    private class Node {
        Item item;
        Node next;
    }
    
    public Steque() {
        first = last = null;
        N = 0;
    }
    
    public boolean isEmpty() { return N == 0;}
    public int size() { return N; }
    
    public void enqueue(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = null;
        if (isEmpty()) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
        N++;
    }
    
    public void push(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = null;
        if (isEmpty()) {
            first = last = node;
        } else {
            node.next = first;
            first = node;
        }
        N++;
    }
    
    public Item pop() {
        if (isEmpty()) throw new RuntimeException("Stque underflow!");
        Item item = first.item;
        first = first.next;
        if (first == null) last = first;
        //StdOut.printf("Size: %d\n", N);
        N--;
        return item;
    }
    
    public static void main(String[] args) {
        Steque<String> steque = new Steque<String>();
        steque.push("1");
        steque.enqueue("2");
        steque.push("3");
        steque.enqueue("4");
        //StdOut.println(steque.size());
        int num = steque.size();
        for(int i = 0; i < num; i++) {
            String str = steque.pop();
            StdOut.println(str);
        }
    }
    
}