package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;

/**
 * @author andreiserov
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Parametrized {

    int[] numbers() default {1, 2, 3};

    String value() default "Hello";

    SomeEnum enumVal();

    Inner annoParam();
    Inner[] annoParams();

    Class<? extends Number> number()
        default Integer.class;

}
@Parametrized(
    enumVal = SomeEnum.ENUM,
    annoParam = @Inner,
    annoParams = {@Inner, @Inner},
    number = BigDecimal.class
)
@interface Inner {}
enum SomeEnum {
    ENUM;

    public static void main(String[] args) {

        short val = 1234;

        final Short aShort = new Short(val);


        final Class<Inner> clazz = Inner.class;
        if (clazz.isAnnotationPresent(Parametrized.class)) {
            final Parametrized annotation = clazz.getAnnotation(Parametrized.class);
            final String value = annotation.value();

            System.out.println(value + " world!");
        }
    }
}


