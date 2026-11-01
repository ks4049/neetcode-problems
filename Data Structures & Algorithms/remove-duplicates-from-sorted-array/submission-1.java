class Solution {
    public int removeDuplicates(int[] nums) {
        int dupIndex=-1, len= nums.length, dupCount=0;
        Set<Integer> numSet = new HashSet<>();
        for(int i=0; i<len; i++) {
            if(!numSet.contains(nums[i])) {
                numSet.add(nums[i]);
                if(dupIndex!= i && dupIndex!= -1){
                    int temp = nums[i];
                    nums[i] = nums[dupIndex];
                    nums[dupIndex] = temp;
                    dupIndex++;
                }
            } else {
                if(dupIndex == -1) {
                    dupIndex = i;
                }
                dupCount++;
            }  
            
        }
        return nums.length-dupCount;
    }
}