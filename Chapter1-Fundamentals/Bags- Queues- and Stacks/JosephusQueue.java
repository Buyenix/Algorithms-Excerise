//public class JosephusQueue {
//    private int[] survivals;
//    private int N;
//    
//    public JosephusQueue(int n) {
//        survivals = new int[n];
//        for (int i = 0; i < n; i++) survivals[i] = i;
//        N = n;
//    }
//    
//    public int howManySurvivals() { return N; }
//    
//    public int killOne(int m) {
//        m--;
//        if (N == 0) throw new RuntimeException("All died!");
//        int killId = m % N;
//        int toBeKilled = survivals[killId];
//        int[] survivalsLeft = new int[N-1];
//        int next = (killId + 1)%N;
//        for (int i = next; i < next + N - 1; i++) {
//            survivalsLeft[i-next] = survivals[i%N];
//        }
//        survivals = survivalsLeft;
//        N--;
//        //StdOut.print("Survivals: ");
//        //for(int j = 0; j < N; j++) StdOut.print(survivals[j]);
//        //StdOut.println();
//        return toBeKilled;
//    }
//    
//    public static void main(String[] args) {
//        int N = Integer.parseInt(args[0]);
//        int M = Integer.parseInt(args[1]);
//        
//        JosephusQueue allPeople = new JosephusQueue(N);
//        while( allPeople.howManySurvivals() > 0) {
//            int toBeKilled = allPeople.killOne(M);
//            StdOut.println(toBeKilled);
//        }
//    }
//}

public class JosephusQueue
{
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]),
            m = Integer.parseInt(args[1]);
        
        Queue<Integer> q = new Queue<Integer>();
        for (int i = 0; i < n; i++)
            q.enqueue(new Integer(i));
        
        int k = 0;
        while (!q.isEmpty())
        {
            int x = q.dequeue();
            
            if (++k % m == 0)
                StdOut.print(x + " ");
            else
                q.enqueue(x);
        }
        StdOut.println();
    }
}