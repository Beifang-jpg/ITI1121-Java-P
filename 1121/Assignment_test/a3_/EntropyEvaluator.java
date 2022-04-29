/**
 * This class enables calculating (weighted-average) entropy values for a set of
 * datasets
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class EntropyEvaluator {

	/**
	 * A static method that calculates the weighted-average entropy of a given set
	 * (array) of datasets. The assignment description provides a detailed
	 * explanation of this calculation. In particular, note that all logarithms are
	 * to base 2. For your convenience, we provide a log2 method. You can use this
	 * method wherever you need to take logarithms in this assignment.
	 * 
	 * @param partitions is the array of datasets to compute the entropy of
	 * @return Shannon's logarithmic entropy (to base 2) for the partitions
	 */
	public static double evaluate(DataSet[] partitions) {
		double totalYes = 0;
		double sum = 0;
		double dataLength = 0;
		String whatever;
		whatever = partitions[0].getAttribute(partitions[0].attributes.length - 1).getValues()[0];
		for (int i = 0; i < partitions.length; i++) {
			dataLength += partitions[i].getNumberOfDatapoints();
		}
		for (int i = 0; i < partitions.length; i++) {
			double entropyP = 0;
			double yes = 0;
			DataSet part = partitions[i];
			double valCount = part.getNumberOfDatapoints(); 
			for (int j = 0; j < part.getNumberOfDatapoints(); j++) {
				String v = part.getValueAt(j, part.attributes.length - 1);
				if (v.equals(whatever)) {
					yes++;
					totalYes++;
				}
			}
			if (yes != 0 && yes != valCount) {
				entropyP -= (yes / valCount) * EntropyEvaluator.log2(yes / valCount);
				entropyP -= ((valCount - yes) / valCount) * log2(((valCount - yes) / valCount));
			}
			sum += part.getNumberOfDatapoints() / dataLength * entropyP;
		}
		double entropyD = 0;
		entropyD -= (totalYes / dataLength) * EntropyEvaluator.log2(totalYes / dataLength);
		entropyD -= ((dataLength - totalYes) / dataLength) * log2(((dataLength - totalYes) / dataLength));
		return entropyD - sum;
	}

	/**
	 * Calculate base-2 logarithm for a given number
	 * 
	 * @param x is the number to take the logarithm of
	 * @return base-2 logarithm for x
	 */
	public static double log2(double x) {
		return (Math.log(x) / Math.log(2));
	}
}
