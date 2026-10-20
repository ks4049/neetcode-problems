class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxSum = 0, currentSum=0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] != 0) {
                currentSum+=nums[i];
            } else {
                // capture the max sum so far, reset the currentSum
                if(maxSum < currentSum) {
                    maxSum = currentSum;
                }
                currentSum = 0;
            }
        }
        return Math.max(maxSum, currentSum);
    }
}