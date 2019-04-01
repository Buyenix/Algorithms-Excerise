public class RangeLookupCSV
{
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);
        ST<String, String> st = new ST<String, String>();
        
        while(in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            st.put(key, val);      
        }
        
        String keyL, keyR;
        if (args[3] == null || args[4] == null) throw new RuntimeException("Invalid args[3] or agrs[4]!");
        if (args[3].compareTo(args[4]) > 0) {
            keyL = args[4];
            keyR = args[3];
        } else {
            keyL = args[3];
            keyR = args[4];
        }
        for (String key : st.keys()) {
            if (key.compareTo(keyL) >= 0 && key.compareTo(keyR) <= 0) {
                StdOut.print(key + " ");
            }
            StdOut.println();
        }
    }
}