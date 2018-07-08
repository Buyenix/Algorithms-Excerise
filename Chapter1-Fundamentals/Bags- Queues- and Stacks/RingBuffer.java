public class RingBuffer<Item> {
    private Item[] data = null;
    private int writePos = 0;
    private int N = 0;
    private int Capacity = 0;
    
    public RingBuffer(int capacity) {
        Capacity = capacity;
        data = (Item[]) new Object[capacity];
        writePos = 0;
        N = 0;
    }
    
    public boolean isEmpty() { return N == 0; }
    public boolean isFull() { return N == Capacity; }
    public int size() { return N; }
    
    
    public boolean write(Item item) {
        if (isFull()) return false;
        data[writePos] = item;
        writePos = (writePos + 1)%Capacity;
        N++;
        return true;
    }
    
    public Item read() {
        if (isEmpty()) return null;
        int readPos = (writePos - N + Capacity)%Capacity;
        Item item = data[readPos];
        N--;
        return item;
    }
    
     public static void main(String[] args){
         int capacity = 10;
         RingBuffer<String> ringBuffer = new RingBuffer<String>(capacity);
         
         /*******************TEST1*************************/
         for (int i = 0; i < capacity + 2; i++) {
             String inputItem = i+"";
             boolean putSuccess = ringBuffer.write(inputItem);
             System.out.println(putSuccess ? "Writting " + inputItem + " success" : "Writting " + inputItem + " fails" );
         }
         
         /*******************TEST2*************************/
         for (int i = 0; i < capacity + 1; i++) {
             
             if (i == capacity - 1) {
                 String takeItem = ringBuffer.read();
                 System.out.println("Reading " + takeItem + " success");
             }
             
             if (i == capacity) {
                 String takeItem = ringBuffer.read();
                 System.out.println("Reading " + takeItem + " success");
             }
             
             String inputItem = i+"";
             boolean putSuccess = ringBuffer.write(inputItem);
             System.out.println(putSuccess ? "Writting " + inputItem + " success" : "Writting " + inputItem + " fails" );
         }
         
         
     }
}