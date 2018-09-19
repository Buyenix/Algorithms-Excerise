public class CompareIntWithIntegerForInsertion {
     public static void main(String[] args) {
        int N = 10000000;
        int[] a1 = new int[N];
        Integer[] a2 = new Integer[N];
        double t1 = 0, t2 = 0;
        Stopwatch timer = new Stopwatch();
        
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < N; i++) {
                a1[i] = StdRandom.uniform(N*100);
                a2[i] = a1[i];
            }
            
            timer.reset();
            InsertionSortwithoutExch.sort(a2);
            t1 += timer.elapsedTime();
            
            timer.reset();
            InsertionSortWithInt.sort(a1);
            t2 += timer.elapsedTime();
        }
        
        StdOut.println("Int:     " + t2);
        StdOut.println("Integer: " + t1);       
    }
}