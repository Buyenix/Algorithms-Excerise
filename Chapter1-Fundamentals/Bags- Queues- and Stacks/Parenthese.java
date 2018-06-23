public class Parenthese {
    public static void main(String[] args) {
        Stack<String> leftOps = new Stack<String>();
        boolean status = true;
        String inputS = "[()]{}{[()()]}";
        //String inputS = "[(])";
        int i = 0;
        while(i < inputS.length() && status) {
            String s = String.valueOf(inputS.charAt(i++));
            if      (s.equals("(")) leftOps.push(s);
            else if (s.equals("[")) leftOps.push(s);
            else if (s.equals("{")) leftOps.push(s);
            else if (s.equals(")")) status = !leftOps.isEmpty() && (leftOps.pop().equals("("));
            else if (s.equals("]")) status = !leftOps.isEmpty() && (leftOps.pop().equals("["));
            else if (s.equals("}")) status = !leftOps.isEmpty() && (leftOps.pop().equals("{"));
            else status = false;
        }
        status = status && leftOps.isEmpty();
        StdOut.println(status);
    }
}