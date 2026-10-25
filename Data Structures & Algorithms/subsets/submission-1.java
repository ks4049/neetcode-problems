class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsetsUtil(nums, 0, nums.length, result, new ArrayList<>());
        return result;
    }

    private void subsetsUtil(int[] nums, int start, int end, List<List<Integer>> result, List<Integer> currentList) {
        
        for(int i=start; i<end; i++) {
            currentList.add(nums[i]);
            subsetsUtil(nums, i+1, end, result, currentList);
            currentList.remove(currentList.size()-1);
        }
        result.add(new ArrayList<>(currentList));
    }
}
