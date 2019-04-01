public class SparseVector
{
    private HashST<Integer, Double> st;
    
    public SparseVector() { st = new HashST<Integer, Double>(); }
    
    public int size() { return st.size(); }
    
    public void put(int i, double x) { st.put(i, x); }
    
    public void delete(int i) { st.delete(i); }
    
    public double get(int i)
    {
        if (!st.contains(i)) return 0;
        else                 return st.get(i);
    }
    
    public Iterable<Integer> keys() { return st.keys(); }
    
    public double dot(double[] that)
    {
        double sum = 0;
        for(int i : st.keys())
            sum += st.get(i) * that[i];
        return sum;
    }
    
    public SparseVector add(SparseVector that)
    {
        SparseVector result = new SparseVector();
        for(int i : st.keys()) {
            result.put(i, st.get(i));
        }
        for(int i : that.keys()) {
            double thatVal = that.get(i);
            if (st.contains(i)) {
                double addValue = st.get(i) + thatVal;
                if (addValue == 0) {
                    result.delete(i);
                } else {
                    result.put(i, addValue);
                }
            } else {
                result.put(i, thatVal);
            }
        }
        return result;
    }
}