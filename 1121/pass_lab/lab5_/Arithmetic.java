public class Arithmetic extends AbstractSeries {

    // instance variables
    double a ;
    double b ;

    public Arithmetic(){
        this.a = 0;
        this.b = 0;
    }

    public double next() {

        // implement the method
        this.a = a+1 ;
        this.b = b+a;

        return b ;
        

        
    }
}