package toddler.common.util;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * Created by jun.
 */
public final class Retry<T> {

    private int maxAttempts = 3;

    private Duration retryInterval = Duration.ofSeconds(3);

    private Predicate<Exception> predicate;

    public <T> T execute(Callable<T> callable) throws Exception {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                attempts++;
                return callable.call();
            } catch (Exception e) {
                if (attempts >= maxAttempts) throw e;
                if (null != predicate && !predicate.test(e)) throw e;
                Threads.sleep(retryInterval);
            }
        }
        return null;
    }


    public Retry<T> maxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        return this;
    }

    public Retry<T> retryInterval(Duration retryInterval) {
        this.retryInterval = retryInterval;
        return this;
    }

    public Retry<T> retryOn(Predicate<Exception> predicate) {
        this.predicate = predicate;
        return this;
    }
}
