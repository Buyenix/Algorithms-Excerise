public class BruteForceSearch
{
    public static int search(String pat, String txt)
    {
        int N = pat.length();
        int M = txt.length();
        for (int i = 0; i < N-M; i++) {
            int j = 0;
            for (; j < M; j++) {
                if (txt.charAt(i+j) != pat.charAt(j))
                    break;
            }
            if (j == M) return i;
        }
        return N;
    }
    
    public static int search2(String pat, String txt)
    {
        int N = pat.length();
        int M = txt.length();
        int i = 0, j = 0;
        for ( ; i < N && j < M; i++) {
            if (txt.charAt(i) == txt.charAt(j)) {
                j++;
            } else {
                j = 0;
                i -= j;
            }
        }
        if (j == M) return i - j;
        else        return N;
    }
}