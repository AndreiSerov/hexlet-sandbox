package org.example;

import java.util.HashMap;

/**
 * @author andreiserov
 */
public class Task3 {
    public static void main(String[] args) {
        final HashMap<Integer, String> map = new HashMap<>();
        map.put(1, null);

        System.out.println(map.getOrDefault(1, "one"));

        map.putIfAbsent(1, "one");
        System.out.println(map.get(1));
    }

    /**
     * a. null one
     * b. one one
     * c. one null
     * d. ничто из этого
     */
}


