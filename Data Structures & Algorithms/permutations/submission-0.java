class Solution {
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> resultList = new ArrayList<>();
      generatePermutation(nums, new ArrayList<>(), new HashSet<>(), resultList);
      return resultList;
    }
  
  	private void generatePermutation(int[] nums, List<Integer> currentList, Set<Integer> currentSet, List<List<Integer>> resultList) {
      if(currentList.size() == nums.length) {
        resultList.add(new ArrayList<>(currentList));
        return;
      }
      for(int i=0; i<nums.length; i++) {
        if(!currentSet.contains(nums[i])) {
          currentList.add(nums[i]);
          currentSet.add(nums[i]);
          generatePermutation(nums, currentList, currentSet, resultList);
          currentList.remove(currentList.size()-1);
          currentSet.remove(nums[i]);
        }
      }
    }
}
