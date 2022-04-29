import java.util.NoSuchElementException;

public class OrderedList implements OrderedStructure {

    // Implementation of the doubly linked nodes (nested-class)

    private static class Node {

        private Comparable value;
        private Node previous;
        private Node next;

        private Node ( Comparable value, Node previous, Node next ) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    // Instance variables

    private Node head;
    private Node tail;

    // Representation of the empty list.

    public OrderedList() {
        // Your code here.
        head = new Node(null,null,null);

        tail = new Node(null,null,null);
        head.previous = null;
        head.next = tail;
        tail.previous = head;
        tail.next = null;

    }

    // Calculates the size of the list

    public int size() {
        // Remove line below and add your implementation.
        Node check = head;
        int n = 0;
        if (check.next == tail){
            return 0;
        }else{
            Node p = head.next;
            while(p !=tail){
                p = p.next;
                n +=1;
            }
        }
        return n;
    }


    public Object get( int pos ) {
        // Remove line below and add your implementation.
        Node find = head.next;
        if (pos < 0 || pos > size()) {
            throw new IndexOutOfBoundsException();
        }else{
            for (int i = 0; i < pos ; i++){
                find = find.next;
            }
            return find.value;
        }


    }

    // Adding an element while preserving the order

    public boolean add( Comparable o ) {
        // Remove line below and add your implementation.
        if(o == null){
            throw new IllegalArgumentException();
        }
        if(head.next == tail){
             Node temp = new Node(o,head,tail);
             tail.previous = temp;
             head.next = temp;

        }else{
            Node p = head.next;
            while (p.next != tail && p.value.compareTo(o) < 0){
                p = p.next;
            }


            
            if (p == tail) {
                Node temp = new Node(o, head, tail);
                tail.previous = temp;
                head.next = temp;

            }

            else{
                if (p.value.compareTo(o) < 0){
                    Node temp = new Node(o,p,p.next);
                    p.next = temp;
                    p.next.previous = temp;
                }else{
                    Node temp = new Node(o,p.previous,p);

                    p.previous.next = temp;
                    p.previous = temp;
                }


            }
        }

        return true;
    }

    //Removes one item from the position pos.

    public void remove( int pos ) {
        // Remove line below and add your implementation.

        if (pos < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node p =head.next;

        for (int i=0; i< pos;i++){
            if (p.next == head){
                throw new IndexOutOfBoundsException();
            }
            else{
                p = p.next;
            }
        }

        Node del = p;
        p = p.previous;

        Node q = del.next;
        p.next = q;
        q.previous = p;


        del = null;
    }

    // Knowing that both lists store their elements in increasing
    // order, both lists can be traversed simultaneously.

    public void merge( OrderedList other ) {
        // Remove line below and add your implementation.

    Node OtherC = other.head;
    while(OtherC.next != other.head);
        OtherC = OtherC.next;

        Node C = head;

        while(C.next != head){
            C = C.next;
            if (OtherC.value.compareTo(C.value)<0){
                Node D = new Node(OtherC.value , C.previous, C);
                D.previous.next = D;
                C.previous = D;
                break;
            }
        }

    }
}