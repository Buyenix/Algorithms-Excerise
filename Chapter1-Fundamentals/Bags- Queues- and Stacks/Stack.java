import java.util.Iterator;

//////////////////////////////////////////////////////////
////Implementation 1: use array to realize basic stack////
//////////////////////////////////////////////////////////
//public class Stack<Item> implements Iterable<Item>{
//    private Item[] a = (Item[]) new Object[1];
//    private int N = 0;
//    
//    public boolean isEmpty() { return N==0; }
//    
//    public int size() { return N; }
//    
//    private void reSize(int max) {
//        Item[] temp = (Item[]) new Object[max];
//        for (int i = 0; i < N; i++) {
//            temp[i] = a[i];
//        }
//        a = temp;
//    }
//    
//    public void push(Item item) {
//        if (N == a.length) reSize(2 * a.length);
//        a[N++] = item;
//    }
//    
//    public Item pop() {
//        Item item = a[--N];
//        a[N] = null;
//        if (N > 0 && N == a.length / 4) reSize(a.length / 2);
//        return item;
//    }
//    
//    public Iterator<Item> iterator() {
//        return new ReverseArrayIterator();
//    }
//    
//    private class ReverseArrayIterator implements Iterator<Item> {
//        private int i = N;
//        public boolean hasNext() { return i > 0; }
//        public Item next() { return a[--i]; }
//        public void remove() {}
//    }
//}


////////////////////////////////////////////////////////////////
////Implementation 2: use linked-list to realize basic stack////
////////////////////////////////////////////////////////////////
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    
    private class Node {
        Item item;
        Node next;
    }
    
    public boolean isEmpty() { return first == null; }
    
    public int size() { return N; }
    
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    
    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    
    public Item peek() {
        if (isEmpty()) throw new RuntimeException("Stack underflow!");
        else return first.item;
    }
    
    public Iterator<Item> iterator() {
       return new LinkedListIterator(); 
    }
    
    private class LinkedListIterator implements Iterator<Item> {
        private Node cur = first;
        public boolean hasNext() { return cur != null; }
        public Item next() {
            Item item = cur.item;
            cur = cur.next;
            return item;
        }
        public void remove() {}
    }
    
    public static Stack<String> copy(Stack<String> srcStack) {
        Stack<String> dstStack  = new Stack<String>();
        Stack<String> tempStack = new Stack<String>();
        
        Iterator<String> itr1 = srcStack.iterator();
        while(itr1.hasNext()) {
            tempStack.push(itr1.next());
        }
        itr1 = null;
        
        Iterator<String> itr2 = tempStack.iterator();
        while(itr2.hasNext()) {
            dstStack.push(itr2.next());
        }
        itr2 = null;
        tempStack = null;
        return dstStack;
    }
}