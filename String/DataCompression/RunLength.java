public class RunLength
{
    public static void compress()
    {
        int cnt = 0;
        boolean old = false;
        while (!BinaryStdIn.isEmpty()) {
            boolean v = BinaryStdIn.readBoolean();
            if (v != old) {
                BinaryStdOut.write(cnt);
                old = v;
                cnt = 0;
            } else {
                if (cnt == 255) {
                    BinaryStdOut.write(cnt);
                    cnt = 0;
                    BinaryStdOut.write(cnt);
                }
            }
            cnt++;
        }
        BinaryStdOut.write(cnt);
        BinaryStdOut.close();
    }
    
    public static void expand()
    {
        boolean old = false;
        while (!BinaryStdIn.isEmpty()) {
            int cnt = BinaryStdIn.readInt();
            while (cnt-- > 0) {
                BinaryStdOut.write(old);
            }
            old = !old;
        }
        BinaryStdOut.close();
    }
}