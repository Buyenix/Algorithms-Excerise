import java.util.Iterator;
public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    private int head = 0;
    private int tail = 0;
    
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    
    public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for(int i=0; i<=N; i++) {
            temp[i] = a[(i+head)%a.length];
        }
        a = temp;
        head = 0;
        tail = N;
    }
    
    public void enqueue(Item s) {
        if (N == a.length) resize(2 * a.length);
        a[tail++] = s;
        if (tail == a.length) tail = 0;
        N++;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue Underflow!");
        Item temp = a[head];
        a[head] = null;
        if (++head == a.length) head = 0;
        if (--N == a.length / 4 && N > 0) resize(a.length/2);
        return temp;
    }
    
    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }
    
    private class ArrayQueueIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() { return i < N; }
        public Item next() {
            if (!hasNext()) throw new RuntimeException("No such element!");
            Item item = a[(i + head) % a.length];
            i++;
            return item;
        }
        public void remove() {}
    }
}