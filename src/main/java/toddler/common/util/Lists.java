package toddler.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jun.
 */
public abstract class Lists {
    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> List<T> newArrayList(T... values) {
        return new ArrayList<>(Arrays.asList(values));
    }
}
