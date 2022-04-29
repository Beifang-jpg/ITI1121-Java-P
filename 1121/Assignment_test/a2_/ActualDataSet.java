/**
 * This class is used for representing an actual dataset, that is, a dataset
 * that holds a data matrix
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class ActualDataSet extends DataSet {
	/**
	 * The data matrix
	 */
	private String[][] matrix;

	/**
	 * The source identifier for the data. When the data source is a file, sourceId
	 * will be the name and location of the source file
	 */
	private String dataSourceId;

	/**
	 * Constructor for ActualDataSet. In addition to initializing dataSourceId,
	 * numAttributes, numRows and matrix, the constructor needs to create an array of
	 * attributes (instance of the Attribute class) and initialize the "attributes"
	 * instance variable of DataSet.
	 *
	 * 
	 * @param reader is the DataReader instance to read data from.
	 */
	public ActualDataSet(DataReader reader) {
		// WRITE YOUR CODE HERE!
		Attribute[] attributes = new Attribute[reader.getNumberOfColumns()];
		this.attributes = attributes;

		this.matrix = reader.getData();
		this.dataSourceId = reader.getSourceId();
		this.numRows = reader.getNumberOfDataRows();

		for (int col = 0; col < matrix[0].length; col++) {
			String[] values = new String[reader.getNumberOfDataRows()];
			for (int row = 0; row < matrix.length; row++) {
				values[row] = this.matrix[row][col];
			}

			Attribute attr = new Attribute(
				reader.getAttributeNames()[col],
				col, 
				Util.isArrayNumeric(values) ? AttributeType.NUMERIC : AttributeType.NOMINAL,
				getUniqueAttributeValues(col)
			);
			attributes[col] = attr;
		}
	}

	/**
	 * Implementation of DataSet's abstract getValueAt method for an actual dataset
	 */
	public String getValueAt(int row, int attributeIndex) {
		// WRITE YOUR CODE HERE!
		return this.matrix[row][attributeIndex];
	}

	/**
	 * @return the sourceId of the dataset.
	 */
	public String getSourceId() {
		// WRITE YOUR CODE HERE!
		
		return this.dataSourceId ;
	}

	/**
	 * Returns a virtual dataset over this (actual) dataset
	 * 
	 * @return a virtual dataset spanning the entire data in this (actual) dataset
	 */
	public VirtualDataSet toVirtual() {
		int[] rows = new int[this.numRows];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = i;
		}
		return new VirtualDataSet(this, rows, this.attributes);
	}

	/**
	 * Override of toString() in DataSet
	 * 
	 * @return a string representation of this (actual) dataset.
	 */
	public String toString() {
		// WRITE YOUR CODE HERE!
		StringBuffer buffer = new StringBuffer();
		buffer.append("Actual dataset (")
				.append(this.getSourceId())
				.append(") with ")
				.append(this.getNumberOfAttributes())
				.append(" attribute(s) and ")
				.append(this.numRows)
				.append(" row(s)")
				.append(System.lineSeparator())
				.append(super.toString()); 
		return buffer.toString();

	}
}