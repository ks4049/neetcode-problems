class Solution {
    public boolean hasDuplicate(int[] nums) {
        Set<Integer> visited = new HashSet<Integer>();
        boolean hasDuplicate=false;
        for(int i=0; i<nums.length; i++) {
            if(visited.contains(nums[i])) {
                hasDuplicate = true;
                break;
            }
            visited.add(nums[i]);
        }
        return hasDuplicate;
    }
}