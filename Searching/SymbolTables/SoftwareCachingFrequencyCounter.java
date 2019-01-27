public class SoftwareCachingFrequencyCounter
{
    public static String lastSearchKey = null;
    public static int lastSearchIdx = -1;
    public static void main(String[] args)
    {
        int minlen = Integer.parseInt(args[0]);
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(10);
        In input = new In("Tale of two cities.txt");
        while(!input.isEmpty()) {
            String word = input.readString();
            //if (word == null ) StdOut.println("null");
            //else               StdOut.println(word);
            if (word.length() < minlen) continue;
            if (lastSearchKey != null && word.compareTo(lastSearchKey) == 0) {
                st.put(word, st.get(lastSearchIdx) + 1);
            } else {
                lastSearchKey = word;
                if (st.isEmpty()) {
                    st.put(word, 1);
                    lastSearchIdx = 0;
                } else {
                    lastSearchIdx = st.rank(word);
                    if (lastSearchIdx < st.size() && word.compareTo(st.select(lastSearchIdx)) == 0)
                        st.put(word, st.get(lastSearchIdx) + 1);
                    else
                        st.put(word, 1);
                }
            }
        }
        String max = "";
        st.put(max, 0);
        for(String word: st.keys()) {
            if(st.get(word) > st.get(max)) {
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
    }
}