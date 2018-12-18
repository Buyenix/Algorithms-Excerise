import java.util.Comparator;
public class Pack
{        
    public static class IntegerOrder1 implements Comparator<Pack>
    {
        public int compare(Pack v, Pack w) {
            return v.c1.compareTo(w.c1);
        }
    }
    
    public static class IntegerOrder2 implements Comparator<Pack>
    {
        public int compare(Pack v, Pack w) {
            return v.c2.compareTo(w.c2);
        }
    }
    
    public static Comparator INT_ORDER1 = new IntegerOrder1();
    public static Comparator INT_ORDER2 = new IntegerOrder2();
    public Integer c1;
    public Integer c2;
    
    public Pack(int v1, int v2) {
        c1 = v1;
        c2 = v2;
    }
}