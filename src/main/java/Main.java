import cache.manual.LRUCache;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2, -1);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache);
        for (Map.Entry<Integer, Integer> entry : cache) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
