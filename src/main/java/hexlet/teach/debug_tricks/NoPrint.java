package hexlet.teach.debug_tricks;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author andreiserov
 */
public class NoPrint {
    public static void main(String[] args) {

        final Child child = new Child();
        System.out.println(child);

        System.out.println(new ChildDecorator(child));
    }
}


final class Child {
    private String name;
    private int age;  // show with collection

    public Child() {
        this.name = "Vasya";
        this.age = 10;
    }

    public String getName() {
        return name;
    }
}

class ChildDecorator {
    private Child child;

    public ChildDecorator(Child child) {
        this.child = child;
    }

//    @Override
//    public String toString() {
//        return "ChildDecorator{" +
//            "child=Child{name=" + child.getName() +
//            "}}";
//    }

    @Override
    public String toString() {
        int age;


        try {
            final Field ageField = child.getClass().getDeclaredField("age");
            ageField.setAccessible(true);
            age = (int) ageField.get(child);
            ageField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return "ChildDecorator{" +
            "child=Child{name=" + child.getName() + ", age=" + age +
            "}}";
    }
}