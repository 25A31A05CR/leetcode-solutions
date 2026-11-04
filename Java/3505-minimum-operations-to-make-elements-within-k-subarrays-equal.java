import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        if (n < x * k) return -1;

        long[] cost = new long[n - x + 1];
        Window window = new Window();

        for (int i = 0; i < n; i++) {
            window.add(nums[i]);

            if (i >= x) {
                window.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        Arrays.fill(prev, INF);
        prev[0] = 0;

        for (int groups = 1; groups <= k; groups++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }
            prev = curr;
        }

        return prev[n];
    }

    private static class Window {
        private final TreeMap<Integer, Integer> left = new TreeMap<>();
        private final TreeMap<Integer, Integer> right = new TreeMap<>();
        private int leftSize = 0, rightSize = 0;
        private long leftSum = 0, rightSum = 0;

        void add(int value) {
            if (left.isEmpty() || value <= left.lastKey()) {
                addTo(left, value);
                leftSize++;
                leftSum += value;
            } else {
                addTo(right, value);
                rightSize++;
                rightSum += value;
            }
            balance();
        }

        void remove(int value) {
            if (left.containsKey(value)) {
                removeFrom(left, value);
                leftSize--;
                leftSum -= value;
            } else {
                removeFrom(right, value);
                rightSize--;
                rightSum -= value;
            }
            balance();
        }

        private void balance() {
            int target = (leftSize + rightSize + 1) / 2;

            while (leftSize > target) {
                int value = left.lastKey();
                removeFrom(left, value);
                leftSize--;
                leftSum -= value;

                addTo(right, value);
                rightSize++;
                rightSum += value;
            }

            while (leftSize < target) {
                int value = right.firstKey();
                removeFrom(right, value);
                rightSize--;
                rightSum -= value;

                addTo(left, value);
                leftSize++;
                leftSum += value;
            }
        }

        long getCost() {
            long median = left.lastKey();
            return median * leftSize - leftSum
                 + rightSum - median * rightSize;
        }

        private void addTo(TreeMap<Integer, Integer> map, int value) {
            map.merge(value, 1, Integer::sum);
        }

        private void removeFrom(TreeMap<Integer, Integer> map, int value) {
            int count = map.get(value);
            if (count == 1) {
                map.remove(value);
            } else {
                map.put(value, count - 1);
            }
        }
    }
}