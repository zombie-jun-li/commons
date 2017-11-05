package toddler.common.cache;

import toddler.common.util.Maps;
import toddler.common.util.Value;

import java.util.Date;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by jun.li
 */
public class LocalCache implements Cache {
    private final Map<String, CacheHolder<Object>> caches = Maps.newConcurrentHashMap();


    @Override
    public <T> T get(String key, Class<T> type) {
        CacheHolder<Object> cacheHolder = caches.get(key);
        if (null == cacheHolder) {
            return null;
        }
        if (cacheHolder.getExpireTime() > new Date().getTime()) {
            return (T) cacheHolder.getObject();
        }
        expireCaches();
        return null;
    }

    @Override
    public boolean exists(String key) {
        return caches.containsKey(key);
    }

    @Override
    public <T> void put(String key, T value, Integer expireAfterMinutes) {
        long expireTime = new Date().getTime() + (long) 60 * expireAfterMinutes * 1000;
        CacheHolder<Object> cacheHolder = new CacheHolder<>(key, value, expireTime);
        caches.put(key, cacheHolder);
    }

    @Override
    public void evict(String key) {
        caches.remove(key);
    }

    @Override
    public void clear() {
        caches.clear();
    }

    private void expireCaches() {
        long now = new Date().getTime();
        Consumer<CacheHolder<Object>> consumer = cacheHolder -> Value.of(cacheHolder)
                .ifTrue(v -> v.getExpireTime() < now)
                .consume(v -> evict(v.getKey()));
        caches.entrySet().stream()
                .forEach(e -> consumer.accept(e.getValue()));

    }

    private static final class CacheHolder<T> {
        private final String key;

        private final T object;

        private final long expireTime;

        CacheHolder(String key, T object, long expireTime) {
            this.key = key;
            this.object = object;
            this.expireTime = expireTime;
        }

        public String getKey() {
            return key;
        }

        public T getObject() {
            return object;
        }

        public long getExpireTime() {
            return expireTime;
        }
    }

}
