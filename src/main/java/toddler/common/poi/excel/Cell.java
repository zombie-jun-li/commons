package toddler.common.poi.excel;

import org.apache.poi.hssf.util.HSSFColor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by jun.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cell {
    int index() default 0;

    String title() default "";

    HSSFColor.HSSFColorPredefined background() default HSSFColor.HSSFColorPredefined.RED;

    int size() default 30;
}
