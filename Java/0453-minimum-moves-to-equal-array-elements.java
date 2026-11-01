/*
 * LeetCode 453 - Minimum Moves to Equal Array Elements
 *
 * Idea:
 * Increasing n - 1 elements by 1 is equivalent to decreasing
 * the remaining element by 1. So reduce every element to the
 * minimum element.
 *
 * Minimum moves = sum(nums) - (nums.length * minimum(nums)).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public int minMoves(int[] nums) {
        int minValue = nums[0];
        long sum = 0;

        for (int num : nums) {
            sum += num;

            if (num < minValue) {
                minValue = num;
            }
        }

        return (int) (sum - (long) nums.length * minValue);
    }
}
