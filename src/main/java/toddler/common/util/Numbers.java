package toddler.common.util;


import toddler.common.primitives.Primitives;

import java.util.Optional;

/**
 * Created by jun.
 */
public abstract class Numbers {
    public static <T extends Number> T nullToZero(T t, Class<T> type) {
        return Optional.ofNullable(t).orElse(Primitives.defaultValue(type));
    }
}
