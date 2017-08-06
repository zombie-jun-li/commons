package toddler.common.util;

import org.junit.Assert;
import org.junit.Test;
import toddler.common.primitives.Primitives;

/**
 * Created by jun.
 */
public class NumbersTest {


    @Test
    public void nullToZero() {
        Assert.assertEquals(Primitives.defaultValue(short.class), Numbers.nullToZero(null, short.class));
        Assert.assertEquals(Primitives.defaultValue(int.class), Numbers.nullToZero(null, int.class));
        Assert.assertEquals(Primitives.defaultValue(long.class), Numbers.nullToZero(null, long.class));
        Assert.assertEquals(Primitives.defaultValue(float.class), Numbers.nullToZero(null, float.class));
        Assert.assertEquals(Primitives.defaultValue(double.class), Numbers.nullToZero(null, double.class));
    }
}