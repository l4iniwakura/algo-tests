package cache.manual;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LRUCacheTest {

    private final static Integer DEFAULT_VALUE = -1;

    private LRUCache<Integer, Integer> lruCache;

    @Test
    void putShouldReturnInsertedValuesCorrectly() {
        lruCache = new LRUCache(2, DEFAULT_VALUE);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        assertEquals(2, lruCache.get(2));
        assertEquals(1, lruCache.get(1));
    }

    @Test
    void cacheEvictionShouldWorkCorrectly() {
        lruCache = new LRUCache<>(2, DEFAULT_VALUE);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        assertEquals(DEFAULT_VALUE, lruCache.get(1));
        assertEquals(2, lruCache.get(2));
        assertEquals(3, lruCache.get(3));
    }

    @Test
    void repeatedPutShouldReplaceValueToNew() {
        lruCache = new LRUCache<>(2, DEFAULT_VALUE);
        lruCache.put(1, 1);
        lruCache.put(1, 2);
        lruCache.put(2, 2);
        assertEquals(2, lruCache.get(1));
        assertEquals(2, lruCache.get(2));
    }

    @Test
    void get() {

    }

    @Test
    void remove() {
    }

    @Test
    void sizeShouldReturnZero_whenCallWithoutCalledPutMethod() {
        lruCache = new LRUCache<>(1, DEFAULT_VALUE);
        assertEquals(0, lruCache.size());
    }

    @Test
    void sizeShouldReturnCorrectNumberOfElementsWhenInvoked() {
        lruCache = new LRUCache<>(10, DEFAULT_VALUE);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(3, 3);
        lruCache.put(2, 2);
        assertEquals(3, lruCache.size());
    }

    @Test
    void getFirst_shouldReturnDefaultValue_whenCacheJustCreated() {
        var defaultVal = -1;
        lruCache = new LRUCache<>(1, defaultVal);
        assertEquals(defaultVal, lruCache.getFirst());
    }

    @Test
    void getLast_shouldReturnDefaultValue_whenCacheJustCreated() {
        var defaultVal = -1;
        lruCache = new LRUCache<>(1, defaultVal);
        assertEquals(defaultVal, lruCache.getLast());
    }
}