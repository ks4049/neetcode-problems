class Solution {
    public int pivotIndex(int[] nums) {
        int[] leftSum = new int[nums.length];
        int[] rightSum = new int[nums.length];
        int len = nums.length;
        leftSum[0]=nums[0]; rightSum[len-1] = nums[len-1];
        // pre-compute the prefix sums
        for(int i=1; i<len; i++) {
            leftSum[i]= leftSum[i-1] + nums[i];
            rightSum[len-i-1] = rightSum[len-i]+nums[len-i-1];
        }
        int pivotIndex = -1;

        // identify the leftmost pivot index
        for(int i=0; i<len; i++) {
            int left, right=0;
            if(i==0) {
                left=0;
            } else {
                left = leftSum[i-1];
            }
            if(i == len-1) {
                right=0;
            } else {
                right = rightSum[i+1];
            }
            if(left == right) {
                pivotIndex = i;
                break;
            }
        }

        return pivotIndex;
    }
}