package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author andreiserov
 */

record Contact(String name, Long age, String phone) implements Comparable {
    @Override
    public int compareTo(Object o) {
        var cast = (Contact) o;
        if (cast.age > this.age) {
            return -1;
        } else if (cast.age < this.age) {
            return 1;
        }
        return 0;
    }
}

public class First {
    public Map<Long, List<Contact>> groupByAge(List<Contact> contacts) {
        Map<Long, List<Contact>> map = new HashMap<>();
        for (Contact contact : contacts) {
            List<Contact> contactSubList = map.getOrDefault(contact.age(), new ArrayList<>());
            contactSubList.add(contact);
            map.put(contact.age(), contactSubList);
        }
        return map;
    }

    public Map<Long, List<Contact>> groupByAgeStream(List<Contact> contacts, Filter filter) {
        return contacts
            .stream()
            .collect(
                Collectors.groupingBy(
                    Contact::age
                )
            );
    }

    public static void main(String[] args) {
        final List<Contact> contacts = List.of(
            new Contact("18L", 18L, "2134"),
            new Contact("24L", 24L, "2134"),
            new Contact("161L", 161L, "2134"),
            new Contact("18L", 18L, "2134"),
            new Contact("17", 17L, "2134"),
            new Contact("19L", 19L, "2134"),
            new Contact("234L", 234L, "2134"),
            new Contact("50L", 50L, "2134")
        );

        System.out.println(groupAgeDiapazone(contacts));


    }


    public static Map<String, List<Contact>> groupAgeDiapazone(List<Contact> contacts) {
        return contacts
            .stream()
            .collect(Collectors.groupingBy(Contact::age))
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    it -> {
                        final Long age = it.getKey();
                        if (age < 18) {
                            return "меньше 18";
                        } else if (age < 60) {
                            return "больше 18, меньше 60";
                        } else return "Больше 60";
                    },
                    it -> {
                        final List<Contact> value = new ArrayList<>();
                        value.addAll(it.getValue());
                        return value;
                    },
                    (exist, replace) -> {
                        exist.addAll(replace);
                        return exist;
                    }
                )
            );
    }
}

