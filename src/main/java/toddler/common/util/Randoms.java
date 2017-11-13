package toddler.common.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jun.li
 */
public final class Randoms {
    private static final Random RANDOM = ThreadLocalRandom.current();

    private Randoms() {

    }

    public static int nextInt(int origin, int bound) {
        Double value = RANDOM.nextDouble() * (bound - origin);
        return value.intValue() + origin;
    }
}
