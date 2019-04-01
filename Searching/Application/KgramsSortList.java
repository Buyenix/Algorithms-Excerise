public class KgramsSortList
{
    public static void main(String[] args)
    {
        String str = args[0];
        int k = Integer.parseInt(args[1]);
        ST<Character, Queue<Integer>> st = new ST<Character, Queue<Integer>>();
        
        
        for (int i = k; i < str.length(); i = i + k){
            Character s = str.charAt(i);
            if (!st.contains(s)) {
                Queue<Integer> q = new Queue<Integer>();
                q.enqueue(i);
                st.put(s, q);
            } else {
                st.get(s).enqueue(i);
            }
        }
        
        for (Character s : st.keys()) {
            StdOut.print(s + ": ");
            for (Integer index : st.get(s)) {
                StdOut.print(index + ", ");
            }
            StdOut.println();
        }
    }
}