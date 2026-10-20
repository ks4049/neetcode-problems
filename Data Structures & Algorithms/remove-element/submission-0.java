class Solution {
    public int removeElement(int[] nums, int val) {
        int replaceIndex=-1;
        int valCount = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] != val) {
                if(replaceIndex != -1 && replaceIndex < i) {
                    int temp = nums[i];
                    nums[i] = nums[replaceIndex];
                    nums[replaceIndex] = temp;
                    replaceIndex++;
                }
            } else {
                valCount++;
                if(replaceIndex == -1) {
                    replaceIndex = i; 
                } 
            }
        }
        return nums.length-valCount;
    }
}