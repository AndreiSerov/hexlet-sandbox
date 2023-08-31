package org.example;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * PASS
 */
public class Task5 {
    public static void main(String[] args) {
        final List<String> strings = List.of("a", "b", "c");
        final Collection<String> stringCollection = List.of("a", "b", "c");

        final Collection<String> unmodifiableStrings = Collections.unmodifiableList(strings);

        System.out.println(strings.equals(stringCollection));
        System.out.println(stringCollection.equals(strings));
        System.out.println(stringCollection.equals(unmodifiableStrings));
    }

    /**
     * 1. true false
     * 2. true true
     * 3. false true
     * 4. ничто из этого
     */
}


