import java.util.Comparator;
import java.util.Arrays;
public class California
{
    public final static Comparator UNBIASED_ELECTION_ORDER = new UnbiasedElectionOrder();
    private static class UnbiasedElectionOrder implements Comparator<String>
    {
        private static String StringOrder = "RWQOJMVAHBSGZXNTCIEKUPDYFL";
        public int compare(String v, String w) {
            int l1 = v.length();
            int l2 = w.length();
            int n = Math.min(l1, l2);
            for (int i = 0; i<n; i++) {
                int p1 = StringOrder.indexOf(v.charAt(i));
                int p2 = StringOrder.indexOf(w.charAt(i));
                if (p1 < p2) return -1;
                else if (p1 > p2) return 1;
            }
            return l1 - l2;
        }
    }
    
    public static void main(String[] args) {
        String[] candidates = {"AFG", "AFGG", "RUG", "RUFFLKG"};
        Arrays.sort(candidates, California.UNBIASED_ELECTION_ORDER);
        for (int i = 0; i < candidates.length; i++)
            StdOut.println(candidates[i]);  
    }
}