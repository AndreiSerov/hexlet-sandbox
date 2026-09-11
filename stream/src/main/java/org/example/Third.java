package org.example;

import java.util.Collection;

/**
 * @author andreiserov
 */

record ContactBook(
    Contact owner,
    Collection<Contact> contacts
) {}

public class Third {

    public Collection<Contact> getAllContactsByAge(Collection<ContactBook> books, Long maxAge) {
        return books
            .stream()
            .flatMap(it -> it.contacts().stream())
            .filter(it -> it.age() < maxAge)
            .toList();
    }
}
