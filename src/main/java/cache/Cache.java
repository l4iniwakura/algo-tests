package cache;

public interface Cache<K, V> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    int size();
}
