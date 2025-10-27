package cache.manual;

public class DoublyLinkedList<V> {
    private final Node head;
    private final Node tail;

    public DoublyLinkedList() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
    }

    public Node deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;

        return node;
    }

    public Node addToHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;

        return node;
    }

    public V getFirst() {
        return head.next.value;
    }

    public V getLast() {
        return tail.prev.value;
    }

    public void clear() {
        head.next = tail;
        tail.prev = head;
    }

    public class Node {
        Node prev;
        Node next;
        V value;

        public Node(V value) {
            this.value = value;
        }
    }
}
