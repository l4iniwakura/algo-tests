package cache.manual;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LRUCache<K, V> {

    private final Node head;
    private final Node tail;
    private final int capacity;
    private final V defaultValue;
    private final Map<K, Node> cache;
    private final AtomicInteger counter = new AtomicInteger(0);

    public LRUCache(int capacity, V defaultValue) {
        assert capacity > 0;

        cache = new ConcurrentHashMap<>(16, 0.75F, Runtime.getRuntime().availableProcessors());
        this.capacity = capacity;
        this.defaultValue = defaultValue;

        head = new Node(null, defaultValue);
        tail = new Node(null, defaultValue);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }

    public V put(K key, V value) {
        var node = cache.get(key);
        if (node != null) {
            deleteNode(node);
            node = new Node(key, value);
            addToHead(node);
            cache.put(key, node);
            return node.val;
        }

        node = new Node(key, value);
        cache.put(key, node);

        if (counter.incrementAndGet() > this.capacity) {
            remove(tail.prev.key);
        }

        return addToHead(node).val;
    }

    public V get(K key) {
        var node = cache.get(key);
        if (node == null) {
            return defaultValue;
        }
        return addToHead(deleteNode(node)).val;
    }

    public V remove(K key) {
        Node node = cache.remove(key);
        if (node == null) return defaultValue;
        deleteNode(node);
        counter.decrementAndGet();
        return node.val;
    }

    public int size() {
        return counter.get();
    }

    public V getFirst() {
        return head.next.val;
    }

    public V getLast() {
        return tail.prev.val;
    }

    private Node deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        return node;
    }

    private Node addToHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;

        return node;
    }

    private class Node {
        Node prev;
        Node next;
        K key;
        V val;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
}
