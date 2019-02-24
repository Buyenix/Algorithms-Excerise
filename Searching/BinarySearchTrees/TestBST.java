public class TestBST
{
    public static void main(String[] args)
    {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int n = keys.length;
        BST<String, Integer> st = new BST<String, Integer>();
        BST_GOLDEN<String, Integer> st2 = new BST_GOLDEN<String, Integer>();
        for (int i = 0; i < n; i++) {
            st.put(keys[i], i);
            st2.put(keys[i], i);
        }
        
        // print keys in order using allKeys()
        StdOut.println("Testing keys() 1");
        StdOut.println("--------------------------------");
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
        StdOut.println();
        
        StdOut.println("Testing keys() 2");
        StdOut.println("--------------------------------");
        for (String s : st2.keys()) 
            StdOut.println(s + " " + st2.get(s)); 
        StdOut.println();

        // print keys in order using select
        StdOut.println("Testing select 1");
        StdOut.println("--------------------------------");
        for (int i = 0; i < st.size(); i++)
            StdOut.println(i + " " + st.select(i)); 
        StdOut.println();
        
        StdOut.println("Testing select 2");
        StdOut.println("--------------------------------");
        for (int i = 0; i < st2.size(); i++)
            StdOut.println(i + " " + st2.select(i)); 
        StdOut.println();

        // test rank, floor, ceiling
        StdOut.println("key rank floor flor2 ceil 1");
        StdOut.println("-------------------------");
        for (char i = 'A'; i <= 'Z'; i++) {
            String s = i + "";
            StdOut.printf("%2s %4d %4s %4s %4s\n", s, st.rank(s), st.floor(s), st.floor2(s), st.ceiling(s));
        }
        StdOut.println();

        StdOut.println("key rank floor flor2 ceil 2");
        StdOut.println("-------------------------");
        for (char i = 'A'; i <= 'Z'; i++) {
            String s = i + "";
            StdOut.printf("%2s %4d %4s %4s %4s\n", s, st2.rank(s), st2.floor(s), st2.floor2(s), st2.ceiling(s));
        }
        StdOut.println();
        
        // test range search and range count
        String[] from = { "A", "Z", "X", "0", "B", "C" };
        String[] to   = { "Z", "A", "X", "Z", "G", "L" };
        StdOut.println("range search1");
        StdOut.println("-------------------");
        for (int i = 0; i < from.length; i++) {
            StdOut.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
            for (String s : st.keys(from[i], to[i]))
                StdOut.print(s + " ");
            StdOut.println();
        }
        StdOut.println();
        
        StdOut.println("range search2");
        StdOut.println("-------------------");
        for (int i = 0; i < from.length; i++) {
            StdOut.printf("%s-%s (%2d) : ", from[i], to[i], st2.size(from[i], to[i]));
            for (String s : st2.keys(from[i], to[i]))
                StdOut.print(s + " ");
            StdOut.println();
        }
        StdOut.println();

        // delete the smallest keys
        for (int i = 0; i < st.size() / 2; i++) {
            st.deleteMin();
        }
        StdOut.println("After deleting the smallest " + st.size() / 2 + " keys 1");
        StdOut.println("--------------------------------");
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
        StdOut.println();

        for (int i = 0; i < st2.size() / 2; i++) {
            st2.deleteMin();
        }
        StdOut.println("After deleting the smallest " + st2.size() / 2 + " keys 2");
        StdOut.println("--------------------------------");
        for (String s : st2.keys()) 
            StdOut.println(s + " " + st2.get(s)); 
        StdOut.println();
        
        // delete all the remaining keys
        while (!st.isEmpty()) {
          st.delete(st.select(st.size() / 2));
        }
        StdOut.println("After deleting the remaining keys 1");
        StdOut.println("--------------------------------");
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
        StdOut.println();

        StdOut.println("After adding back the keys 1");
        StdOut.println("--------------------------------");
        for (int i = 0; i < n; i++) 
            st.put(keys[i], i); 
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
        StdOut.println();
        
        while (!st2.isEmpty()) {
            st2.delete(st2.select(st2.size() / 2));
        }
        StdOut.println("After deleting the remaining keys 2");
        StdOut.println("--------------------------------");
        for (String s : st2.keys()) 
            StdOut.println(s + " " + st2.get(s)); 
        StdOut.println();

        StdOut.println("After adding back the keys 2");
        StdOut.println("--------------------------------");
        for (int i = 0; i < n; i++) 
            st2.put(keys[i], i); 
        for (String s : st2.keys()) 
            StdOut.println(s + " " + st2.get(s)); 
        StdOut.println();

    }
}