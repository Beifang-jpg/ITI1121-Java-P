public class Combination {

    // Instance variables.
    int first ;
    int second ;
    int third ;


    // Constructor
    public Combination( int first, int second, int third ) {
        // Your code here
        this.first = first;
        this.second = second;
        this.third = third;
    }

    // An instance method that compares this object
    // to other.
    // Always check that other is not null, i)
    // an error would occur if you tried to
    // access other.first if other was null, ii)
    // null is a valid argument, the method should
    // simply return false.

    public boolean equals( Combination other ) {
        // Put your code here and remove the line below
        return ( ( other != null ) &&
        ( first == other.first ) &&
        ( second == other.second ) &&
        ( third == other.third ) );

    }

    // Returns a String representation of this Combination.

    public String toString() {
        // Put your code here and remove the line below
        return first + ":" + second + ":" + third;
    }
}