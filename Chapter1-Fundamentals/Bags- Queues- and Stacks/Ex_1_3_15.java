public class Ex_1_3_15 {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        
        In in = new In("Strings.txt");
        Queue<String> q = new Queue<String>();
        while (!in.isEmpty()) {
            String s = in.readString();
            StdOut.println(s);
            q.enqueue(s);
        }
        
        int pos = q.size() - k + 1;
        int i = 0;
        String s = "";
        while (i++ < pos) {
            s = q.dequeue();
        }
        StdOut.println(s);
        
    }
}