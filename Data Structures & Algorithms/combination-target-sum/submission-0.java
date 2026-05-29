class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
      List<List<Integer>> resultList = new ArrayList<>();
      generateCombinationSum(nums, target, 0, 0, new ArrayList<Integer>(), resultList);
      return resultList;
    }
  	
  
  	private void generateCombinationSum(int[] nums, int targetSum, int currentSum, int startIndex, List<Integer> currentList, List<List<Integer>> resultList) {
      if(startIndex == nums.length || currentSum > targetSum) {
        return;
      }
      if(currentSum == targetSum){
     	  resultList.add(new ArrayList<>(currentList));
      	return;
    	}
      for(int i=startIndex; i<nums.length; i++) {
        currentSum+=nums[i];
        currentList.add(nums[i]);
        generateCombinationSum(nums, targetSum, currentSum, i, currentList, resultList);
        currentList.remove(currentList.size()-1);
        currentSum-=nums[i];
      }
    }
}
