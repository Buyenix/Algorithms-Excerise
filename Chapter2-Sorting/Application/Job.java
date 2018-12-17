public class Job implements Comparable<Job>
{
    String Name;
    int Time;
    
    public Job(String name, int time) {
        Name = name;
        Time = time;
    }
    
    public int compareTo(Job that) {
        if (this.Time > that.Time) return 1;
        else if (this.Time < that.Time) return -1;
        else return 0;
    }
    
    public String toString() {
        return Name + ":" + Time;
    }
    
    public int getTime() {
        return Time;
    }
}