public class LookupCSV
{
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);
        ST<String, Queue<String>> st = new ST<String, Queue<String>>();
        
        while(in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            if (!st.contains(key)) {
                Queue<String> queue = new Queue<String>();
                queue.enqueue(val);
                st.put(key, queue);
            } else {
                st.get(key).enqueue(val);
            }
            
        }
        
        In in2 = new In(args[3]);
        while(!in2.isEmpty()) {
            String query = in2.readString();
            if (st.contains(query)) {
                for(String val : st.get(query)) {
                    StdOut.println(val);
                }    
            }
        }
    }
}