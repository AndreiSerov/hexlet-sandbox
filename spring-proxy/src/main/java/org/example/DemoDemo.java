package org.example;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.Instant;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author andreiserov
 */

interface Demo {
    void method();
}
public class DemoDemo implements Demo {

    public void method() {
        System.out.println("Hello");
    }

}

@Component
class InheritenceProxy extends DemoDemo {

    @Annotation2
    public void method() {
        super.method();
    }
}

@Retention(RUNTIME)
@Target(METHOD)
@interface Annotation2 {

}

@Aspect
@Component
class MyAspect2 {

    @Pointcut("@annotation(org.example.Annotation2)")
    public void annotated() {
    }

    @Before("annotated()")
    public void before() {
        final Instant start = Instant.now();
        System.out.println("Method called at: " + start);
    }

    @After("annotated()")
    public void after() {
        final Instant finish = Instant.now();
        System.out.println("Method finished exectution at: " + finish);
    }
}

class DelegateProxy {
    private Demo obj = new DemoDemo();

    public void method() {
        final Instant start = Instant.now();
        System.out.println("Method called at: " + start);


        obj.method();


        final Instant finish = Instant.now();
        System.out.println("Method finished exectution at: " + finish);
        final long totalTime = finish.toEpochMilli() - start.toEpochMilli();
        System.out.println("Total exectution time is: " + totalTime);
    }

    public static void main(String[] args) {

    }
}
