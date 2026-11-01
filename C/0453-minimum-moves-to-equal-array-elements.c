/*
 * LeetCode 453 - Minimum Moves to Equal Array Elements
 *
 * Idea:
 * Increasing n - 1 elements by 1 is equivalent to decreasing
 * the remaining element by 1. Therefore, the optimal strategy
 * is to reduce every element to the minimum element.
 *
 * Minimum moves = sum(nums) - (numsSize * minimum(nums)).
 */

int minMoves(int* nums, int numsSize) {
    if (numsSize <= 1) {
        return 0;
    }

    int minValue = nums[0];
    long long sum = 0;

    for (int i = 0; i < numsSize; i++) {
        sum += nums[i];

        if (nums[i] < minValue) {
            minValue = nums[i];
        }
    }

    return (int)(sum - (long long)numsSize * minValue);
}
