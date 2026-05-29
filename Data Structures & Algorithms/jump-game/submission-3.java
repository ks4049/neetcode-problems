class Solution {
    public boolean canJump(int[] nums) {
        if(nums.length == 1) {
            return true;
        }
        return canJumpIterative(nums);
        //return canJumpUtil(0, nums, nums.length-1);
    }

    boolean canJumpIterative(int[] nums) {
        boolean[] canJump = new boolean[nums.length];
        int dest = nums.length-1;
        for(int i=nums.length-2; i>=0; i--) {
            if(i+nums[i] >= dest) {
                canJump[i] = true;
                dest = i;
            }
        }
        return canJump[0];
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
