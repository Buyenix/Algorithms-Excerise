public class Volume
{
    private int month;
    private int day;
    private int id;
    private int count;
    
    public Volume (int m, int d, int id, int c) {
        month = m;
        day = d;
        id = id;
        count = c;
    }
    
    public int compareTo(Volume that) {
        if (count > that.count) return 1;
        else if (count < that.count) return -1;
        else return 0;       
    }
    
    public String toString() {
        String mStr;
        switch(month) {
            case 1 : mStr = "Jan"; break;
            case 2 : mStr = "Feb"; break;
            case 3 : mStr = "Thu"; break;
            case 4 : mStr = "Apr"; break;
            case 5 : mStr = "May"; break;
            case 6 : mStr = "Jun"; break;
            case 7 : mStr = "Jul"; break;
            case 8 : mStr = "Aug"; break;
            case 9 : mStr = "Sep"; break;
            case 10 : mStr = "Oct"; break;
            case 11 : mStr = "Nov"; break;
            case 12 : mStr = "Dec"; break;
            default : throw new RuntimeException("Invalid month!");
        }
        return day + "-" + mStr + "-" + id + "    " + count;
    }
}