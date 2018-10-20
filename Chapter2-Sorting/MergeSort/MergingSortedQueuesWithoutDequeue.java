import java.util.Iterator;

public class MergingSortedQueuesWithoutDequeue {
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
        Iterator<Comparable> aItr = a.iterator();
        Iterator<Comparable> bItr = b.iterator();
        if (aItr.hasNext() && bItr.hasNext()) {
            boolean aIsValid = true;
            boolean bIsValid = true;
            Comparable aTemp = aItr.next();
            Comparable bTemp = bItr.next();
            while (aIsValid) {
                while (bIsValid && less(bTemp, aTemp)) {
                    finalQueue.enqueue(bTemp);
                    bIsValid = bItr.hasNext();
                    if (bIsValid) bTemp = bItr.next();
                    else break; // Reach b's end here, need jump out to copy the remaining a.
                }
                finalQueue.enqueue(aTemp);
                aIsValid = aItr.hasNext();
                if (aIsValid) {
                    aTemp = aItr.next();
                    continue;
                }
                // Reach a's end here, need to copy the remaining b.
                while (bIsValid) {
                    finalQueue.enqueue(bTemp);
                    bIsValid = bItr.hasNext();
                    bTemp = bItr.next();
                }
            }
        } else {
          if (aItr.hasNext()) finalQueue = a;
          if (bItr.hasNext()) finalQueue = b;
        }
        return finalQueue;
    }
}