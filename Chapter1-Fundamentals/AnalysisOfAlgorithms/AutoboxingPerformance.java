public class AutoboxingPerformance {
    public static void timeTrial(int N) {
        StdOut.println(N);
        
        FixedCapacityStackOfInts stack1 = new FixedCapacityStackOfInts(N);
        Stopwatch timer1 = new Stopwatch();
        for (int i = 0; i < N ; i++) {
            stack1.push(i);
        }
        double t1 = timer1.elapsedTime();
        
        Stopwatch timer3 = new Stopwatch();
        for (int i = 0; i < N ; i++) {
            stack1.pop();
        }
        double t3 = timer3.elapsedTime();
        StdOut.printf("  Int:     push %.1f, pop %.1f\n", t1, t3);
            
//        FixedCapacityStack<Integer> stack2 = new FixedCapacityStack<Integer>(N);
//        Stopwatch timer2 = new Stopwatch();
//        for (int i = 0; i < N ; i++) {
//            stack2.push(i);
//        }
//        double t2 = timer2.elapsedTime();
//            
//        Stopwatch timer4 = new Stopwatch();
//        for (int i = 0; i < N ; i++) {
//            stack2.pop();
//        }
//        double t4 = timer4.elapsedTime();
//        StdOut.printf("  Integer: push %.1f, pop %.1f\n", t2, t4);
    }
    
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            int N = 100;
            for (int j = 0; j < i; j++) N *= 10;
            timeTrial(N);
        }
    }
}