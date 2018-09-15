package toddler.common.util;


import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jun.
 */
public abstract class Numbers {
    public static <T extends Number> T nullToZero(T t, Class<T> type) {
        return Optional.ofNullable(t).orElse(Primitives.defaultValue(type));
    }

    private static class Primitives {

        private static final Map<Class<?>, Object> DEFAULTS;

        static {
            Map<Class<?>, Object> map = Maps.newHashMap();
            put(map, boolean.class, false);
            put(map, char.class, '\0');
            put(map, byte.class, (byte) 0);
            put(map, short.class, (short) 0);
            put(map, int.class, 0);
            put(map, long.class, 0L);
            put(map, float.class, 0f);
            put(map, double.class, 0d);
            DEFAULTS = Collections.unmodifiableMap(map);
        }

        private static <T> void put(Map<Class<?>, Object> map, Class<T> type, T value) {
            map.put(type, value);
        }

        private static <T> T defaultValue(Class<T> type) {
            return (T) DEFAULTS.get(type);
        }
    }

}
