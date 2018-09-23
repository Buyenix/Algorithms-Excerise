public class ShellsortIsSubquadratic {
    public static void main(String[] args) {
        for (int N = 128; N < 500000; N *= 2) {
            Integer[] a1 = new Integer[N];
            Integer[] a2 = new Integer[N];
            Integer[] a3 = new Integer[N];
            double t1 = 0, t2 = 0, t3 = 0;
            Stopwatch timer = new Stopwatch();
            
            for (int i = 0; i < N; i++) {
                a1[i] = StdRandom.uniform(N);
                a3[i] = a2[i] = a1[i];
            }
            
            timer.reset();
            Selection.sort(a1);
            t1 = timer.elapsedTime();
            
            timer.reset();
            InsertionSortWithSentinel.sort(a2);
            t2 = timer.elapsedTime();
            
            timer.reset();
            Shell.sort(a3);
            t3 = timer.elapsedTime();
            
            StdOut.println("N: " + N);
            StdOut.println("   Selection: " + t1);
            StdOut.println("   Insetion:  " + t2);  
            StdOut.println("   Shell:     " + t3);
        }
    }
}