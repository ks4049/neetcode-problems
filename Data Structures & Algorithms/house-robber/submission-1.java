class Solution {
    public int rob(int[] nums) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0]= nums[0];
        for(int i=1; i<nums.length; i++) {
            if(i-2 >=0) {
                prefixSum[i] = Math.max(prefixSum[i-1], nums[i] + prefixSum[i-2]);
            } else {
                prefixSum[i] = Math.max(nums[i], prefixSum[i-1]);
            }
        }
        return prefixSum[nums.length-1];
        
        
    }
}
