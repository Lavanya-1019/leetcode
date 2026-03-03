class Solution {

    private int kadaneMax(int[] nums) {
        int maxEndingHere = nums[0], maxSoFar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
    private int kadaneMin(int[] nums) {
        int minEndingHere = nums[0], minSoFar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minEndingHere = Math.min(nums[i], minEndingHere + nums[i]);
            minSoFar = Math.min(minSoFar, minEndingHere);
        }
        return minSoFar;
    }

    public int maxSubarraySumCircular(int[] nums) {
        int maxKadane = kadaneMax(nums); 

        int totalSum = 0;
        for (int num : nums) totalSum += num;

        int minKadane = kadaneMin(nums); 
        int circularMax = totalSum - minKadane; 

        return (circularMax == 0) ? maxKadane : Math.max(maxKadane, circularMax);
    }
}