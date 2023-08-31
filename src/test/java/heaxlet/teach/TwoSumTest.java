package heaxlet.teach;

import org.junit.jupiter.api.Test;

import static hexlet.teach.GenericDemoKotlKt.twoSum;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author andreiserov
 */
public class TwoSumTest {

    @Test
    void test1() {
        assertArrayEquals(new int[] {0, 4}, twoSum(new int[] {3,2,4,1,9}, 12));
        assertArrayEquals(new int[] {0,1}, twoSum(new int[] {1,9,13,20,47}, 10));
        assertArrayEquals(new int[] {1,3}, twoSum(new int[] {1, 3, 6, 7, 9}, 10));
        assertArrayEquals(new int[] {1,2}, twoSum(new int[] {3, 2, 4}, 6));
        assertArrayEquals(new int[]{0, 1}, twoSum(new int[]{3, 3}, 6));
    }

    void kalia(int[] nums) {

//        nums.length
    }
}
