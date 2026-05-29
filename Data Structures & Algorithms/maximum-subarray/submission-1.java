class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length == 1) {
            return nums[0]; // nothing to sum with, return the same elment as max
        }
        // greedily try to find the maximum sum
        int currentSum=nums[0], maxSoFar=nums[0];
        for(int i=1; i<nums.length; i++) {
            currentSum+=nums[i];
            maxSoFar = Math.max(maxSoFar, Math.max(currentSum, nums[i]));
            if(nums[i] > currentSum) {
                currentSum = nums[i]; // reset the current sum to start fresh
            }
            
        }
        return maxSoFar;

        
    }
}
