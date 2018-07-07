import java.util.Iterator;
public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] data;
    private int N;
    
    public RandomQueue() {
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
    public int size() { return N; }
    
    public void enqueue(Item item) {
        if (size() == data.length) resize(2*data.length);
        data[N++] = item;
    }
    
    public Item dequeue() {
        int randomPos = StdRandom.uniform(N);
        Item item = data[randomPos];
        data[randomPos] = data[N-1];
        N--;
        if (size() == data.length/4) resize(data.length/2);
        return item;
    }
    
    public Item sample() {
        int randomPos = StdRandom.uniform(N);
        return data[randomPos];      
    }
    
    public Iterator<Item> iterator() {
        return new RandomQueueIter();
    }
    
    private class RandomQueueIter implements Iterator<Item> {
        private int[] randomSeq;
        private int num;
        public RandomQueueIter() {
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
        RandomQueue<String> cards = new RandomQueue<String>();
        String[] card = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] type = {"H", "S", "D", "C"}; //"Heart", "Spade", "Diamond", "Club"
        for(int i = 0; i < card.length; i++)
            for (int j = 0; j < type.length; j++)
                cards.enqueue(type[j] + "-" + card[i]);

        String[] player1 = new String[13];
        String[] player2 = new String[13];
        String[] player3 = new String[13];
        String[] player4 = new String[13];
        
        int cardsNum = cards.size();
        for(int k = 0; k < cardsNum; k++) {
            int seq = k % 4;
            int round = k / 4;
            String oneCard = cards.dequeue();
            //StdOut.println(cards.size());
            switch(seq) {
                case 0 : player1[round] = oneCard; break;
                case 1 : player2[round] = oneCard; break;
                case 2 : player3[round] = oneCard; break;
                case 3 : player4[round] = oneCard; break;
                //default: throw new RuntimeException("Bad card!"); break;
            }
        }
        
        StdOut.print("Player1's Cards: ");
        for(int i1 = 0; i1 < player1.length; i1++) {
            StdOut.printf("%s ", player1[i1]);
        }
        StdOut.println();
        
        StdOut.print("Player2's Cards: ");
        for(int i2 = 0; i2 < player2.length; i2++) {
            StdOut.printf("%s ", player2[i2]);
        }
        StdOut.println();
        
        StdOut.print("Player3's Cards: ");
        for(int i3 = 0; i3 < player3.length; i3++) {
            StdOut.printf("%s ", player3[i3]);
        }
        StdOut.println();
        
        StdOut.print("Player4's Cards: ");
        for(int i4 = 0; i4 < player4.length; i4++) {
            StdOut.printf("%s ", player4[i4]);
        }
        StdOut.println();
    }      
}