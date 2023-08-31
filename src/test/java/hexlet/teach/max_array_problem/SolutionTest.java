package hexlet.teach.max_array_problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author andreiserov
 */

/*
Дан массив чисел. Нужно найти подмассив с максимальной суммой чисел в подмассиве.

-2, 1, -3, 4, -1, 2, 1,-5,4
           4, -1, 2, 1 -> 6

 */
class SolutionTest {

    @Test void test1() {
        assertEquals(6, Solution.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

    @Test void test2() {
        assertEquals(1, Solution.maxSubArray(new int[] {1}));
    }

    @Test void test3() {
        assertEquals(23, Solution.maxSubArray(new int[] {5,4,-1,7,8}));
    }

    @Test void test4() {
        assertEquals(-1, Solution.maxSubArray(new int[] {-1, -1}));
    }

    @Test void test5() {
        assertEquals(-100, Solution.maxSubArray(new int[] {-100}));
    }

}