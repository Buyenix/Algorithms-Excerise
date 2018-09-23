public class ShellsortIncrements {
    public static void main(String[] args) {
        for (int N = 128; N < 5000000; N *= 2) {
            Integer[] a1 = new Integer[N];
            Integer[] a2 = new Integer[N];
            double t1 = 0, t2 = 0;
            Stopwatch timer = new Stopwatch();
            
            for (int i = 0; i < N; i++) {
                a1[i] = StdRandom.uniform(N);
                a2[i] = a1[i];
            }
            
            timer.reset();
            Shell.sort(a1);
            t1 = timer.elapsedTime();
            
            timer.reset();
            ShellWithNewSequence.sort(a2);
            t2 = timer.elapsedTime();
            
            StdOut.println("N: " + N);
            StdOut.println("   Shell:     " + t1);
            StdOut.println("   NewShell:  " + t2);  
        }
    }
}