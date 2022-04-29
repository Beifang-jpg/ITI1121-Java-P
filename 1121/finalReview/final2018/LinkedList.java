package tutorial.finalReview.final2018;

import tutorial.structure.List;

public class LinkedList<E> implements List<E> {
    private static class Node<T> {
        private T elem;
        private Node<T> previous;
        private Node<T> next;
        private Node(T value, Node<T> prev, Node<T> next) {
            this.elem = value;
            this.previous = prev;
            this.next = next;
        }
    }
    private Node<E> head;
    private int size;

    public LinkedList() {
        //create dummy node
        head = new Node<>(null, null, null);
        head.previous = head;
        head.next = head;
    }

    //O(1)
    @Override
    public void addFirst(E elem) {
        Node<E> newNode = new Node<E>(elem, head, head.next);
        head.next = newNode;
        newNode.next.previous = newNode;
        size ++;
    }

    //O(1)
    @Override
    public void addLast(E elem) {
        Node<E> newNode = new Node<E>(elem, head.previous, head);
        newNode.previous.next = newNode;
        head.previous = newNode;
        size ++;
    }

    //O(1)
    @Override
    public E removeFirst() {
        if (head.next == head) {
            throw new IllegalStateException();
        }
        Node<E> first = head.next;
        head.next = first.next;
        first.next.previous = head;
        first.previous = null;
        first.next = null;
        E saved = first.elem;
        first.elem = null;
        size --;
        return saved;
    }

    //O(1)
    @Override
    public E removeLast() {
        if (head.next == head) {
            throw new IllegalStateException();
        }
        Node<E> last = head.previous;
        last.previous.next = head;
        head.previous = last.previous;
        last.previous = null;
        last.next = null;
        E saved = last.elem;
        last.elem = null;
        size --;
        return saved;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<E> current = head;
        while (current.next != head) {
            current = current.next;
            builder.append(current.elem);
            if (current.next != head) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public void swapFirst() {
        if (size < 2) {
            throw new IllegalStateException();
        }
        Node<E> first = head.next;
        Node<E> second = first.next;
        second.previous = head;
        head.next = second;
        first.previous = second;
        first.next = second.next;
        second.next.previous = first;
        second.next = first;
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("A");
        list.addLast("B");
        System.out.println(list);
        list.swapFirst();
        System.out.println(list);
        list.addLast("D");
        System.out.println(list);
    }
}
