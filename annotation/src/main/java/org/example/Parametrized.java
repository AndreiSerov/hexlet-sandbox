package org.example;

/**
 * @author andreiserov
 */
public @interface Parametrized {

    int[] numbers() default {1, 2, 3};

    String value() default "Hello";

    SomeEnum enumVal();

    Inner annoParam();
    Inner[] annoParams();

    Class<? extends Number> number()
        default Integer.class;
}

@interface Inner {}
enum SomeEnum { }
