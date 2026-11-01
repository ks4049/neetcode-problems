class Solution {
    public int removeDuplicates(int[] nums) {
        int len=nums.length, dupCount=0, dupIndex=-1;
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if(freqMap.getOrDefault(nums[i], 0) < 2) {
                freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0)+1);
                if(dupIndex != -1 && dupIndex!= i) {
                    // swap dupIndex with i
                    int temp = nums[dupIndex];
                    nums[dupIndex] = nums[i];
                    nums[i] = temp;
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