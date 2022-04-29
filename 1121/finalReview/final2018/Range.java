package tutorial.finalReview.final2018;

public class Range implements Iterator {
    private int range;
    private int value;

    public Range(int range) {
        this.range = range;
        value = -1;
    }

    @Override
    public int next() {
        if (value >= range - 1) {
            throw new IllegalStateException();
        }
        value ++;
        return value;
    }

    @Override
    public boolean hasNext() {
        return value < range - 1;
    }

    public static void main(String[] args) {
        Iterator i;
        i = new Range(5);
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
