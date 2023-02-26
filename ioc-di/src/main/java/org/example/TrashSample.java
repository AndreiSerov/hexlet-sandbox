package org.example;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuperAnnotation
public class TrashSample {

    @Wow Wow @Wow [] @Wow [] wow(@Wow Wow[] wow @Wow [])
        @Wow [] @Wow [] throws @Wow WOW {
        return new Wow @Wow [] @Wow [] @Wow [] @Wow [] { { wow } };
    }

    @Target(ElementType.TYPE_USE)
    @interface Wow { }
    class WOW extends Exception { }
}

@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@interface SuperAnnotation { }


@SuperAnnotation
class SuperClass { }