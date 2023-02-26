package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author andreiserov
 */

// next theme reflection!!, DI!!, IoC
public class PhoneNumberValidator {

    public static void main(String[] args) {
        final List<Contact> phones = List.of(
            new Contact("Vova", "79123456789"),
            new Contact("Vasya", "89123456789"),
            new Contact("Vania", "7912345678910")
        );

        phones.forEach(
            it -> System.out.println(validate(it) ? "Correct number: " + it.getPhone() : "Invalid number: " + it.getPhone())
        );
    }

    public static boolean validate(Object obj) {
        final Class<?> clazz = obj.getClass();
        return Arrays.stream(clazz.getDeclaredFields())
            .filter(it -> it.isAnnotationPresent(PhoneNumber.class))
            .anyMatch(field -> {
                try {
                    field.setAccessible(true);
                    return validateWithCodeRegion((String) field.get(obj), field.getAnnotation(PhoneNumber.class));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
    }

    private static boolean validateWithCodeRegion(String phone, PhoneNumber anno) {
        return switch (anno.region()) {
            case RUSSIA -> {
                final String cut = phone.substring(1);
                Pattern pattern = Pattern.compile("\\d{10}");
                yield phone.startsWith("7") && pattern.matcher(cut).matches();
            }
        };
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.RECORD_COMPONENT, ElementType.TYPE_USE})
@interface PhoneNumber {
    Region region() default Region.RUSSIA;
}

enum Region {
    RUSSIA
}

class Contact {
    String name;

    @PhoneNumber
    private final String phone;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
