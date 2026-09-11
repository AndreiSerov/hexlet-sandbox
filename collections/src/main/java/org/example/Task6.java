package org.example;

import java.util.HashMap;
import java.util.Map;

public class Task6 {
    public static void main(String[] args) {
        final Map<NotFinalClass, String> map = new HashMap<>();

        map.put(new NotFinalClass(1), "one");
        map.put(new Child(1), "one");
        map.put(new NotFinalClass(1), "one");

        System.out.println(map.size());
    }

    /**
     * a. 1
     * b. 2
     * c. 3
     * d. Error
     */
}

class NotFinalClass {
    int num;

    public NotFinalClass(int num) {
        this.num = num;
    }


    // region
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotFinalClass that)) return false;

        return num == that.num;
    }

    @Override
    public int hashCode() {
        return num;
    }
    // endregion
}

final class Child extends NotFinalClass {
    int num2 = 1;

    public Child(int num) {
        super(num);
    }

    @Override
    public int hashCode() {
        return num + num2;
    }
}



