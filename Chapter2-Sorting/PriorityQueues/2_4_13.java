public class 2_4_13 {
    public Key delMax()
    {
        Key max = pq[1]; // Retrieve max key from top.
        exch(1, N--); // Exchange with last item.
        pq[N+1] = pq[N]; // Avoid check j < N in sink
        sink(1); // Restore heap property.
        return max;
    }
    
    private void sink(k) {
        while (2*k <= N) {
            int j = 2*k;
            if (less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k=j;
        }
    }
}