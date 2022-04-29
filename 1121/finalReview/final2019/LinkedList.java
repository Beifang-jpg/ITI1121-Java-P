package tutorial.finalReview.final2019;

import tutorial.structure.List;

public class LinkedList<E> implements List<E> {
    private static class Node<T> {
        private T elem;
        private Node<T> previous, next;

        private Node(T elem, Node<T> previous, Node<T> next) {
            this.elem = elem;
            this.previous = previous;
            this.next = next;
        }
    }

    private Node<E> head;
    private int size;

    public LinkedList() {
        //create dummy node
        head = new Node<E>(null, null, null);
        head.previous = head;
        head.next = head;
        size = 0;
    }

    //O(1)
    @Override
    public void addFirst(E elem) {
        Node<E> newNode = new Node<E>(elem, head, head.next);
        head.next = newNode;
        newNode.next.previous = newNode;
        size++;
}

    //O(1)
    @Override
    public void addLast(E elem) {
        Node<E> newNode = new Node<E>(elem, head.previous, head);
        newNode.previous.next = newNode;
        head.previous = newNode;
        size++;
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
        size--;
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
        size--;
        return saved;
    }

    public void merge(LinkedList<E> other) {
        if (other != null && other.size != 0) {
            Node<E> thisLast, otherFirst, otherLast;
            thisLast = head.previous;
            otherFirst = other.head.next;
            otherLast = other.head.previous;
            otherFirst.previous = thisLast;
            thisLast.next = otherFirst;
            head.previous = otherLast;
            otherLast.next = head;
            other.head.next = other.head;
            other.head.previous = other.head;
            size += other.size;
            other.size = 0;
        }
    }
}
