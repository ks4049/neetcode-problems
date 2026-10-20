class Solution {
    public int removeElement(int[] nums, int val) {
        int replaceIndex=-1;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] != val) {
                if(replaceIndex != -1 && replaceIndex < i) {
                    int temp = nums[i];
                    nums[i] = nums[replaceIndex];
                    nums[replaceIndex] = temp;
                    replaceIndex++;
                }
            } else if(replaceIndex == -1) {
                    replaceIndex = i; 
            } 
        }
        return replaceIndex==-1? nums.length: replaceIndex;
    }
}