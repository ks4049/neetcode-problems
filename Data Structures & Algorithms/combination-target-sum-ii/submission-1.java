class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(candidates);
      	generateCombinationSumUtil(candidates, 0, 0, target, new ArrayList<>(), resultList);
      	return resultList;
    }
  
  	private void generateCombinationSumUtil(int[] candidates, int startIndex, int currentSum, int targetSum, List<Integer> currentList, List<List<Integer>> resultList) {
      if(currentSum == targetSum) {
        resultList.add(new ArrayList<>(currentList));
        return;
      }
      if(startIndex == candidates.length || currentSum > targetSum) {
        return;
      }
      
      Set<Integer> currentSet = new HashSet<Integer>();
      for(int i=startIndex; i<candidates.length; i++) {
        if(!currentSet.contains(candidates[i])) {
          currentSum+=candidates[i];
          currentList.add(candidates[i]);
          generateCombinationSumUtil(candidates, i+1, currentSum, targetSum, currentList, resultList);
          currentSet.add(candidates[i]); // add the current num to the set for the current level
          currentList.remove(currentList.size()-1);
          currentSum-=candidates[i];
        }
      }
    }
}
