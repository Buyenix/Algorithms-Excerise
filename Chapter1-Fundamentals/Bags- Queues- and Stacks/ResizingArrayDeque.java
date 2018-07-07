import java.util.Iterator;
public class ResizingArrayDeque<Item> implements Iterable<Item> {
    private Item[] data;
    private int N;
    private int leftBd;  //left  boundary
    private int rightBd; //right boundary
    
    public ResizingArrayDeque() {
        data = (Item[]) new Object[1];
        N = 0;
        leftBd = rightBd = 0;
    }
    
    public boolean isEmpty() { return N == 0;}
    public int size() { return N; }
    
    private void resize(int max) {
        Item[] newData = (Item[]) new Object[max];
        for (int i = leftBd; i < leftBd + N; i++) {
            newData[i-leftBd] = data[i%data.length];
        }
        //StdOut.printf("Resize: %d --> %d\n", data.length, newData.length);
        data = newData;
        leftBd = 0;
        rightBd = N - 1;
    }
    
    public void pushLeft(Item item) {
        int max = data.length;
        if(size() == max) {
            resize(2 * max);
            max = data.length;
        }
        leftBd = (leftBd - 1 + max)%max;
        data[leftBd] = item;
        N++;
    }
    
    public void pushRight(Item item) {
        int max = data.length;
        if(size() == max) {
            resize(2 * max);
            max = data.length;
        }
        //StdOut.printf("Prev rightBd: %d\n", rightBd);
        rightBd = (rightBd + 1)%max;
        //StdOut.printf("Post rightBd: %d\n", rightBd);
        data[rightBd] = item;
        N++;
    }
    
    public Item popLeft() {
        int max = data.length;
        Item item = data[leftBd];
        leftBd = (leftBd + 1)%max;
        N--;
        if (N == max/4) resize(max/2);
        return item;
    }

    public Item popRight() {
        int max = data.length;
        Item item = data[rightBd];
        rightBd = (rightBd - 1 + max)%max;
        N--;
        if (N == max/4) resize(max/2);
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new ResizingArrayDequeIter();
    }
    
    private class ResizingArrayDequeIter implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() { return i < size(); }
        public Item next() {
            Item item = data[(i+leftBd)%data.length];
            i++;
            return item;
        }
        public void remove() {}
    }
    
    public static void main(String[] args) {
        ResizingArrayDeque<String> deque = new ResizingArrayDeque<String>();
        deque.pushLeft("1");
        deque.pushRight("2");
        deque.pushLeft("3");
        deque.pushRight("4");
        deque.pushLeft("5");
        deque.pushRight("6");
        deque.pushLeft("7");
        deque.pushRight("8");
        deque.pushLeft("11");
        deque.pushRight("22");
        deque.pushLeft("33");
        deque.pushRight("44");
        deque.pushLeft("55");
        deque.pushRight("66");
        deque.pushLeft("77");
        deque.pushRight("88");
        //StdOut.println(steque.size());
        Iterator<String> itr = deque.iterator();
        while(itr.hasNext()) {
            String str = itr.next();
            StdOut.println(str);
        }
    }
}