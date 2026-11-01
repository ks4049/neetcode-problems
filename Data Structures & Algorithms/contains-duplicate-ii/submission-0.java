class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        boolean containsDup = false;
        
        Set<Integer> numSet = new HashSet<Integer>();
        numSet.add(nums[0]);
        int left=0, right, i=1, len = nums.length;
        while(i<len) {
            right = i;
            // current window size
            if(right-left > k) {
                numSet.remove(nums[left]);
                left = left+1;
            }
            if(numSet.contains(nums[right])) {
                containsDup = true;
                break;
            }
            numSet.add(nums[right]);
            i++;
        }
        return containsDup;
    }
}