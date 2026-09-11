package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * @author andreiserov
 */
public class Task4 {
    public static void main(String[] args) {
        final HashSet<String> hashSet = new HashSet<>(List.of("a", "b", "c"));
        final TreeSet<String> treeSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        treeSet.addAll(List.of("A", "B", "C"));

        System.out.println(treeSet.equals(hashSet));
        System.out.println(hashSet.equals(treeSet));
    }

    /**
     * 1. true false
     * 2. true true
     * 3. false true
     * 4. ничто из этого
     */
}


