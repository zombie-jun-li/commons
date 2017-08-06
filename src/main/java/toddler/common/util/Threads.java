package toddler.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Created by jun.
 */
public final class Threads {

    private static final Logger LOGGER = LoggerFactory.getLogger(Threads.class);

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            LOGGER.error("Thread was interrupted!, ex={}", e);
        }
    }
}
