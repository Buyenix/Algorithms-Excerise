public class SortTransaction {
    public static Transaction[] readTransactions() {
        Queue<Transaction> trans = new Queue<Transaction>();
        In in = new In("transaction.txt");
        while (!in.isEmpty()) {
            String who = in.readString();
            int year = in.readInt();
            int month = in.readInt();
            int day = in.readInt();
            double amout = in.readDouble();
            trans.enqueue(new Transaction(who, new Date(year, month, day), amout));
        }
        
        int N = trans.size();
        Transaction[] finalTrans = new Transaction[N];
        for (int i=0; i<N; i++) {
            finalTrans[i] = trans.dequeue();
        }
        return finalTrans;
    }
    
    public static void main(String[] args) {
        Transaction[] a = readTransactions();
        Shell.sort(a);
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i].toString());
        }
    }
}