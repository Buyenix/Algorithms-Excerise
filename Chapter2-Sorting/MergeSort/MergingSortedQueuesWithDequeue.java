import java.util.Iterator;
public class MergingSortedQueuesWithDequeue {
    private static boolean isSorted(Queue<Comparable> q) {
        Iterator<Comparable> itr = q.iterator();
        Comparable min, cur;
        if (itr.hasNext()) {
            min = itr.next();
            while (itr.hasNext()) {
                cur = itr.next();
                if (less(cur, min)) return false;
                min = cur;
            }
        }
        return true;
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static Queue<Comparable> merge(Queue<Comparable> a, Queue<Comparable> b) {
        if (!isSorted(a) || !isSorted(b)) {
            throw new RuntimeException("Error: Input Queue should be sorted!\n");
        }
        Queue<Comparable> finalQueue = new Queue<Comparable>();
        while (!a.isEmpty() && !b.isEmpty()) {
            if (less(a.front(), b.front()))
                finalQueue.enqueue(a.dequeue());
            else
                finalQueue.enqueue(b.dequeue());
        }
        while(!a.isEmpty())
            finalQueue.enqueue(a.dequeue());
        while(!b.isEmpty())
            finalQueue.enqueue(b.dequeue());
        return finalQueue;
    }
}
