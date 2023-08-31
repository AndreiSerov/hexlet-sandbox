package hexlet.teach.max_array_problem;

import java.util.Arrays;

/**
 * @author andreiserov
 */
public class Solution {

    public static int maxSubArray31(int[] nums) {
        int result = 0;
        int sum = 0;

        for (int i : nums) {
            sum += i;
            sum = Integer.max(sum, 0);
            result = Integer.max(result, sum);
        }


        return result;
    }

    public static int maxSubArrayRe(int[] nums) {
        return solve(nums, 0, false);
    }

    public static int solve(int[] nums, int index, boolean mustPick) {
        // our subarray must contain atleast 1 element. If mustPick is false at end means no element is picked and this is not valid case
        if(index >= nums.length)
            return mustPick ? 0 : Integer.MIN_VALUE;

        // either stop here or choose current element and recurse
        // try both choosing current element or not choosing
        if (mustPick)
            return Math.max(
                0,
                nums[index] + solve(nums, index + 1, true)
            );

        return Math.max(
            solve(nums, index + 1, false),
            nums[index] + solve(nums, index + 1, true)
        );
    }

    private static int getMaxSumInSubArray(int[] nums, int index, int prevSum, int maxSum) {
        if (index == nums.length) return maxSum;

        final int curSum = nums[index] + prevSum;
        return getMaxSumInSubArray(nums, index + 1, curSum, Math.max(curSum, maxSum));
    }

    private static int maxInArray(int[] nums, int index, int ans) {
       if (index == nums.length) return ans;

       return maxInArray(
           nums,
           index + 1,
           Math.max(ans, getMaxSumInSubArray(nums, index, 0, Integer.MIN_VALUE))
       );
    }

    public static int maxSubArray(int[] nums) {
        return maxInArray(nums, 0, Integer.MIN_VALUE);
    }


    /**
     * (Divide & Conquer)
     * @param nums
     * @return
     */
    public static int maxSubArrayDivide(int[] nums) {

        return solve(nums, 0, false);
    }
}
