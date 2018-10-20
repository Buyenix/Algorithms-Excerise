import java.util.Iterator;
public class BottomupQueueMergesort {
    private static Queue<Queue<Comparable>> genRawQueues(int N) {
        Queue<Queue<Comparable>> rawQueues = new Queue<Queue<Comparable>>();
        for (int i = 0; i < N; i++) {
            Integer val = StdRandom.uniform(1, 1000);
            Queue<Comparable> queue = new Queue<Comparable>();
            queue.enqueue(val);
            rawQueues.enqueue(queue);
        }
        return rawQueues;
    }
    
    public static void sort(Queue<Queue<Comparable>> a) {
        StdOut.print("Raw: ");
        show(a);
        while (a.size() > 1) {
            Queue<Comparable> first = a.dequeue();
            Queue<Comparable> second = a.dequeue();
            Queue<Comparable> sorted = MergingSortedQueuesWithDequeue.merge(first, second);
            //Queue<Comparable> sorted = MergingSortedQueuesWithoutDequeue.merge(first, second);
            a.enqueue(sorted);
        }
        StdOut.print("Sort:");
        show(a);
    }
    
    public static void show(Queue<Queue<Comparable>> a) {
        Iterator<Queue<Comparable>> itr1 = a.iterator();
        while(itr1.hasNext()) {
            Queue<Comparable> queue = itr1.next();
            Iterator<Comparable> itr2 = queue.iterator();
            while(itr2.hasNext()) {
                Comparable val = itr2.next();
                StdOut.print(val + " ");
            }
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        Queue<Queue<Comparable>> rawQueues = genRawQueues(10);
        sort(rawQueues);
    }
}