package org.example;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author andreiserov
 */
public class Task2 {
    public static void main(String[] args) {
        String[] strings = {"one", "two", "three", null};

        var stringList = Stream.of(strings).toList();
        System.out.println(stringList.size());

        stringList = List.of(strings);
        System.out.println(stringList.size());
    }

    /**
     * a. 4 4
     * b. 4 3
     * c. 3 4
     * d. ничто из этого
     */
}


