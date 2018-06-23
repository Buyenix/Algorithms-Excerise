public class Ex_1_3_9 {
    public static void main(String[] args) {
        Stack<String> vals = new Stack<String>();
        Stack<String> ops  = new Stack<String>();
        StdOut.println("Start...");
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) ;
            else if (s.equals("+") ||
                     s.equals("-") ||
                     s.equals("*") ||
                     s.equals("/") ||
                     s.equals("sqrt")) { ops.push(s); }
            else if (s.equals(")")) {
              String val = vals.pop();
              String op = ops.pop();
              if (op.equals("sqrt")) vals.push("(" + op + val + ")");
              else vals.push("(" + vals.pop() + op + val + ")");
            }
            else vals.push(s);
        }
        StdOut.println(vals.pop());
    }
}