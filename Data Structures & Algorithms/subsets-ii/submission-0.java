class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
      List<List<Integer>> resultList = new ArrayList<>();
      if(nums.length == 0) {
        return resultList;
      }
      Arrays.sort(nums);
      resultList.add(new ArrayList<>());
      generateSubsetsUtil(nums, 0, new ArrayList<>(), resultList);
      return resultList;
    }
  
  	private void generateSubsetsUtil(int[] nums, int startIndex, List<Integer> currentList, List<List<Integer>> resultList) {
      if(startIndex == nums.length) {
        return;
      }
      Set<Integer> hashSet = new HashSet<Integer>();
      for(int i=startIndex; i<nums.length; i++) {
        if(!hashSet.contains(nums[i])) {
        	currentList.add(nums[i]);
        	generateSubsetsUtil(nums, i+1, currentList, resultList);
            resultList.add(new ArrayList<>(currentList));
            hashSet.add(nums[i]);
            currentList.remove(currentList.size()-1);
        }
      }
    }
}
