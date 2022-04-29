public abstract class AbstractSeries implements Series {

    public double[] take(int k) {

        // implement the method
		double[] nextPartialSums = new double[k]; 
		for(int i=0;i<nextPartialSums.length;i++){
			nextPartialSums[i] = next();
		}
		return nextPartialSums; 
    }

}