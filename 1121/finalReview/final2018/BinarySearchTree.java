package tutorial.finalReview.final2018;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private static class Node<T> {
        T value;
        Node<T> left, right;

        private Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private Node<E> root;
    private int size;

    public BinarySearchTree(List<E> list) {
        if(list == null || list.size() == 0) {
            return;
        }
        root = buildBalanced(0, list.size() - 1, list);
    }

    public int getSize() {
        return size;
    }

    public boolean add(E value) {
        if (root == null) {
            root = new Node<>(value, null, null);
            return true;
        }
        return add(value, root);
    }

    private boolean add(E value, Node<E> current) {
        if (value.compareTo(current.value) < 0) {
            if (current.left == null) {
                Node<E> node = new Node<>(value, null, null);
                current.left = node;
                size ++;
                return true;
            } else {
                return add(value, current.left);
            }
        } else if (value.compareTo(current.value) > 0) {
            if (current.right == null) {
                Node<E> node = new Node<>(value, null, null);
                current.right = node;
                size ++;
                return true;
            } else {
                return add(value, current.right);
            }
        } else {
            return false;
        }
    }

    public boolean contains(E value) {
        return contains(value, root);
    }

    private boolean contains(E value, Node<E> current) {
        if (current == null) {
            return false;
        } else {
            if (value.compareTo(current.value) < 0) {
                return contains(value, current.left);
            } else if (value.compareTo(current.value) > 0) {
                return contains(value, current.right);
            } else {
                return true;
            }
        }
    }

    private void visit(Node<E> current) {
        if (current != null) {
            System.out.print(current.value +", ");
        }
    }

    private void preOrder(Node<E> current) {
        visit(current);
        if (current.left != null) {
            preOrder(current.left);
        }
        if (current.right != null) {
            preOrder(current.right);
        }
    }

    private void inOrder(Node<E> current) {
        if (current.left != null) {
            inOrder(current.left);
        }
        visit(current);
        if (current.right != null) {
            inOrder(current.right);
        }
    }

    private void postOrder(Node<E> current) {
        if (current.left != null) {
            postOrder(current.left);
        }
        if (current.right != null) {
            postOrder(current.right);
        }
        visit(current);
    }

    public void preOrder() {
        preOrder(root);
    }
    public void inOrder() {
        inOrder(root);
    }
    public void postOrder() {
        postOrder(root);
    }

    public E getMin() {
        if (root == null) {
            throw  new IllegalStateException();
        }
        return getMin(root);
    }

    private E getMin(Node<E> current) {
        if (current.left == null) {
            return current.value;
        }
        //recursive
        return getMin(current.left);
    }

    public E getMax() {
        if (root == null) {
            throw  new IllegalStateException();
        }
        return getMax(root);
    }

    private E getMax(Node<E> current) {
        if (current.right == null) {
            return current.value;
        }
        //recursive
        return getMax(current.right);
    }

    public void buildSortedList(List<E> list) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        }
        if (list.size() != 0) {
            throw new IllegalArgumentException("List must be empty");
        }
        buildSortedList(list, root);
    }

    private void buildSortedList(List<E> list, Node<E> current) {
        if (current.left != null) {
            buildSortedList(list, current.left);
        }
        list.add(current.value);//O(1)
        if (current.right != null) {
            buildSortedList(list, current.right);
        }
    }

    private Node<E> buildBalanced(int from, int to, List<E> list) {
        if (from > to) {
            return null;
        } else {
            int mid = (from + to) / 2;
            Node<E> node = new Node<>(list.get(mid), null, null);
            node.left = buildBalanced(from, mid - 1, list);
            node.right = buildBalanced(mid + 1, to, list);
            return node;
        }
    }

}
