import java.io.File;
import java.util.Scanner;

/**
 * This class provides an implementation of the DataReader interface for CSV
 * files
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class CSVReader implements DataReader {
	private String[][] data;
	private String[] attr;
	private int numCols;
	private int numRows;
	private String filename;

	/**
	 * Constructs a dataset by loading a CSV file
	 * 
	 * @param strFilename is the name of the file
	 */
	public CSVReader(String filePath) throws Exception {
		this.filename = filePath;
		Scanner scanner = new Scanner(new File(filePath));

		this.attr = this.seperateAttributes(scanner.nextLine());
		this.numCols = attr.length;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (!line.isBlank())
				this.numRows++;
		}
		scanner.close();
		scanner = new Scanner(new File(filePath));

		this.data = new String[this.numRows][this.numCols];

		int counter = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
		    if (counter == 0) {
		    	counter++;
		    	continue;
			}
			if (line.isBlank()) continue;
			this.data[counter - 1] = this.seperateAttributes(line);
			counter++;
		}
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
			if (processed[i] != null)
				c++;
		}

		String[] result = new String[c];
		c = 0;
		for (String s : processed) {
			if (s == null)
				break;
			result[c] = s;
			c++;
		}

		return result;
	}

	public String[] getAttributeNames() {
		// WRITE YOUR CODE HERE!
		
		return this.attr;
	}

	public String[][] getData() {
		return this.data;
	}

	public String getSourceId() {
		String[] splitted = this.filename.split("/");

		return splitted[splitted.length - 1];
	}

	public int getNumberOfColumns() {
		// WRITE YOUR CODE HERE!
		
		//Remove the following line when this method has been implemented
		return this.numCols;
	}

	public int getNumberOfDataRows() {
		// WRITE YOUR CODE HERE!
	  	
		//Remove the following line when this method has been implemented
		return this.numRows;
	}
}