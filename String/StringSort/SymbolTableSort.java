public class SymbolTableSort
{
    public static void sort(String[] a, int W)
    {
        int N = a.length;
        String[] aux = new String[N];
        
        for (int d = W-1; d >= 0; d--) {
          BinarySearchST<Integer, Integer> st = new BinarySearchST<Integer, Integer>(N);
          for (int i = 0; i < N; i++) {
              int idx = a[i].charAt(d);
              //StdOut.println(st.size());
              //StdOut.println(idx + " " + st.get(st.rank(idx)));
              if (st.contains(idx)) st.put(idx, st.get(st.rank(idx)) + 1);
              else                  st.put(idx, 1);
          }
          
          int[] count = new int[st.size() + 1];
          int k = 1;
          for(Integer i : st.keys()) {
              count[k++] = st.get(st.rank(i));
          }
          
          for (int i = 1; i < count.length; i++) {
              count[i] += count[i-1];
          }
          
          for (int i = 0; i < count.length; i++) {
              StdOut.print(count[i] + " ");
          }
          StdOut.println();
          for (int i = 0; i < N; i++) {
              int idx = a[i].charAt(d);
              StdOut.printf("%s at %d --> %s\n", a[i], i, a[i].charAt(d));
              StdOut.println(idx);
              StdOut.printf("count[%d] = %d\n", st.rank(idx), count[st.rank(idx)]);
              aux[count[st.rank(idx)]++] = a[i];
          }
          
          for (int i = 0; i < N; i++) {
              a[i] = aux[i];
          }
        }
    }
    
    public static void main(String[] args)
    {
        String[] a = {"4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524", "1ICK750", "3CIO720", "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723"};
        sort(a, 7);
        for(int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
}