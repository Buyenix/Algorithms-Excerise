public class UnorderedLinkedListMaxPQ<Key extends Comparable<Key>> {
    private class Node {
        Key   value;
        Node  next;
    }
    Node first;
    int N;
    
    public UnorderedLinkedListMaxPQ() {
        first = null;
        N = 0;
    }
    
    public void insert(Key v) {
        Node newNode = new Node();
        newNode.value = v;
        newNode.next = first;
        first = newNode;
        N++;
    }
    
    public Key max() {
        if (isEmpty()) throw new RuntimeException("Empty!");
        return first.value;
    }
    
    public Key delMax() {
        if (isEmpty()) throw new RuntimeException("Empty!");
        Node cur = first;
        if (first.next == null) {
            N--;
            return first.value;
        }
        Node prevCur = first;
        cur = cur.next;
        Node prevMax = null;
        Node max = first;
        while(cur != null) {
            if (less(max.value, cur.value)) {
                max = cur;
                prevMax = prevCur;
            }
            prevCur = cur;
            cur = cur.next;
        }
        Key val = max.value;
        if (prevMax != null) {
            prevMax.next = max.next;
        } else {
            first = max.next;
        }
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
        UnorderedLinkedListMaxPQ<String> pq = new UnorderedLinkedListMaxPQ<String>();
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        pq.insert("this");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
}