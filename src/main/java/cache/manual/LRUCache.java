package cache.manual;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LRUCache<K, V> implements Iterable<Map.Entry<K, V>> {

    private final Node head;
    private final Node tail;
    private final int capacity;
    private final V defaultValue;
    private final Map<K, Node> cache;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final AtomicInteger modificationCount = new AtomicInteger(0);

    public LRUCache(int capacity, V defaultValue) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        cache = new ConcurrentHashMap<>(16, 0.75F, Runtime.getRuntime().availableProcessors());
        this.capacity = capacity;
        this.defaultValue = defaultValue;

        head = new Node(null, defaultValue);
        tail = new Node(null, defaultValue);
        head.next = tail;
        tail.prev = head;
    }

    public V put(K key, V value) {
        var node = cache.get(key);
        if (node != null) {
            var oldValue = node.value;
            node.value = value;
            moveToHead(node);
            return oldValue;
        }
        node = new Node(key, value);
        cache.put(key, node);
        if (counter.get() >= this.capacity) {
            remove(tail.prev.key);
        } else {
            counter.incrementAndGet();
        }
        addToHead(node);
        return defaultValue;
    }

    private Node moveToHead(Node node) {
        return addToHead(deleteNode(node));
    }

    public V get(K key) {
        var node = cache.get(key);
        if (node == null) {
            return defaultValue;
        }
        moveToHead(node);
        return node.value;
    }

    public V remove(K key) {
        Node node = cache.remove(key);
        if (node == null) {
            return defaultValue;
        }
        deleteNode(node);
        counter.decrementAndGet();
        return node.value;
    }

    public int size() {
        return counter.get();
    }

    public V getFirst() {
        return head.next.value;
    }

    public V getLast() {
        return tail.prev.value;
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        cache.clear();
        // Восстанавливаем начальное состояние списка
        head.next = tail;
        tail.prev = head;
        counter.set(0);
    }

    private Node deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // Защита от утечек памяти
        node.prev = null;
        node.next = null;

        modificationCount.incrementAndGet();

        return node;
    }

    private Node addToHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;

        modificationCount.incrementAndGet();

        return node;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<>() {
            private Node current = head.next;
            private int currentModificationCount = modificationCount.get();

            @Override
            public boolean hasNext() {
                return current != null && !current.equals(tail);
            }

            @Override
            public Map.Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (currentModificationCount != modificationCount.get()) {
                    throw new ConcurrentModificationException("Cache modified during iteration");
                }
                Map.Entry<K, V> entry = Map.entry(current.key, current.value);
                current = current.next;
                return entry;
            }
        };
    }

    @Override
    public String toString() {
        var sb = new StringBuilder(size() * 6 - 1 + 2);
        sb.append('[');
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            var entry = it.next();
            sb.append('{')
                    .append(entry.getKey())
                    .append(':')
                    .append(entry.getValue())
                    .append('}');
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.append(']').toString();
    }

    private class Node {
        Node prev;
        Node next;
        final K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
