package tutorial.finalReview.final2018;

import java.util.Arrays;

public class DynamicArrayList<E> {
    private E[] array;
    private int size;
    private int first, last;
    private int capacity = 100;
    @SuppressWarnings("unchecked")
    public DynamicArrayList() {
        array = (E[]) new Object[capacity];
        first = last = 0;
        size = 0;
    }
    @SuppressWarnings("unchecked")
    public DynamicArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Minimum capacity is 1");
        }
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
        first = last = 0;
        size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == capacity;
    }

    private void ensureSpace() {
        if (isFull()) {
            E[] newArray = (E[]) new Object[capacity * 2];
            for (int i = 0; i < size; i ++) {
                newArray[i] = array[(first + i) % capacity];
            }
            first = 0;
            last = capacity - 1;
            capacity = capacity * 2;
            array = newArray;
        }
    }

    public void addFirst(E newElement) {
        if (newElement == null) {
            throw new IllegalArgumentException();
        }
        ensureSpace();
        if (isEmpty()) {
            first = last = 0;
        } else {
            first = ((first - 1) + capacity) % capacity;
        }
        array[first] = newElement;
        size = size + 1;
    }

    @Override
    public String toString() {
        return "DynamicArrayList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", first=" + first +
                ", last=" + last +
                ", capacity=" + capacity +
                '}';
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E result = array[first];
        array[first] = null;
        size = size - 1;
        if (size == 0) {
            first = last = 0;
        } else {
            first = (first + 1) % capacity;
        }
        return result;
    }

    public void add(E newElement, int index) {
        if (newElement == null) {
            throw new IllegalArgumentException();
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            addFirst(newElement);
        } else {
            ensureSpace();
            int currentIndexLocation = (first + index) % capacity;
            int i = (last + 1) % capacity;
            while (i != currentIndexLocation) {
                array[i] = array[((i - 1) + capacity) % capacity];
                i = ((i - 1) + capacity) % capacity;
            }
            array[currentIndexLocation] = newElement;
            size ++;
            last = (last + 1) % capacity;
        }
    }

    public E remove(int index) {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        E result = array[(first + index) % capacity];
        if (size == 1) {
            result = removeFirst();
        } else {
            int currentIndexLocation = (first + index) % capacity;
            int i = currentIndexLocation;
            while (i != last) {
                array[i] = array[(i + 1) % capacity];
                i = (i + 1) % capacity;
            }
            size --;
            array[currentIndexLocation] = null;
            last = ((last - 1) + capacity) % capacity;
        }

        return result;
    }

    public static void main(String[] args) {
        DynamicArrayList<Integer> list = new DynamicArrayList<>(3);
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        System.out.println(list);
        list.addFirst(4);
        System.out.println(list);
    }
}
