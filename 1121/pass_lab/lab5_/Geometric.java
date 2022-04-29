public class Geometric extends AbstractSeries {

    // instance variables
    int i = 1;
    double count1 = 0;
    double count2 = 0;

    public double next() {


    	if(count2==0){ 
    		count1 = count1/Math.pow(2,i-1) + 1; 
    		count2++; 
    		i++; 
    		return count1;
    	}
    	else{
    		count1 += count2/Math.pow(2,i-1); 
    		i++; 
    	}
    	return count1;
    }

}
