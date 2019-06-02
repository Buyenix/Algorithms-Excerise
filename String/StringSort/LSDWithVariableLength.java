public class LSDWithVariableLength
{
    public static void sort(String[] a)
    {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            if (a[i].length() > maxLen) {
                maxLen = a[i].length();
            }
        }
        for (int d = maxLen-1; d >= 0; d--) {
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++) {
                count[charAt(a[i], d) + 1]++;
            }
            for (int i = 0; i < R; i++) {
                count[i+1] += count[i];
            }
            for (int i = 0; i < N; i++) {
                aux[count[charAt(a[i], d)]++] = a[i];
            }
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }
    
    private static int charAt(String a, int d)
    {
        if (d >= a.length()) return 0;
        else                 return a.charAt(d);
    }
    
    public static void main(String[] args)
    {
        String a[] = {"38A", "3TW723", "2IYEA938", "3CI34780720"};
        sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}