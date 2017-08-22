package toddler.common.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by jun.
 */
@FunctionalInterface
public interface Command<T> {
    T execute(Jedis jedis);
}
