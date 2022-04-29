import java.lang.reflect.Array;

/**
 * This class enables the calculation and sorting of information gain values
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class InformationGainCalculator {

	/**
	 * @param dataset is the dataset whose attributes we want to analyze and sort
	 *                according to information gain
	 * @return an array of GainInfoItem instances sorted in descending order of gain
	 *         value
	 */
	public static GainInfoItem[] calculateAndSortInformationGains(VirtualDataSet dataset) {
		int datasetValCount = dataset.getNumberOfDatapoints();
		LinkedList<GainInfoItem> list = new LinkedList<>(); 
		for (int i = 0; i < dataset.attributes.length - 1; i++) {
			Attribute attr = dataset.attributes[i];
			DataSet[] p;
			if (attr.getType() == AttributeType.NOMINAL) {
				p = dataset.partitionByNominallAttribute(i);
				list.append(calculateGainForAttr(p, attr, null));
			} else {
				double maxGain = 0;
				GainInfoItem max = null;
				for (int j = 0; j < dataset.getUniqueAttributeValues(attr.getName()).length; j++) {
					String v = dataset.getUniqueAttributeValues(attr.getName())[j];
					p = dataset.partitionByNumericAttribute(i, j);
					GainInfoItem gain = calculateGainForAttr(p, attr, v);
					if (gain.getGainValue() > maxGain) {
						maxGain = gain.getGainValue();
						max = gain;
					}
				}
				list.append(max);
			}

		}

		GainInfoItem[] arr = list.toArray(GainInfoItem.class);

		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (arr[i].getGainValue() < arr[j].getGainValue()) {
					GainInfoItem tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}

		return arr;
	}

	private static class LinkedList<T> {
		public T val;
		public LinkedList<T> prev;
		public LinkedList<T> next;

		public LinkedList(T val) {
			this.val = val;
		}

		public LinkedList() {

		}

		public void append(T val) {
			if (this.prev == null && this.val == null) {
				this.val = val;
				return;
			}
			LinkedList<T> current = this;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new LinkedList<T>(val);
		}

		public void remove() {
			this.prev.next = this.next;
			this.next.prev = this.prev;
		}

		public T[] toArray(Class<T> c) {
			int count = 1;
			LinkedList<T> current = this;
			LinkedList<T> first = this;
			while (current.prev != null) {
				current = current.prev;
			}
			first = current;
			while (current.next != null) {
				count++;
				current = current.next;
			}
			T[] arr = (T[]) Array.newInstance(c, count);
			int i = 0;
			current = first;
			while (current != null) {
				arr[i] = current.val;
				current = current.next;
				i++;
			}
			return arr;
		}
	}

	private static GainInfoItem calculateGainForAttr(DataSet[] data, Attribute attr, String splitAt) {
		double gain = calculateGain(data);
		return new GainInfoItem(attr.getName(), attr.getType(), gain, splitAt);
	}

	private static double calculateGain(DataSet[] p) {
		return EntropyEvaluator.evaluate(p);
	}

	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		if (args == null || args.length == 0) {
			System.out.println("Expected a file name as argument!");
			System.out.println("Usage: java InformationGainCalculator <file name>");
			return;
		}

		String strFilename = args[0];

		ActualDataSet actual = new ActualDataSet(new CSVReader(strFilename));

		// System.out.println(actual);

		VirtualDataSet virtual = actual.toVirtual();

		// System.out.println(virtual);

		GainInfoItem[] items = calculateAndSortInformationGains(virtual);

		// Print out the output
		System.out.println(
				" *** items represent (attribute name, information gain) in descending order of gain value ***");
		System.out.println();

		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}
}
