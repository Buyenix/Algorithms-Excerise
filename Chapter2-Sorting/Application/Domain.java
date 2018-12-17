import java.util.Arrays;
public class Domain implements Comparable<Domain>
{
    private final String[] fields;
    private final int n;
    
    public Domain(String s) {
        fields = s.split("\\.");
        n = fields.length;
    }
    
    public String toString() {
        if (n==0) return "";
        String s = fields[0];
        for (int i=1; i<n; i++)
            s += fields[i];
        return s;
    }
    
    public int compareTo(Domain that) {
        int n = Math.min(this.n, that.n);
        for (int i=0; i<n; i++) {
            String s1 = this.fields[n-i-1];
            String s2 = that.fields[n-i-1];
            int compare = s1.compareTo(s2);
            if (compare != 0) return compare;
        }
        return this.n - that.n;
    }
}