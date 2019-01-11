package toddler.common.util.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * created by jun.
 **/
public class LocalLocks extends Locks {
    private final Map<Object, LocalLockHolder> lockMap = new ConcurrentHashMap<>();

    @Override
    void lock(Object object) {
        lockMap.putIfAbsent(object, new LocalLockHolder());
        lockMap.get(object).lock();
    }

    @Override
    void unlock(Object object) {
        LocalLockHolder lockHolder = lockMap.get(object);
        // 1. unlock first
        lockHolder.unlock();
        // 2. remove lockHolder
        if (lockHolder.counter.intValue() == 0) {
            lockMap.remove(object);
        }
    }

    private static class LocalLockHolder {
        private final AtomicInteger counter = new AtomicInteger(0);

        private final Lock localLock = new ReentrantLock();

        public void lock() {
            counter.incrementAndGet();
            localLock.lock();
        }

        public void unlock() {
            counter.decrementAndGet();
            localLock.unlock();
        }
    }
}
