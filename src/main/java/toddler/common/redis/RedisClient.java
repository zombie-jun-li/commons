package toddler.common.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Optional;


/**
 * Created by jun.
 */
public class RedisClient {
    private final JedisPool jedisPool;

    public RedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public <T> T execute(Command<T> command) {
        try (Jedis jedis = jedisPool.getResource()) {
            return command.execute(jedis);
        }
    }

    public String get(String key) {
        return execute(jedis -> jedis.get(key));
    }

    public void set(String key, String value) {
        execute(jedis -> jedis.set(key, value));
    }

    public void del(String key) {
        execute(jedis -> jedis.del(key));
    }

    public void incr(String key) {
        execute(jedis -> jedis.incr(key));
    }

    public void decr(String key) {
        execute(jedis -> jedis.decr(key));
    }

    public void close() {
        Optional.ofNullable(jedisPool).ifPresent(pool -> pool.close());
    }
}
