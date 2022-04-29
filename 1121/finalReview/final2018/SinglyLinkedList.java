package tutorial.finalReview.final2018;

import tutorial.structure.List;

public class SinglyLinkedList<E> implements List<E> {
    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
    private Node<E> head;
    private int size;

    @Override
    public void addFirst(E elem) {

    }

    @Override
    public void addLast(E elem) {
        if (head == null) {
            head = new Node<>(elem, null);
        } else {
            Node<E> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node<>(elem, null);
        }
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    public int findAndReplace(E target, E replacement) {
        if (target == null || replacement == null) {
            throw new IllegalArgumentException();
        }
        return findAndReplace(head, target, replacement);
    }

    private int findAndReplace(Node<E> current, E target, E replacement) {
        int number;
        if (current == null) {
            number = 0;
        } else {
            number = findAndReplace(current.next, target, replacement);
            if (current.value.equals(target)) {
                number++;
                current.value = replacement;
            }
        }
        return number;
    }
}
