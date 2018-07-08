public class GeneralizedQueueWithArray<Item> {
    private Item[] data;
    private int N;
    
    public GeneralizedQueueWithArray() {
        data = (Item[]) new Object[1];
        N = 0;
    }
    
    private void resize(int max) {
        Item[] newData = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    
    public boolean isEmpty() { return N == 0; }
    public int size() {return N;}
    
    public void insert(Item item) {
        if (N == data.length) resize(2 * data.length);
        data[N++] = item;
    }
    
    public Item delete(int k) {
        if (k > N) throw new RuntimeException("Delete overflow!");
        Item item = data[k-1];
        for(int i = k; k < N; k++) {
            data[i-1] = data[i];
        }
        N--;
        if(N == data.length/4) resize(data.length/2);
        return item;
    }
    
    public static void main(String[] args) {
        GeneralizedQueueWithArray<String> queue =
            new GeneralizedQueueWithArray<String>();
        queue.insert("1");
        queue.insert("2");
        queue.insert("3");
        queue.insert("4");
        StdOut.printf("Delete 2nd: %s\n", queue.delete(2));
        StdOut.printf("Delete 2nd again: %s\n", queue.delete(2));
        StdOut.printf("Delete 3rd: %s\n", queue.delete(1));
    }
}