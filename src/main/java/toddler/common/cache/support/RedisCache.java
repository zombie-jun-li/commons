package toddler.common.cache.support;

/**
 * Created by jun.li
 */
public class RedisCache implements Cache {
    @Override
    public <T> T get(String key, Class<T> type) {
        return null;
    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public <T> void put(String key, T value, Integer expireAfterMinutes) {

    }

    @Override
    public void evict(String key) {

    }

    @Override
    public void clear() {

    }
}
