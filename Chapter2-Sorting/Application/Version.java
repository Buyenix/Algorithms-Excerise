public class Version
{
    private int V1;
    private int V2;
    private int V3;
    
    public Version (int v1, int v2, int v3) {
        V1 = v1;
        V2 = v2;
        V3 = v3;
    }
    
    public int compareTo(Version that) {
        if (V1 > that.V1) return 1;
        else if (V1 < that.V1) return -1;
        else {
            if (V2 > that.V2) return 1;
            else if (V2 < that.V2) return -1;
            else {
                if (V3 > that.V3) return 1;
                else if (V3 < that.V3) return -1;
                else return 0;
            }
        }
    }
    
    public String toString() {
        return V1 + "." + V2 + "." + V3;
    }
}