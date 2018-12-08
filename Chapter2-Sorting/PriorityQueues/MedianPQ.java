public class MedianPQ<Key extends Comparable<Key>>{
    private MaxPQ<Key> lessPQ;   // store values lesser than median
    private MinPQ<Key> greaterPQ;// store values greater than median
    private Key median;
    private int N;
    
    public MedianPQ(int maxN) {
        lessPQ = new MaxPQ<Key>(maxN);
        greaterPQ = new MinPQ<Key>(maxN);
        median = null;
        N = 0;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public Key median() {
        return median;
    }
    
    public void insert(Key v) {
        if (median == null){
            median = v;
        } else {
            if (less(median, v)) {
                greaterPQ.insert(v);
                if (greaterPQ.size() > lessPQ.size()) {
                    lessPQ.insert(median);
                    median = greaterPQ.delMin();
                }
            } else {
                lessPQ.insert(v);
                if (lessPQ.size() > greaterPQ.size()) {
                    greaterPQ.insert(median);
                    median = lessPQ.delMax();
                }
            }
        }
        N++;
    }
    
    public Key delMedian() {
        Key v = median;
        N--;
        if (N == 0) {
            median = null;
        } else {
            if (lessPQ.size() > greaterPQ.size()) {
                median = lessPQ.delMax();
            } else {
                median = greaterPQ.delMin();
            }
        }
        return v;
    }
    
    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }
}