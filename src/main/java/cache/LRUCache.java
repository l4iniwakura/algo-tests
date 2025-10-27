package cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
  LinkedHashMap based LRUCache.
  All operations O(1)
  Not thread safe
 */
public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    private final Map<K, V> cache;

    public LRUCache(int capacity) {
        this.cache = new LinkedHashMap<>(16, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
        this.capacity = capacity;
    }

    @Override
    public V put(K key, V value) {
        return cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public V remove(K key) {
        return cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<K, V> snapshot() {
        return new HashMap<>(cache);
    }
}
