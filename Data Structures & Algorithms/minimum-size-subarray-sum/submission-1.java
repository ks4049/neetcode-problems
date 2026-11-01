class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left=0,len=nums.length, i=0, minLen=Integer.MAX_VALUE, curSum=0;

        while(i<len) {
            curSum = curSum+nums[i];
            if(curSum < target) {
                i++;
            } else {
                minLen = Math.min(minLen, i-left+1);
                // decrement left to identify new min length;
                curSum = curSum-nums[left];
                curSum = curSum-nums[i];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE? 0: minLen;
    }
}