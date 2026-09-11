package org.example.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author andreiserov
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {
}
