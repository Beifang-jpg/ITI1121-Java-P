// You are allowed to use LinkedList or other Collection classes in A2 and A3

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class is used for representing a virtual dataset, that is, a dataset
 * that is a view over an actual dataset. A virtual dataset has no data matrix
 * of its own.
 *
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class VirtualDataSet extends DataSet {

    /**
     * reference to the source dataset (instance of ActualDataSet)
     */
    private ActualDataSet source;

    /**
     * array of integers mapping the rows of this virtual dataset to the rows of its
     * source (actual) dataset
     */
    private int[] map;

    /**
     * Constructor for VirtualDataSet. There are two important considerations here:
     * (1) Make sure that you keep COPIES of the "rows" and "attributes" passed as
     * formal parameters. Do not, for example, say this.map = rows. Instead, create
     * a copy of rows before assigning that copy to this.map. (2) Prune the value
     * sets of the attributes. Since a virtual dataset is only a subset of an actual
     * dataset, it is likely that some or all of its attributes may have smaller
     * value sets.
     *
     * @param source     is the source dataset (always an instance of ActualDataSet)
     * @param rows       is the set of rows from the source dataset that belong to
     *                   this virtual dataset
     * @param attributes is the set of attributes belonging to this virtual dataset.
     *                   IMPORTANT: you need to recalculate the unique value sets
     *                   for these attributes according to the rows. Why? Because
     *                   this virtual set is only a subset of the source dataset and
     *                   its attributes potentially have fewer unique values.
     */
    public VirtualDataSet(ActualDataSet source, int[] rows, Attribute[] attributes) {
        this.map = rows.clone();
        this.source = source;
        this.numRows = rows.length;
        this.numAttributes = attributes.length;

        this.attributes = new Attribute[attributes.length];
        for (int i = 0; i < this.attributes.length; i++) {
            Attribute sourceAttr = source.attributes[i];
            Attribute attr = new Attribute(sourceAttr.getName(), sourceAttr.getAbsoluteIndex(), sourceAttr.getType(), this.getUniqueAttributeValues(i));
            this.attributes[i] = attr;
        }
    }

    /**
     * String representation of the virtual dataset.
     */
    public String toString() {
        // WRITE YOUR CODE HERE!
        StringBuffer buffer = new StringBuffer();
        buffer.append("Virtual DataSet with ")
                .append(numAttributes)
                .append(" attribute(s) and ")
                .append(numRows)
                .append(" row(s)")
                .append(System.lineSeparator())
                //
                .append("- Dataset is a view over ")
                .append(this.source.getSourceId())
                .append(System.lineSeparator())
                //
                .append(" - Row indices in this dataset (w.r.t. its source dataset): ")
                .append(Arrays.toString(map))
                .append(System.lineSeparator())
                .append(super.toString());
        return buffer.toString();
    }

    /**
     * Implementation of DataSet's getValueAt abstract method for virtual datasets.
     * Hint: You need to call source.getValueAt(...). What you need to figure out is
     * with what parameter values that method needs to be called.
     */
    public String getValueAt(int row, int attributeIndex) {
        return source.getValueAt(this.map[row], attributeIndex);
    }

    /**
     * @return reference to source dataset
     */
    public ActualDataSet getSourceDataSet() {
        return this.source;
    }

    private abstract class Condition {
        protected String targetValue;

        public Condition(String targetValue) {
            this.targetValue = targetValue;
        }

        public abstract boolean Calc(String value);
    }

    private class NominalCondition extends Condition {
        public NominalCondition(String targetValue) {
            super(targetValue);
        }

        public boolean Calc(String value) {
            return value.equals(this.targetValue);
        }
    }

    private class NumericalLessThanCondition extends Condition {
        public NumericalLessThanCondition(String targetValue) {
            super(targetValue);
        }

        public boolean Calc(String value) {
            return Integer.parseInt(value) <= Integer.parseInt(this.targetValue);
        }
    }

    private class NumericalGreaterThanCondition extends Condition {
        public NumericalGreaterThanCondition(String targetValue) {
            super(targetValue);
        }

        public boolean Calc(String value) {
            return Integer.parseInt(value) > Integer.parseInt(this.targetValue);
        }
    }

    private <T extends Condition> VirtualDataSet getRowsByCondition(Class<T> condition, int attributeIndex, String target, boolean removeAttr) throws Exception {
        final LinkedList<Integer> map = new LinkedList<>();
        for (int j = 0; j < this.numRows; j++) {
            Condition c;
            try {
                c = condition.getDeclaredConstructor(new Class[]{VirtualDataSet.class, String.class}).newInstance(this, target);
            } catch (Exception e) {
                throw e;
            }
            if (c.Calc(this.getValueAt(j, attributeIndex))) {
                map.add(this.map[j]);
            }
        }
        int[] rows = new int[map.size()];
        for (int j = 0; j < rows.length; j++) {
            rows[j] = map.get(j);
        }
        Attribute[] newAttr = new Attribute[this.attributes.length - (removeAttr ? 1 : 0)];
        int counter = 0;
        for (int j = 0; j < this.attributes.length; j++) {
            if (j == attributeIndex && removeAttr) {
                continue;
            }
            newAttr[counter] = this.attributes[j].clone();
            counter++;
        }
        return new VirtualDataSet(this.source, rows, newAttr);
    }

    /**
     * This method splits the virtual dataset over a nominal attribute. This process
     * has been discussed and exemplified in detail in the assignment description.
     *
     * @param attributeIndex is the index of the nominal attribute over which we
     *                       want to split.
     * @return a set (array) of partitions resulting from the split. The partitions
     * will no longer contain the attribute over which we performed the
     * split.
     */
    public VirtualDataSet[] partitionByNominallAttribute(int attributeIndex) throws Exception {
        LinkedList<VirtualDataSet> result = new LinkedList<>();

        for (int i = 0; i < this.attributes[attributeIndex].getValues().length; i++) {
            String currentTarget = this.attributes[attributeIndex].getValues()[i];
            VirtualDataSet r = getRowsByCondition(NominalCondition.class, attributeIndex, currentTarget, true);
            result.add(r);
        }

        VirtualDataSet[] arr = new VirtualDataSet[result.size()];
        for (int j = 0; j < result.size(); j++) {
            arr[j] = result.get(j);
        }

        return arr;
    }

    /**
     * This method splits the virtual dataset over a given numeric attribute at a
     * specific value from the value set of that attribute. This process has been
     * discussed and exemplified in detail in the assignment description.
     *
     * @param attributeIndex is the index of the numeric attribute over which we
     *                       want to split.
     * @param valueIndex     is the index of the value (in the value set of the
     *                       attribute of interest) to use for splitting
     * @return a pair of partitions (VirtualDataSet array of length two) resulting
     * from the two-way split. Note that the partitions will retain the
     * attribute over which we perform the split. This is in contrast to
     * splitting over a nominal, where the split attribute disappears from
     * the partitions.
     */
    public VirtualDataSet[] partitionByNumericAttribute(int attributeIndex, int valueIndex) throws Exception {
        VirtualDataSet[] result = new VirtualDataSet[2];

        String target = this.getValueAt(valueIndex, attributeIndex);
        result[0] = getRowsByCondition(NumericalLessThanCondition.class, attributeIndex, target, false);
        result[1] = getRowsByCondition(NumericalGreaterThanCondition.class, attributeIndex, target, false);
        return result;
    }


    public static void main(String[] args) throws Exception {

        StudentInfo.display();

        System.out.println("============================================");
        System.out.println("THE WEATHER-NOMINAL DATASET:");
        System.out.println();

        ActualDataSet figure5Actual = new ActualDataSet(new CSVReader("datasets/weather-nominal.csv"));

        System.out.println(figure5Actual);

        VirtualDataSet figure5Virtual = figure5Actual.toVirtual();

        System.out.println("JAVA IMPLEMENTATION OF THE SPLIT IN FIGURE 5:");
        System.out.println();

        VirtualDataSet[] figure5Partitions = figure5Virtual
                .partitionByNominallAttribute(figure5Virtual.getAttributeIndex("outlook"));

        for (int i = 0; i < figure5Partitions.length; i++)
            System.out.println("Partition " + i + ": " + figure5Partitions[i]);

        System.out.println("============================================");
        System.out.println("THE WEATHER-NUMERIC DATASET:");
        System.out.println();

        ActualDataSet figure9Actual = new ActualDataSet(new CSVReader("datasets/weather-numeric.csv"));

        System.out.println(figure9Actual);

        VirtualDataSet figure9Virtual = figure9Actual.toVirtual();

        // Now let's figure out what is the index for humidity in figure9Virtual and
        // what is the index for "80" in the value set of humidity!

        int indexForHumidity = figure9Virtual.getAttributeIndex("humidity");

        Attribute humidity = figure9Virtual.getAttribute(indexForHumidity);

        String[] values = humidity.getValues();

        int indexFor80 = -1;

        for (int i = 0; i < values.length; i++) {
            if (values[i].equals("80")) {
                indexFor80 = i;
                break;
            }
        }

        if (indexFor80 == -1) {
            System.out.println("Houston, we have a problem!");
            return;
        }

        VirtualDataSet[] figure9Partitions = figure9Virtual.partitionByNumericAttribute(indexForHumidity, indexFor80);

        System.out.println("JAVA IMPLEMENTATION OF THE SPLIT IN FIGURE 9:");
        System.out.println();

        for (int i = 0; i < figure9Partitions.length; i++)
            System.out.println("Partition " + i + ": " + figure9Partitions[i]);

    }
}