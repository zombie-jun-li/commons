package toddler.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jun.
 */
public abstract class Maps {
    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }
}
