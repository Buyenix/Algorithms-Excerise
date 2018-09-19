public class Transaction implements Comparable<Transaction> {
    private final String _who;
    private final Date _date;
    private final double _amount;
    
    public Transaction(String who, Date when, double amount) {
        _who = who;
        _date = when;
        _amount = amount;
    }
    
    public Transaction(String transaction) {
        String[] sections = transaction.split("\\s+");
        _who = sections[0];
        _date = new Date(sections[1]);
        _amount = Double.parseDouble(sections[2]);
    }
    
    public String who() { return _who; }
    public Date when() { return _date; }
    public double amount() { return _amount; }
    
    public String toString() {
        return _who + " " + _date.toString() + " " + _amount;
    }
    
    public int compareTo(Transaction that) {
        if (_amount > that._amount) return 1;
        else if (_amount < that._amount) return -1;
        else return 0;
    }
    
    public boolean equals(Object that){
        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        Transaction x = (Transaction) that;
        if (!_who.equals(x._who)) return false;
        if (!_date.equals(x._date)) return false;
        if (_amount != x._amount) return false;
        return true;
    }
    
}