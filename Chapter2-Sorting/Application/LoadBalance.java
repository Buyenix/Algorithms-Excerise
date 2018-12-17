import java.util.PriorityQueue;
import java.util.Arrays;
public class LoadBalance
{
    private int M; // Process count
    private IndexMinPQ<Integer> processor;
    
    public LoadBalance(int m) {
        M = m;
        processor = new IndexMinPQ<Integer>(M);
    }
    
    public void process(Job job) {
        int size = processor.size();
        if (size < M) {
            processor.insert(size, job.getTime());
            StdOut.printf("Processor %d >>>> %s, totoal process time = %d\n", size+1, job.toString(), job.getTime());
        } else {
            int time = processor.min();
            int id = processor.delMin();
            processor.insert(id, time+job.getTime());
            StdOut.printf("Processor %d >>>> %s, totoal process time = %d\n", id+1, job.toString(), time+job.getTime());
        }
    }
    
    public static void main(String[] args) {
        int m = 3;
        LoadBalance balancer = new LoadBalance(m);
        int n = 12;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            String name = "Job" + (i+1);
            int time = StdRandom.uniform(100);
            Job job = new Job(name, time);
            jobs[i] = job;
        }
        
        Arrays.sort(jobs);
        for (int i = n-1; i >= 0; i--) {
            balancer.process(jobs[i]);
        }
    }
}