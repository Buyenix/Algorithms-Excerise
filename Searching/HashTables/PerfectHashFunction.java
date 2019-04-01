public class PerfectHashFunction
{
    public static void getPerfectHashFunction(char[] letters)
    {
        int N = letters.length;
        if (N <= 0) throw new IllegalArgumentException("Keys count <= 0!");
        boolean isFound = false;

        for(int M = N; M < 1000; M++) {
            //StdOut.printf("M = %d", M);
            int[] index = new int[M];
            for (int i = 0; i < M; i++) index[i] = 0;
                
            for(int a = 1; a < 1000; a++) {
                //StdOut.printf("  a = %d\n", a);
                //StdOut.print("   ");
                for (int i = 0; i < N; i++) {
                    index[(a * (int)letters[i])%M] = 1;
                    //StdOut.printf("%s->%d, ", letters[i], (a * (int)letters[i])%M);
                }
                //StdOut.println();
                int sum = 0;
                for (int i = 0; i < M; i++) {
                    sum += index[i];
                    //StdOut.print(index[i] + " ");
                }
                //StdOut.println();
                if (sum == N) {
                    StdOut.printf("M = %d, a = %d\n", M, a);
                    isFound = true;
                    break;
                } else {
                    for (int i = 0; i < M; i++) index[i] = 0;
                }
            }
            if (isFound) break;
        }
        if (!isFound) StdOut.println("Not found.");
    }
    
    public static void main(String[] args) {
        String s = "EASYQUTION";
        char[] letters = s.toCharArray();
        for(int i = 0; i < letters.length; i++) {
            //StdOut.print(letters[i] + " ");
            StdOut.print((int)letters[i] + " ");
        }
        StdOut.println();
        getPerfectHashFunction(letters);
    }
}