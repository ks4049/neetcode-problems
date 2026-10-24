class Solution {
    public void sortColors(int[] nums) {
        int left=0, right=nums.length-1, i=0;
        for(i=0; i<=right && left<right; ) {
            // compare left and ith index
            if(nums[i] == 0) {
                // swap left and i;
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                if(left==i) {
                    i++;
                }
                left++;
            } else if(nums[i] == 2) {
                // swap right and i;
                int temp = nums[right];
                nums[right] = nums[i];
                nums[i] = temp;
                right--;
            } else {
                i++;
            }
        }
    }
}