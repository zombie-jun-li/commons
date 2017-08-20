package toddler.common.util;

import java.util.Optional;

/**
 * Created by jun.
 */
public abstract class Strings {
    public static String nullToEmpty(String value) {
        return Optional.ofNullable(value).orElse("");
    }

    public static boolean isNull(String value) {
        return null == value;
    }
}
