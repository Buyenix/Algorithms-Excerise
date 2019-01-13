public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value>
{
    private Node first;
    private class Node
    {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    
    public void put(Key key, Value val) {
        for (Node x=first, p=null; x!=null; p=x, x=x.next) {
            if (key.equals(x.key)) {
                if (p != null) {
                    p.next = x.next;
                }
                break;
            }
        }
        first = new Node(key, val, first);
        
        Node p = null, x = first;
        while(x != null) {    
            if (first.key.compareTo(x.key) > 0) {
                p = x;
                x = x.next;
            } else {
                if (p == null) break;
                Node t = first.next;
                p.next = first;
                first.next = x;
                first = t;
                break;
            }
        }
    }
    
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val;
        }
        return null;
    }
    
    public void delete(Key key) {
        for (Node x = first, p = null; x != null; p = x, x = x.next) {
            if (key.equals(x.key)) {
                if (p == null) {
                    first = x.next;
                } else {
                    p.next = x.next;
                }
                return;
            }
        }
    }
    
    public boolean contains(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return true;
        }
        return false;
    }
    
    public boolean isEmpty() {
       return first != null; 
    }
    
    public int size() {
        int length = 0;
        Node x = first;
        while(x != null) {
            x = x.next;
            length++;
        }
        return length;      
    }
    
    public Key min() {
        if (first != null) return first.key;
        else               return null;
    }
    
    public Key max() {
        if (first == null) return null;
        Node p = first;
        for (Node x = first.next; x != null; p = x, x = x.next) {}
        return p.key;
    }
    
    public Key floor(Key key) {
        Node p = null, x = first;
        while(x != null && key.compareTo(x.key) > 0) {
            p = x;
            x = x.next;
        }
        if (x == null) return null;
        if (key.equals(x.key)) return x.key;
        else                   return p == null ? null : p.key;
    }
    
    public Key ceiling(Key key) {
        Node x = first;
        while(x != null && key.compareTo(x.key) > 0) {
            x = x.next;
        }
        if (x == null) return null;
        else           return x.key;
    }
    
    public int rank(Key key) {
        int rank = 0;
        Node x = first;
        while(x != null && key.compareTo(x.key) < 0) {
            x = x.next;
            rank++;
        }
        return rank; 
    }
    
    public Key select(int k) {
        Node x = first;
        while(x != null && --k > 0) {
            x = x.next;
        }
        if (k == 0) return x.key;
        else return null;
    }
    
    public void deleteMin() {
        if (first == null) return;
        first = first.next;
    }
    
    public void deleteMax() {
        if (first == null) return;
        if (first.next == null) {
            first = null;
            return;
        }
        Node p = first, x = first.next;
        while (x != null && x.next != null) {
            p = x;
            x = x.next;
        }
        p.next = null;
    }
    //TODO below APIs are pending
    int size(Key lo, Key hi) { return 0; }
    Iterable<Key> keys(Key lo, Key hi) { return null; }
    Iterable<Key> keys() { return null; }
}
        
        
        
        
        
        
        
        