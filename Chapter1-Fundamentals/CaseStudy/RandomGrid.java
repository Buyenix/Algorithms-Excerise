import java.util.Iterator;
public class RandomGrid {
    public static RandomBag<Connection> generate(int N) {
        RandomBag<Connection> bag = new RandomBag<Connection>();
        UF uf = new UF(N);
        for (int i=0; i<N; i++) {
            int p = StdRandom.uniform(N);
            int q = StdRandom.uniform(N);
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            bag.add(new Connection(p, q));
        }
        return bag;
    }
    
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RandomBag<Connection> bag = generate(N);
        Iterator<Connection> itr = bag.iterator();
            while(itr.hasNext()) {
                Connection c = itr.next();
                StdOut.printf("(%d, %d)\n", c.p, c.q);
            }
    }
}