public class LZW
{
    private static final int R = 256;
    private static final int L = 4096;
    private static final int W = 12;
    
    public static void compress()
    {
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++) {
            st.put(""+(char)i, i);
        }
        
        String input = BinaryStdIn.readString();
        int cur = R+1;
        while (input.length() > 0) {
            String prefix = st.longestPrefixOf(input);
            BinaryStdOut.write(st.get(prefix), W);
            int len = prefix.length();
            if (len < input.length() && cur < L) {
                String newStr = prefix + input.charAt(len);
                st.put(newStr, cur++); 
            }
            input = input.substring(len);
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }
    
    public static void expand()
    {
        String[] st = new String[L];
        for (int i = 0; i < R; i++) {
            st[i] = "" + (char)i;
        }
        st[R] = " ";
        int cur = R+1;
        int code = BinaryStdIn.readInt();
        while (true) {
            String s = st[code];
            BinaryStdOut.write(s);
            code = BinaryStdIn.readInt();
            if (code == R) break;
            if (cur == code) {
                s = s + s.charAt(0);
            } else {
                s = st[code];
            }
            if (cur < L) {
                st[cur++] = s;
            }
        }
        BinaryStdOut.close();
    }
}