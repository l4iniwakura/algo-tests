package cache.manual;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/*

    Manually written LRUCache with manual deque management

 */
public class LRUCache<K, V> implements Iterable<Map.Entry<K, V>> {

    private final Node<K, V> head;
    private final Node<K, V> tail;
    private final int capacity;
    private final V defaultValue;
    private final Map<K, Node<K, V>> cache;
    private final AtomicInteger counter = new AtomicInteger(0);

    private int modificationCount = 0;

    public LRUCache(int capacity, V defaultValue) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        cache = new ConcurrentHashMap<>(16, 0.75F, Runtime.getRuntime().availableProcessors());
        this.capacity = capacity;
        this.defaultValue = defaultValue;

        head = new Node<>(null, defaultValue);
        tail = new Node<>(null, defaultValue);
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
        node = new Node<>(key, value);
        cache.put(key, node);
        if (counter.get() >= this.capacity) {
            remove(tail.prev.key);
        } else {
            counter.incrementAndGet();
        }
        addToHead(node);
        return defaultValue;
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
        var node = cache.remove(key);
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

    private Node<K, V> deleteNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // Защита от утечек памяти
        node.prev = null;
        node.next = null;

        modificationCount++;

        return node;
    }

    private Node<K, V> addToHead(Node<K, V> node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;

        modificationCount++;

        return node;
    }

    private Node<K, V> moveToHead(Node<K, V> node) {
        deleteNode(node);
        addToHead(node);
        return node;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<>() {
            private Node<K, V> current = head.next;
            private final int currentModificationCount = modificationCount;

            @Override
            public boolean hasNext() {
                return current != null && !current.equals(tail);
            }

            @Override
            public Map.Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (currentModificationCount != modificationCount) {
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

    private static class Node<K, V> {
        Node<K, V> prev;
        Node<K, V> next;
        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
