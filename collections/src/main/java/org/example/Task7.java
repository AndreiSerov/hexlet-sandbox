package org.example;

import java.util.HashSet;
import java.util.Set;

public class Task7 {
    public static void main(String[] args) {
        final Set<Character> chars = new HashSet<>();

        for (char ch = 'а'; ch <= 'я'; ch++) {
//            System.out.print(ch);
            chars.add(ch);
            chars.remove((char) (ch - 1));
        }

//        System.out.println();
        System.out.println(chars.size());
    }


    /**
     * a. 1
     * b. 32
     * c. 33
     * d. Error
     */
}


