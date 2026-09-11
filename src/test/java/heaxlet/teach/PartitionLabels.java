package heaxlet.teach;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartitionLabels {


    /**
     * Given a string, split it into as many parts as possible so that each letter appears in
     * at most one part, and return the length of each part in order.
     */
    public List<Integer> partitionLabels(String s) {
        // todo


        return null;
    }


    @Test
    void test1() {
        assertEquals(
                List.of(9, 7, 8),
                partitionLabels("ababcbacadefegdehijhklij")
        );
    }

    @Test
    void test2() {
        assertEquals(
                List.of(3, 10),
                partitionLabels("eccbbbbdec")
        );
    }
}
