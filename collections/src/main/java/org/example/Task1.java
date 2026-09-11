package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author andreiserov
 */
public class Task1 {
    public static void main(String[] args) {
        String[] strings = {"one", "two", "three"};
        final var stringList = List.of(strings);


        // int, double, float, char, boolean, short, long, byte
        int[] ints = {1, 2, 3};
        List<Integer> correctIntList = new ArrayList<>();
        for (int i : ints) {
            correctIntList.add(i);
        }
        final var intList = List.of(ints);



        System.out.println(stringList.contains("one"));
        System.out.println(intList.contains(1));
    }

    /**
     * 1. true false
     * 2. true true
     * 3. false true
     * 4. ничто из этого
     */
}


