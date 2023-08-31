package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author andreiserov
 */

/*
Нужно написать свой рекуррентный дженерик наподобие того, что в классе Стримов
 */
public class GenericDemo {
    public void someMethod(Object shouldBeString) {

        final String string = (String) shouldBeString;

        var kaliaBalia = List.of("kalia", "balia", "kalia", "balia", "kalia", "balia", "kalia", "balia", "kalia", "balia"
            , "kalia", "balia", "kalia", "balia", "kalia", "balia");


    }

    public static List<? extends Number> invariant(Collection<? extends Number> collection) {
        final Stream<? extends Number> stream = collection.stream();
        return collection.stream().toList();
    }

    public static void main(String[] args) {
        final Number num = 10;
        final List<Number> numberList = new ArrayList<>(List.of(num));
        final List<BigDecimal> integerList = List.of(BigDecimal.ONE);

        Collections.copy(numberList, integerList);


        numberList.add(10);
        final Number number = numberList.get(1);

        final List<? extends Number> invariant = invariant(integerList);
        invariantFix(integerList);

        final List<? super Fruit> fruits = new ArrayList<>(List.of(new Apple(), new Fruit()));

        fruits.add(null);

        final Object fruit = fruits.get(0);

        /**
         *
         * Если получать, то extends ( contrvariance)  Upper Bounded Wildcards
         *
         * Если даавать на вход, то super (covariance) Lower Bounded Wildcards
         *
         */
//        fruits.add(new Apple());
//        fruits.add(new Fruit());
//        fruits.add(new Object());

        final IntBox intBox = new IntBox();

        Box stringBox = new StringBox();

//        final Integer param = intBox.param;


//        final Integer param = intBox.param;

//
        eat(fruits);
//
        invariant(numberList);
//        invariant(integerList);

    }

    static void eat(List<? super Fruit> food) {
        food.add(null);
        food.add(new Apple());
        food.add(new Fruit());

    }

    static class Fruit {
    }

    static class Apple extends Fruit {
    }


    public static <T extends Number> List<T>
    invariantFix(Collection<T> collection) {

        return collection.stream().toList();
    }

    public static <R, T extends Iterable<R>> List<R> convert(T iterable) {
        final List<R> objects = new ArrayList<>();
        for (R o : iterable) {
            objects.add(o);
        }
        return objects;
    }


}

/*
When a parameterized type being passed to a method will produce instances of T
 (they will be retrieved from it in some way), ? extends T should be used,
 since any instance of a sub class of T is also a T.
 */

class Covariant<T extends Collection<?>> {

    private T someVal;

    public Covariant(T someVal) {
        this.someVal = someVal;
    }

    T get() {
        return someVal;
    }
}

class Box<T> {
    T param;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}

class StringBox extends Box<String> {


}

class NumberBox<T extends Number> extends Box<T> {
    List<T> params;
}

class IntBox extends NumberBox<Integer> {
}

class LongBox extends NumberBox<Long> {
}


class BoxDemo {
    static void method(NumberBox<? super Integer> nums) {
        HashMap<?, ?> map = new HashMap<String, String>();
        map = new HashMap<String, Integer>();


    }

    public static void main(String[] args) {


        final Box<? super Inner> box = new Box<>();
        box.param = new Inner();

        final StringBox stringBox = new StringBox();
        stringBox.param = "String";

        final NumberBox intBox = new IntBox();
        final LongBox longBox = new LongBox();
//        method(longBox); // error
        method(intBox);

        intBox.param = 100L;
        intBox.param = 1000;

        Number num = 10;
        final List<Number> nums = List.of(num);
        final List<Integer> integers = List.of(1, 2, 3, 4);
        //     ArrayList<Integer>

        final ArrayList<Object> objects = new ArrayList<>();


        final List<Box> boxes = Stream.of(1, 2, 3, 4, 5)
            .map(it -> new Box())
            .toList();


        intBox.setParam(new Object());


    }

    static class Inner {
        String param;
    }

}

interface NotRecursive {

    default NotRecursive method() {
        System.out.println("NotRecursive " + this.getClass());
        return this;
    }
}

class Sample implements NotRecursive {

    public Sample childMethod() {
        System.out.println("Sample " + this.getClass());
        return this;
    }
}

interface Recursive<T extends Recursive<T>> {

    @SuppressWarnings("unchecked")
    default T method() {
        System.out.println("Recursive " + this.getClass());
        return (T) this;
    }
}

abstract class AbstractRecursive<T extends AbstractRecursive<T>> implements Recursive<T> {

    @SuppressWarnings("unchecked")
    public T methodFromAbstractClass() {
        System.out.println("AbstractRecursive " + this.getClass());
        return (T) this;
    }
}

class RecursiveImpl extends AbstractRecursive<RecursiveImpl> {

    public RecursiveImpl anotherMethod() {
        System.out.println("Impl " + this.getClass());
        return this;
    }
}

class RecursiveDemo {
    public static void main(String[] args) {

        final Sample sample = new Sample();
        final NotRecursive sampleParent = sample
            .childMethod()
            .method()
            .method();
        sampleParent.method();

        final RecursiveImpl recursive = new RecursiveImpl();

        recursive.method()
            .methodFromAbstractClass()
            .anotherMethod()
            .method();
        recursive.method()
            .methodFromAbstractClass()
            .anotherMethod()
        ;
    }
}


@FunctionalInterface
interface OriginalPredicate<T> {
    boolean test(T t);
}

class Puzzle {
    public static void main(String[] args) {
        OriginalPredicate<Object> lambda = it -> it.equals("adidas");
        OriginalPredicate<String> methodRef = it -> it.equals("adidas");

        lambda.test("asdf");
        methodRef.test("asdf");
        methodRef.test("asdf");
    }
}