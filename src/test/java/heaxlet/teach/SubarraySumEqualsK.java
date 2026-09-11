package heaxlet.teach;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubarraySumEqualsK {


    public int subarraySum(int[] nums, int k) {

        var subNum = new HashMap<Integer, Integer>();
        subNum.put(0, 1);

        int total = 0;
        int count = 0;
        for (int num : nums) {
            total += num;

            if (subNum.containsKey(total - k)) {
                count += subNum.get(total - k);
            }
            subNum.put(
                    total,
                    subNum.getOrDefault(total, 0) + 1
            );
        }

        return count;
    }


    @Test
    void test1() {
        assertEquals(
                2,
                subarraySum(new int[]{1, 1, 1}, 2)
        );
    }

    @Test
    void test2() {
        assertEquals(
                2,
                subarraySum(new int[]{1,1,-1,1,2,5}, 7)
        );
    }
}
