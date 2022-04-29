import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * The class enables loading a dataset from a file (CSV format) and deriving
 * some important characteristics of the data
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class DataSet {

	/**
	 * The delimiter that separates attribute names and attribute values
	 */
	private static final char DELIMITER = ',';

	/**
	 * Character allowing escape sequences containing the delimiter
	 */
	private static final char QUOTE_MARK = '\'';

	/**
	 * Instance variable for storing the number of attributes (columns)
	 */
	private int numColumns;

	/**
	 * Instance variable for storing the number of datapoints (data rows)
	 */
	private int numRows;

	/**
	 * Instance variable for storing attribute names
	 */
	private String[] attributeNames;

	/**
	 * Instance variable for storing datapoints
	 */
	private String[][] matrix;

	/**
	 * Constructs a dataset by loading a CSV file
	 * 
	 * @param strFilename is the name of the file
	 */
	public DataSet(String strFilename) throws Exception {
		this.instantiateFromFile(strFilename);
	}

	/**
	 * Returns the name of the attribute at a given column index
	 * 
	 * @param column is the column index
	 * @return attribute name at index (null if the index is out of range)
	 */
	public String getAttributeName(int column) {
		// Note: Remember to handle out-of-range values!
		if (column > this.numColumns - 1) {
			return null;
		} else {
			return this.attributeNames[column];
		}
	}

	/**
	 * Returns the value of a given column for a given row (datapoint)
	 * 
	 * @param row    is the row (datapoint) index
	 * @param column is the column index
	 * @return the value of the attribute at column for the datapoint at row (null
	 *         if either row or column are out of range)
	 */
	public String getAttributeValue(int row, int column) {

		if (row > this.numRows - 1 || column > this.numColumns - 1) {
			return null;
		}

		return this.matrix[row][column];

	}

	/**
	 * Returns the number of attributes
	 * 
	 * @return number of attributes
	 */
	public int getNumberOfAttributes() {
		return numColumns;
	}

	/**
	 * Returns the number of datapoints
	 * 
	 * @return number of datapoints
	 */
	public int getNumberOfDatapoints() {
		return numRows;
	}

	/**
	 * Returns a reference to an array containing the unique values that an
	 * attribute can assume in the dataset
	 * 
	 * @param attributeName is the name of the attribute whose unique values must be
	 *                      returned
	 * @return String[] reference to the unique values of the the attribute with the
	 *         given name
	 */
	public String[] getUniqueAttributeValues(String attributeName) {
		int i;
		for (i = 0; i < this.attributeNames.length; i++) {
			if (this.attributeNames[i].equals(attributeName)) {
				break;
			}
		}

		if (!this.attributeNames[i].equals(attributeName))
			return null;

		return this.getUniqueAttributeValues(i);

	}

	/**
	 * Returns a reference to an array containing the unique values that the
	 * attribute at a certain column can assume in the dataset
	 * 
	 * @param column is the index (staring from zero) for the attribute whose unique
	 *               values must be returned
	 * @return String[] reference to the unique values of the attribute at the given
	 *         column
	 */
	private String[] getUniqueAttributeValues(int column) {

		String[] found = new String[this.numRows];
		int c = 0;
		boolean add = true;
		for (int i = 0; i < this.numRows; i++) {
			add = true;
			for (int j = 0; j < found.length; j++) {
				if (this.matrix[i][column].equals(found[j])) {
					add = false;
					break;
				}
			}
			if (add){
				found[c] = this.matrix[i][column];
				c++;
			}
		}

		String[] result = new String[c];
		for (int i = 0; i < c; i++) {
			result[i] = found[c - 1];
		}

		return result;
	}

	/**
	 * Returns in the form of an explanatory string some important characteristics
	 * of the dataset. These characteristics are: the number of attributes, the
	 * number of datapoints and the unique values that each attribute can assume
	 * 
	 * @return String containing the characteristics (metadata)
	 */
	public String metadataToString() {

		// Hint: You can combine multiple lines by appending
		// a (platform-dependent) separator to the end of each line.
		// To obtain the (platform-dependent) separator, you can use
		// the following command.
		String separator = System.getProperty("line.separator");

		// WRITE YOUR CODE HERE!




		// Hint: You need to call getUniqueAttributeValues() for
		// each attribute (via either attribute name or attribute column) and
		// then concatenate the string representations of the arrays returned by
		// getUniqueAttributeValues(). To get the string representations for
		// these arrays, you can use the methods provided in the Util class.
		// For nominal attributes use: Util.nominalArrayToString()
		// For numeric attributes use: Util.numericArrayToString()

		// Remove the following null return after you implement this method



		return "Number of attributes: " + numColumns + separator + "Number of datapoints: " + numRows ;
	}

	/**
	 * <b>main</b> of the application. The method first reads from the standard
	 * input the name of the CSV file to process. Next, it creates an instance of
	 * DataSet. Finally, it prints to the standard output the metadata of the
	 * instance of the DataSet just created.
	 * 
	 * @param args command lines parameters (not used in the body of the method)
	 */
	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		System.out.print("Please enter the name of the CSV file to read: ");

		Scanner scanner = new Scanner(System.in);

		String strFilename = scanner.nextLine();

		DataSet dataset = new DataSet(strFilename);

		dataset.getUniqueAttributeValues("personal_status");

		System.out.print(dataset.metadataToString());

	}

	private String[] seperateAttributes(String line) {

		String[] splitted = line.trim().split(",");
		String[] processed = new String[splitted.length];
		Boolean isQuoted = false;

		int c = 0;
		for (String s : splitted) {
			if (isQuoted) {
				processed[c] += s.replaceAll("'\\s*$", "");
				if (s.trim().endsWith("'")) {
					isQuoted = false;
					c++;
					continue;
				}
			}
			if (s.trim().matches("'(.)*'")) {
				processed[c] = s.trim().replaceAll("^\\s'", "").replaceAll("'\\s*", "");
				c++;
				continue;
			}
			if (s.trim().startsWith("'")) {
				isQuoted = true;
				processed[c] = s.replaceAll("^\\s*'", "") + ",";
			} else {
				processed[c] = s.trim();
				c++;
			}
		}

		c = 0;
		for (int i = 0; i < processed.length; i++) {
			if (processed[i] != null) c++;
		}

		String[] result = new String[c];
		c = 0;
		for (String s : processed) {
			if (s == null) break;
			result[c] = s;
			c++;
		}

		return result;
	}

	/**
	 * This method should set the numColumns and numRows instance variables The
	 * method is incomplete; you need to complete it.
	 * 
	 * @param strFilename is the name of the dataset file
	 */
	private void calculateDimensions(String strFilename) throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader(new File(strFilename)));

		String l1 = reader.readLine();
		this.numColumns = this.seperateAttributes(l1).length;

		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			} else if (!line.equals("")){
				this.numRows++;
			} else {
				continue;
			}
		}

	}

	/**
	 * This method should load the attribute names into the attributeNames instance
	 * variable and load the datapoints into the matrix instance variable. The
	 * method is incomplete; you need to complete it.
	 * 
	 * @param strFilename is the name of the file to read
	 */
	private void instantiateFromFile(String strFilename) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File(strFilename)));

		this.attributeNames = new String[this.numColumns];
		this.attributeNames = seperateAttributes(reader.readLine());
		reader.close();

		this.calculateDimensions(strFilename);

		this.matrix = new String[this.numRows][this.numColumns];

		reader = new BufferedReader(new FileReader(new File(strFilename)));
		reader.readLine();
		// move to the second line

		int i = 0;
		while (true) {

			String line = reader.readLine();
			if (line == null) {
				break;
			}
			if (line.equals("")) {
				continue;
			}
			String[] attr = this.seperateAttributes(line);
			for (int j = 0; j < this.numColumns; j++) {
				this.matrix[i][j] = attr.length <= j ? "" : attr[j];
			}
			i++;

		}
		reader.close();

	}
}