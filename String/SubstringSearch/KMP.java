public class KMP
{
    private int dfa[][];
    private int M;
    Queue<Integer> match;
    
    public KMP(String pat)
    {
        int R = 256;
        this.M = pat.length();
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X]; // Mismatch
            }
            dfa[pat.charAt(j)][j] = j + 1; // Match
            X = dfa[pat.charAt(j)][X];
        }
        match = new Queue<Integer>();
        
    }
    
    public int search(String txt)
    {
        int N = txt.length();
        int i = 0, j = 0;
        for ( ; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) return i - M;
        else        return N;
    }
    
    public void searchAll(String txt)
    {
        int N = txt.length();
        int i = 0, j = 0;
        for (; i < N; i++) {
            j = dfa[txt.charAt(i)][j];
            if (j == M) {
                match.enqueue(i-M);
                j = 0;
                i = i-M+1;
            }
        }
    }
    
    public int count() { return match.size(); }
}