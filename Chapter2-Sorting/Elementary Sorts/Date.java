public class Date {
    
    private final int value;
    private static final int[] maxDaysNonLeapYear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] maxDaysLeapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    private static boolean isValidDate(int y, int m, int d) {
        if (y < 1) return false;
        if (m < 1 || m > 12) return false;
        int maxDay = isLeapYear(y) ? maxDaysLeapYear[m-1] : maxDaysNonLeapYear[m-1];
        if (d < 1 || d > maxDay) return false;
        return true;
    }
    
    public static boolean isLeapYear(int y) {
        return (y%400==0) || (y%4 == 0 && y%100 != 0);
    }
    public Date(int y, int m, int d) {
        if (!isValidDate(y, m, d)) {
            throw new RuntimeException("Invalid date!");
        }
        value = y * 512 + m * 32 + d;
    }
    
    public Date(String date) {
        String[] numbers = date.split("/");
        int y = Integer.parseInt(numbers[0]);
        int m = Integer.parseInt(numbers[1]);
        int d = Integer.parseInt(numbers[2]);
        if (!isValidDate(y, m, d)) {
            throw new RuntimeException("Invalid date!");
        }
        value = y * 512 + m * 32 + d;
    }
    
    public int year() {  return value/512; }
    public int month() { return (value/32)%16;}
    public int day() {return value%32;}
    
    public String toString(){
        return month() + "/" + day() + "/" + year();
    }
    
    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Date that = (Date) x;
        if (year() != that.year()) return false;
        if (month() != that.month()) return false;
        if (day() != that.day()) return false;
        return true;
    }
    
    public int compareTo(Date that) {
        if (this.year()  < that.year())  return -1;
        if (this.year()  > that.year())  return +1;
        if (this.month() < that.month()) return -1;
        if (this.month() > that.month()) return +1;
        if (this.day()   < that.day())   return -1;
        if (this.day()   > that.day())   return +1;
        return 0;
    }
}