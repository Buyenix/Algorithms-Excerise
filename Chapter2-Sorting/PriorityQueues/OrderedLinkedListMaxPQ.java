public class OrderedLinkedListMaxPQ<Key extends Comparable<Key>> {
    private class Node {
        Key   value;
        Node  next;
    }
    Node first;
    int N;
    
    public OrderedLinkedListMaxPQ() {
        first = null;
        N = 0;
    }
    
    public void insert(Key v) {
        Node newNode = new Node();
        newNode.value = v;
        newNode.next = null;
        
        if (isEmpty()) {
            first = newNode;
        } else {
            if (!isSorted(first, false/*isIncr*/)) {
                throw new RuntimeException("Current data is not sorted!");
            }
            Node prev = null;
            Node cur = first;
            while(cur != null && less(v, cur.value)) {
                prev = cur;
                cur = cur.next; 
            }
            newNode.next = cur;
            if (prev != null) prev.next = newNode;
            if (cur == first) first = newNode;
        }
        N++;
    }
    
    public Key max() {
        if (isEmpty()) throw new RuntimeException("Empty!");
        return first.value;
    }
    
    public Key delMax() {
        if (isEmpty()) throw new RuntimeException("Empty!");
        Key val = first.value;
        first = first.next;
        N--;
        return val;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private boolean less(Key v, Key w) {
        if (v == w) return false;
        return v.compareTo(w) < 0;
    }
    
    private boolean isSorted(Node first, boolean isIncr) {
        if (first == null) throw new RuntimeException("Not valid linked list!");
        while (first.next != null) {
            boolean isLess = less(first.value, first.next.value);
            if ((isIncr && !isLess) || (!isIncr && isLess)) {
                return false;
            }
            first = first.next;
        }
        return true;
    }
    
    public static void main(String[] args) {
        OrderedLinkedListMaxPQ<String> pq = new OrderedLinkedListMaxPQ<String>();
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
}