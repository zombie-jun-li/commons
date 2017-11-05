package toddler.common.cache;

/**
 * Created by jun.li
 */
public interface Cache {

    default void putIfAbsent(String key, Object value, Integer expireAfterMinutes) {
        if (!exists(key)) {
            put(key, value, expireAfterMinutes);
        }
    }

    <T> T get(String key, Class<T> type);

    boolean exists(String key);


    <T> void put(String key, T value, Integer expireAfterMinutes);

    void evict(String key);

    void clear();


}
