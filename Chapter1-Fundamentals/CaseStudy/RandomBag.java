import java.util.Iterator;
public class RandomBag<Item> implements Iterable<Item> {
    private Item[] data;
    private int N;
    
    public RandomBag() {
        data = (Item[]) new Object[1];
        N = 0;
    }
    
    private void resize(int max) {
        Item[] newData = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            newData[i] = data[i];
        }
        data = newData;;
    }
    
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    
    public void add(Item item) {
        if (size() == data.length) resize(2 * data.length);
        data[N++] = item;
    }
    
    public Iterator<Item> iterator() {
        return new RandomBagIter();
    }
    
    private class RandomBagIter implements Iterator<Item> {
        private int[] randomSeq;
        private int num;
        public RandomBagIter() {
            num = 0;
            randomSeq = new int[N];
            for (int i = 0; i < N; i++) {
                randomSeq[i] = i;
            }
            StdRandom.shuffle(randomSeq);
        }
        
        public boolean hasNext() { return num < N; }
        public Item next() {
            int curPos = randomSeq[num++];
            return data[curPos];
        }
        public void remove() {}
    }
    
    public static void main(String[] args) {
        RandomBag<String> bag = new RandomBag<String>();
        bag.add("1");
        bag.add("2");
        bag.add("3");
        bag.add("4");
        bag.add("5");

        //StdOut.println(steque.size());
        for (int i = 0; i < 3; i++) {
            StdOut.printf("Random %d\n", i+1);
            Iterator<String> itr = bag.iterator();
            while(itr.hasNext()) {
                String str = itr.next();
                StdOut.println(str);
            }
        }
    }    
}