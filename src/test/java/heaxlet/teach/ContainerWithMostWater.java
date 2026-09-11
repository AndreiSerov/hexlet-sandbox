package heaxlet.teach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerWithMostWater {


    public int maxArea(int[] height) {
        int maxArea = 0, left = 0, right = height.length - 1;

        while (left < right) {
            int curArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, curArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }


    @Test
    void test1() {
        assertEquals(
                49,
                maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})
        );
    }

    @Test
    void test2() {
        assertEquals(
                1,
                maxArea(new int[]{1, 1})
        );
    }
}
