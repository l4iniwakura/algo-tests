import cache.manual.LRUCache;

public class Main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2, -1);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }
}
