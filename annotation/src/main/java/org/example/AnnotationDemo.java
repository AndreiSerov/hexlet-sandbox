package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

/**
 * @author andreiserov
 */
@Type
public class AnnotationDemo {
    @Annotation
    @interface Anno {
    }

    @Field
    String s;

    @Constructor
    AnnotationDemo(@Param int x) {
        @LocalVariable int y = x;
    }

    @Method
    <@TypeParameter T> T method() {
        return null;
    }

    @Method
    @TypeUse List<@TypeUse String @TypeUse []> method2() {
        return null;
    }
}

@Type
record RecordDemo(
    @RecordComponent
    @Field
    @TypeUse
    @Method
    @Param
    String s
) {}


@Target(ElementType.TYPE)
@interface Type { }
@Target(ElementType.ANNOTATION_TYPE)
@interface Annotation { }
@Target(ElementType.FIELD)
@interface Field { }
@Target(ElementType.CONSTRUCTOR)
@interface Constructor { }
@Target(ElementType.PARAMETER)
@interface Param { }
@Target(ElementType.LOCAL_VARIABLE)
@interface LocalVariable { }
@Target(ElementType.METHOD)
@interface Method { }
@Target(ElementType.TYPE_PARAMETER)
@interface TypeParameter { }
@Target(ElementType.TYPE_USE)
@interface TypeUse { }
@Target(ElementType.RECORD_COMPONENT)
@interface RecordComponent { }

