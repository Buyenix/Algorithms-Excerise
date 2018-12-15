import java.util.Arrays;

public class Frequency {

    public static void main(String[] args) {

        // read in and sort the input strings
        String[] a = StdIn.readAllStrings();
        int n = a.length;
        Arrays.sort(a);

        // create an array of distinct strings and their frequencies
        Record[] records = new Record[n];
        String word = a[0];
        int freq = 1;
        int m = 0;
        Frequency inst = new Frequency();
        for (int i = 1; i < n; i++) {
            if (!a[i].equals(word)) {
                records[m++] = inst.new Record(word, freq);
                word = a[i];
                freq = 0;
            }
            freq++;
        }
        records[m++] = inst.new Record(word, freq);

        // sort by frequency and print results
        Arrays.sort(records, 0, m);
        for (int i = m-1; i >= 0; i--) 
            StdOut.println(records[i]);
    }
    
    private class Record
    {
        private String name;
        private int count;
        
        public Record(String s, int n) {
            name = s;
            count = n;
        }
        
        public int compareTo(Record that) {
            if (count > that.count) return 1;
            else if (count < that.count) return -1;
            else return 0;
        }
    }
}