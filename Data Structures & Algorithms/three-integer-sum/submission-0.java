class Solution {
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0; i<nums.length; i++) {
            if(i==0 || nums[i-1]!= nums[i]) {
                // process i;
                int targetSum = -nums[i];
                List<List<Integer>> possibleTriplets = computeTripletsForTargetSum(nums[i], i+1, nums.length-1, nums, targetSum);
                if(!possibleTriplets.isEmpty()) {
                    resultList.addAll(possibleTriplets);
                }
            }
        }
        return resultList;
    }

    private List<List<Integer>> computeTripletsForTargetSum(int firstNum, int low, int high, int[] nums, int targetSum) {
        int len = nums.length;
        List<List<Integer>> resultList = new ArrayList<>();
        while(low < high) {
            if(nums[low] + nums[high] == targetSum) {
                List<Integer> triplet = new ArrayList<>();
                triplet.add(firstNum);
                triplet.add(nums[low]);
                triplet.add(nums[high]);
                resultList.add(triplet);
                int k=low, l=high;
                // skip duplicate pairs
                while(k<len && nums[k] == nums[low]) {
                    k++;
                }
                while(l >=0 && nums[l] == nums[high]) {
                    l--;
                }
                low=k;
                high=l;
            } else if(nums[low] + nums[high] < targetSum) {
                low++;
            } else {
                high--;
            }
        }
        //System.out.println("Possible triplets for num "+firstNum + " are: " + resultList);
        return resultList;
    }
}
