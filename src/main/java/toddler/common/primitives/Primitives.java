package toddler.common.primitives;

/**
 * Created by jun.
 */
public abstract class Primitives {

    public static <T> T defaultValue(T t) {
        if (t instanceof Boolean) return Defaults.defaultValue((Class<T>) Boolean.class);
        if (t instanceof Character) return Defaults.defaultValue((Class<T>) Character.class);
        // numbers
        if (t instanceof Short) return Defaults.defaultValue((Class<T>) Short.class);
        if (t instanceof Integer) return Defaults.defaultValue((Class<T>) Integer.class);
        if (t instanceof Long) return Defaults.defaultValue((Class<T>) Long.class);
        if (t instanceof Float) return Defaults.defaultValue((Class<T>) Float.class);
        if (t instanceof Double) return Defaults.defaultValue((Class<T>) Double.class);
        throw new IllegalArgumentException("Param should be primitive data types!");
    }


    public static <T extends Number> T defaultNumber(T t) {
        return defaultValue(t);
    }
}
