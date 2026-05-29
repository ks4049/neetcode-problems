class Solution {
     public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> resultList = new ArrayList<>();
      
      generateSubsetsUtil(nums, 0, new ArrayList<Integer>(), resultList);
      resultList.add(new ArrayList<Integer>());
      return resultList;
    }
  
  	private void generateSubsetsUtil(int[] nums, int startIndex, List<Integer> currentList, List<List<Integer>> resultList) {
      if(startIndex == nums.length) {
        return;
      }
      for(int i=startIndex; i<nums.length; i++) {
        currentList.add(nums[i]);
        generateSubsetsUtil(nums, i+1, currentList, resultList);
        resultList.add(new ArrayList<>(currentList));
        currentList.remove(currentList.size()-1);
      }
    }
}
