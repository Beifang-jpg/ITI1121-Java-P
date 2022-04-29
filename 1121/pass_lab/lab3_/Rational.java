

public class Rational {

    private int numerator;
    private int denominator;

    // constructors

    public Rational(int numerator) {
	     // Your code here
       this.numerator = numerator ;
       denominator = 1 ;
    }

    public Rational(int numerator, int denominator) {
	     // Your code here
        if (denominator < 0) {
            denominator = -1 * denominator;
            numerator = -1 * numerator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    // getters

    public int getNumerator() {
	     return numerator;
    }

    public int getDenominator() {
	     return denominator;
    }

    // instance methods

    public Rational plus(Rational other) {
	     // Your code here
        int newDenominator = denominator * other.denominator;
        int newNumerator = numerator * other.denominator;
        int newOtherNumerator = other.numerator * denominator;
        int sum = newNumerator + newOtherNumerator;
        return new Rational(sum, newDenominator);

    }

    public static Rational plus(Rational a, Rational b) {
    	// Your code here
      return a.plus(b) ;
    }

    // Transforms this number into its reduced form

    private void reduce() {
      // Your code here
      if(numerator == 0){
        denominator = 1;
      }else{
        int g = gcd(Math.abs(this.numerator),this.denominator) ;
        this.numerator = this.numerator/g ;
        this.denominator = this.denominator/g ;
      }
    }
    // Euclid's algorithm for calculating the greatest common divisor
    private int gcd(int a, int b) {
      // Note that the loop below, as-is, will time out on negative inputs.
      // The gcd should always be a positive number.
      // Add code here to pre-process the inputs so this doesn't happen.

    	while (a != b)
    	    if (a > b)
    		     a = a - b;
    	    else
    		     b = b - a;
    	return a;
    }

    public int compareTo(Rational other) {
      // Your code here
      float s = this.numerator/this.denominator;

      float a = other.numerator/other.denominator;

      if(s == a){
        return 0;
      }else if (s < a){
        return -1;

      }else{
        return 1;
      }
    }


    public boolean equals(Rational other) {
      // Your code here

      if (numerator == other.numerator && denominator == other.denominator) {
        return true;
      }
      else {
        return false ; 
      }
    
    }

    public String toString() {
    	String result;
    	if (denominator == 1) {
    	    // Your code here
          result = Integer.toString(numerator);
    	} else {
    	    // Your code here
          result = numerator + "/" + denominator;
    	}
    	return result;
    }

}
