public class AdvancedRational {
    private final long _numerator;
    private final long _denominator;
    
    public AdvancedRational(int numerator, int denominator) {
        this((long)numerator, (long)denominator);
    }
    
    public AdvancedRational(int n)
    {
        this._numerator = n;
        this._denominator = 1;
    }
    
    private AdvancedRational(long numerator, long denominator) {
        if (denominator == 0) throw new RuntimeException("Zero denominator!");
        
        long gcd = gcd(numerator, denominator);
        long temp_denominator = denominator/gcd;
        if (temp_denominator > 0) {
          _numerator = numerator/gcd;
          _denominator = temp_denominator;
        } else {
          _numerator = -numerator/gcd; 
          _denominator = -temp_denominator; // _denominator should be possitive.
        }
        if (Math.abs(_numerator) > Integer.MAX_VALUE ||
            Math.abs(_denominator) > Integer.MAX_VALUE) {
            throw new RuntimeException("Overflow value!");
        }
    }
    
    public AdvancedRational plus(AdvancedRational b) {
        //int numerator = _numerator * b._denominator + _denominator * b._numerator;
        //int denominator = _denominator * b._denominator;
        //return new AdvancedRational(numerator, denominator);
        long lcm = lcm(_denominator, b._denominator);
        long add1 = _numerator * (lcm / _denominator);
        long add2 = b._numerator * (lcm / b._denominator);
        return new AdvancedRational(add1 + add2, lcm);
    }
    
    public AdvancedRational minus(AdvancedRational b) {
//        int numerator = _numerator * b._denominator - _denominator * b._numerator;
//        int denominator = _denominator * b._denominator;
//        return new AdvancedRational(numerator, denominator);
        long lcm = lcm(_denominator, b._denominator);
        long minus1 = _numerator * (lcm / _denominator);
        long minus2 = b._numerator * (lcm / b._denominator);
        return new AdvancedRational(minus1 - minus2, lcm);
    }
    
    public AdvancedRational times(AdvancedRational b) {
        long numerator = _numerator * b._numerator;
        long denominator = _denominator * b._denominator;
        return new AdvancedRational(numerator, denominator);
    }
    
    public AdvancedRational divides(AdvancedRational b) {
        long numerator = _numerator * b._denominator;
        long denominator = _denominator * b._numerator;
        return new AdvancedRational(numerator, denominator);
    }
    
    public boolean equals(AdvancedRational that) {
        return (_numerator == that._numerator && _denominator == that._denominator);
    }
    
    public String toString() {
        if (_denominator == 1) return "" + _numerator;
        else return _numerator + "/" + _denominator;
    }
    
    public double toDouble()
    {
        return ((double)_numerator) / _denominator;
    }
    
        public AdvancedRational neg()
    {
        return new AdvancedRational(-_numerator, _denominator);
    }
    
    public AdvancedRational inverse()
    {
        return new AdvancedRational(_denominator, _numerator);
    }
    
    public static AdvancedRational zero()
    {
        return new AdvancedRational(0, 1);
    }
    
    public int numerator()
    {
        return (int)_numerator;
    }
    
    public int denominator()
    {
        return (int)_denominator;
    }
    // Return the positive gcd value.
    public static long gcd(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if ( 0 == n) return m;
        return gcd(n, m%n);
    }
    
    public static long lcm(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        return (long)(m * (n / gcd(m, n))); //parenthesis is important to avoid overflow.
    }
    
    // test client
 public static void main(String[] args)
    {
        StdOut.println(new AdvancedRational(12, 28));
        StdOut.println(new AdvancedRational(12, 28).neg());
        StdOut.println(new AdvancedRational(12, 28).inverse());
        StdOut.println();
        
        StdOut.println(new AdvancedRational(-12, 28));
        StdOut.println(new AdvancedRational(12, -28));
        StdOut.println(new AdvancedRational(-12, -28));
        StdOut.println();
        
        StdOut.println(new AdvancedRational(5));
        StdOut.println(AdvancedRational.zero());
        StdOut.println();
        
        StdOut.println(new AdvancedRational(2, 3).toDouble());
        StdOut.println(new AdvancedRational(12, 28).numerator());
        StdOut.println(new AdvancedRational(12, 28).denominator());
        StdOut.println();
        
        AdvancedRational r1 = new AdvancedRational(2, 3),
                 r2 = new AdvancedRational(4, 7);
        
        StdOut.printf("%s + %s = %s (%s)\n", r1, r2, r1.plus(r2),
                                             r1.plus(r2).equals(new AdvancedRational(26, 21)));
        
        StdOut.printf("%s - %s = %s (%s)\n", r1, r2, r1.minus(r2),
                                             r1.minus(r2).equals(new AdvancedRational(2, 21)));        
        
        StdOut.printf("%s * %s = %s (%s)\n", r1, r2, r1.times(r2),
                                             r1.times(r2).equals(new AdvancedRational(8, 21)));        
        
        StdOut.printf("%s / %s = %s (%s)\n", r1, r2, r1.divides(r2),
                                             r1.divides(r2).equals(new AdvancedRational(7, 6)));
        StdOut.println();
        
        r1 = new AdvancedRational(Integer.MAX_VALUE, 10);
        
        r2 = new AdvancedRational(5, 17);
        StdOut.printf("%s * %s = %s\n", r1, r2, r1.times(r2));
        
        r2 = new AdvancedRational(101, 13);
        //StdOut.printf("%s * %s = %s\n", r1, r2, r1.times(r2));
        
        r1 = new AdvancedRational(Integer.MAX_VALUE, Integer.MAX_VALUE - 1);
        r2 = r1.inverse();
        //StdOut.printf("%s + %s = %s\n", r1, r2, r1.plus(r2));
    }
}