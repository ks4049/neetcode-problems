class Solution {
    public boolean canJump(int[] nums) {
        if(nums.length == 1) {
            return true;
        }
        return canJumpUtil(0, nums, nums.length-1);
    }

    boolean canJumpUtil(int start, int[] nums, int dest) {
        if(start>=dest) {
            return true;
        }
        if(nums[start] == 0) {
            return false;
        }
        for(int i=1; i<=nums[start]; i++) {
            if(canJumpUtil(start+i, nums, dest)) {
                return true;
            }
        }
        return false;
        
    }
}
