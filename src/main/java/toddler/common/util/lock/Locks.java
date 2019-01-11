package toddler.common.util.lock;

/**
 * created by jun.
 **/
public abstract class Locks {

    abstract void lock(Object object);

    abstract void unlock(Object object);

    public void run(Object object, Runnable runnable) {
        try {
            lock(object);
            runnable.run();
        } finally {
            unlock(object);
        }
    }
}
