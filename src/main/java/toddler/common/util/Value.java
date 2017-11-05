package toddler.common.util;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by jun.li
 */
public final class Value<T> {
    private static final Value<?> EMPTY = of(null);

    private final T object;

    private Value(T object) {
        this.object = object;
    }


    public static <T> Value<T> of(T object) {
        return new Value<>(object);
    }

    public Value<T> ifTrue(Predicate<T> predicate) {
        return predicate.test(get()) ? this : empty();
    }

    public Value<T> ifFalse(Predicate<T> predicate) {
        return predicate.test(get()) ? empty() : this;
    }

    public void consume(Consumer<T> consumer) {
        consumer.accept(get());
    }

    public T get() {
        return object;
    }

    private Value<T> empty() {
        return (Value<T>) EMPTY;
    }
}
