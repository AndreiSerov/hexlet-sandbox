package org.example.field_example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static java.lang.String.format;

/**
 * @author andreiserov
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final User godrik = new User("Godrik", "Dark Lord");

        final Class<? extends User> aClass = godrik.getClass();

        final Method[] methods = aClass.getMethods();
        final Method[] declaredMethods = aClass.getDeclaredMethods();

        final Field[] fields = aClass.getFields();
        final Field[] declaredFields = aClass.getDeclaredFields();


        final Method setKaliaField = aClass.getDeclaredMethod("setKaliaField");
        final Method setKaliaField2 = aClass.getDeclaredMethod("setKaliaField", String.class);
        setKaliaField2.setAccessible(true);
        setKaliaField.setAccessible(true);
        setKaliaField.invoke(godrik);
        setKaliaField2.invoke(godrik, "Argument for setKaliaField2");
        setKaliaField.setAccessible(false);


        final StringBuilder sb = new StringBuilder("{\n");
        for (Field field : declaredFields) {
            field.setAccessible(true);
            sb.append(
                format("  \"%s\": \"%s\"\n", field.getName(), field.get(godrik))
            );
            field.setAccessible(false);
        }
        sb.append("}");


        System.out.println(sb);
        final Class<?>[] declaredClasses = aClass.getDeclaredClasses();
    }

    public static void getClassExample(Object o) throws ClassNotFoundException {
        final Class<?> aClass = o.getClass();
        final Class<User> userClass = User.class;
        final Class<?> aClass2 = Class.forName("org.example.field_example.User");
        final Class<? extends String> aClass1 = "lajsdf".getClass();
        final Class<? extends Integer> aClass3 = Integer.valueOf(1234).getClass();
    }

    record User(String name, String nick) {}
}
