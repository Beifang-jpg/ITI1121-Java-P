/**
 * This class enables the construction of a decision tree
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */

public class DecisionTree {

	private static class Node<E> {
		E data;
		Node<E>[] children;

		Node(E data) {
			this.data = data;
		}
	}

	Node<VirtualDataSet> root;

	/**
	 * @param data is the training set (instance of ActualDataSet) over which a
	 *             decision tree is to be built
	 */
	public DecisionTree(ActualDataSet data) throws NullPointerException, ArrayIndexOutOfBoundsException{
		root = new Node<VirtualDataSet>(data.toVirtual());
		build(root);
	}

	/**
	 * The recursive tree building function
	 * 
	 * @param node is the tree node for which a (sub)tree is to be built
	 */
	@SuppressWarnings("unchecked")
	private void build(Node<VirtualDataSet> node) throws NullPointerException, ArrayIndexOutOfBoundsException{
		if (node.data == null || node.data == null) {
			throw new NullPointerException();
		}
		if (node.data.attributes.length == 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (node.data.getNumberOfAttributes() == 0 ){
			throw new IllegalArgumentException();
		}
		if (node.data.getNumberOfDatapoints() == 0){
			throw new IllegalArgumentException();
		}

		if (node.data.numRows == 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (
				node.data.numAttributes == 1 ||
				node.data.attributes[node.data.attributes.length - 1].getValues().length == 1 ||
				node.data.numAttributes == 2 && node.data.attributes[0].getValues().length == 1
				) {
					return;
		}

		GainInfoItem[] items = InformationGainCalculator.calculateAndSortInformationGains(node.data);
		VirtualDataSet[] partitions;
		if (items[0].getAttributeType() == AttributeType.NOMINAL) {
			partitions = node.data.partitionByNominallAttribute(node.data.getAttributeIndex(items[0].getAttributeName()));
		} else {
			int splitAtIndex = 0;
			String[] values = node.data.getUniqueAttributeValues(items[0].getAttributeName());
			for (int i = 0; i < values.length; i++) {
				if (values[i].equals(items[0].getSplitAt())) {
					splitAtIndex = i;
					break;
				}
			}
			partitions = node.data.partitionByNumericAttribute(node.data.getAttributeIndex(items[0].getAttributeName()),
					splitAtIndex);
		}

		Node<VirtualDataSet>[] children = new Node[partitions.length];
		node.children = children;
		for (int i = 0; i < children.length; i++) {
			children[i] = new Node<VirtualDataSet>(partitions[i]);
			build(children[i]);
		}
	}

	@Override
	public String toString() {
		return toString(root, 0);
	}

	/**
	 * The recursive toString function
	 * 
	 * @param node        is the tree node for which an if-else representation is to
	 *                    be derived
	 * @param indentDepth is the number of indenting spaces to be added to the
	 *                    representation
	 * @return an if-else representation of node
	 */
	private String toString(Node<VirtualDataSet> node, int indentDepth) {
		String indent = createIndent(indentDepth); 

		if (node.children != null) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < node.children.length; i++) {
				Node<VirtualDataSet> child = node.children[i];
				buffer.append(indent).append((i != 0 ? "else " : "") + "if (").append(child.data.getCondition()).append(") {\n");
				buffer.append(this.toString(child, indentDepth + 1));
				buffer.append(indent).append("}\n");
			}
			return buffer.toString();
		} else {
			return String.format(
					"%s%s = %s\n",
					indent,
					node.data.getAttribute(node.data.numAttributes - 1).getName(),
					node.data.getAttribute(node.data.numAttributes - 1).getValues()[0]
				);
		}
	}

	/**
	 * @param indentDepth is the depth of the indentation
	 * @return a string containing indentDepth spaces; the returned string (composed
	 *         of only spaces) will be used as a prefix by the recursive toString
	 *         method
	 */
	private static String createIndent(int indentDepth) {
		if (indentDepth <= 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < indentDepth; i++) {
			buffer.append(' ');
		}
		return buffer.toString();
	}

	public static void main(String[] args) throws Exception {
	
		StudentInfo.display();

		if (args == null || args.length == 0) {
			System.out.println("Expected a file name as argument!");
			System.out.println("Usage: java DecisionTree <file name>");
			return;
		}

		String strFilename = args[0];

		ActualDataSet data = new ActualDataSet(new CSVReader(strFilename));

		DecisionTree dtree = new DecisionTree(data);

		System.out.println(dtree);
	}
}