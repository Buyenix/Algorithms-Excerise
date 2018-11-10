public class NutsAndBolts {
    private int[] nuts;
    private int[] bolts;
    private int N;
    
    public void genNutsAndBolts(int n) {
        N = n;
        if (N < 1) throw new RuntimeException("Error: N is invalid!");
        nuts = new int[N];
        bolts = new int[N];
        for (int i = 0; i < N; i++){
            nuts[i] = i;
            bolts[i] = 3*i+1;
        }
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);
    }
    
    // To check whether nut and bolt are matched.
    public boolean isPair(int nut, int bolt) {
        return bolt == (3 * nut + 1);
    }
    
    // Entrance to fit all nuts and bolts.
    public void fit() {
        //pairs is for tracking the bolts index matched with buts
        // (nut[i], bolts[pair[i]] is a final pair.
        int[] pairs = new int[N];
        
        for (int i = 0; i < N; i++) {
            fitOne(pairs, i);
        }
        // According to pairs, adjust bolts.
        int[] newBolts = new int[N];
        for (int i = 0; i < N; i++) {
            newBolts[i] = bolts[pairs[i]];
        }
        bolts = newBolts;
    }
    
    public int compare(int nut, int bolt) {
        if (nut > (bolt - 1)/3) return 1;
        else if (nut < (bolt - 1)/3) return -1;
        else return 0;
    }
    
    public void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    // Sort the bolts and the final lt will be bolt index
    // fitted for nut.
    public int sort(int ith, int lo, int hi) {
        if (hi <= lo) return lo;
        int lt = lo, i = lo, gt = hi;
        int curNut = nuts[ith];
        int matchId = lo;
        StdOut.printf("lt = %d, i = %d, gt = %d,\n", lt, i, gt);
        show(false);
        while(i <= gt) {
            int cmp = compare(curNut, bolts[i]);
            if (cmp == 1)          exch(bolts, lt++, i++);
            else if (cmp == -1)    exch(bolts, i, gt--);
            else                   i++;
            StdOut.printf("lt = %d, i = %d, gt = %d,\n", lt, i, gt);
            show(false);
        }
        
        StdOut.printf("Match = %d\n", lt);
        return lt;
    }
    
    // To fit one nut and save the bolt index in pair
    public void fitOne(int[] pairs, int ith) {
        int lo = 0, hi = N-1;
        
        StdOut.printf("ith = %d\n", ith);
        
        StdOut.print("MatchId:");
        for(int i = 0; i < N; i++) {
            StdOut.printf("%3d", pairs[i]);
        }
        StdOut.println();
        
        // Since bolts are sorted, we can narrow the look up
        // range by comparing the previous fitted nut-bolt pairs.
        for (int i = 0; i < ith; i++) {
            int cmp = compare(nuts[ith], bolts[pairs[i]]);
            StdOut.printf("nut = %d, bolt = %d, cmp = %d\n", nuts[ith], bolts[pairs[i]], cmp);
            if (cmp == 1) {
                if (lo < pairs[i] + 1) {
                    StdOut.printf("lo: %d -> %d\n", lo, pairs[i] + 1);
                    lo = pairs[i] + 1;
                }
            }
            else if (cmp == -1) {
                if (hi > (pairs[i] - 1)) {
                    StdOut.printf("hi: %d -> %d\n", hi, pairs[i] - 1);                    
                    hi = pairs[i] - 1;
                }
            }
            else {
                throw new RuntimeException("Error: not unique nut!");
            }
        }
        pairs[ith] = sort(ith, lo, hi);
    }
    
    public void show(boolean more) {
        StdOut.print("Nuts: ");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%6d", nuts[i]);
        }
        
        StdOut.println();
        StdOut.print("Bolts:");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%6d", bolts[i]);
        }
        StdOut.println();
        if (more) {
//            for (int i = 0; i < N; i++) {
//                StdOut.printf("%5d = %5d * 3 + 1\n", bolts[i], (bolts[i] - 1)/3);
//            }
//            StdOut.println();
        }
    }
    public static void main(String[] args) {
        NutsAndBolts onePile = new NutsAndBolts();
        onePile.genNutsAndBolts(10);
        onePile.show(true);
        onePile.fit();
        onePile.show(false);
    }
    
}