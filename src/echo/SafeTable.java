package echo;
import java.util.HashMap;

public class SafeTable<K, V> {
    private HashMap<K, V> table = new HashMap<>();

    public synchronized void put(K key, V value) {
        table.put(key, value);
    }

    public synchronized V get(K key) {
        return table.get(key);
    }

    public synchronized boolean containsKey(K key) {
        return table.containsKey(key);
    }

    public synchronized boolean isEmpty() {
        return table.isEmpty();
    }

    public synchronized int size() {
        return table.size();
    }

    public synchronized V remove(K key){
        return table.remove(key);
    }

    public synchronized void clear(){
        table.clear();
    }

}
