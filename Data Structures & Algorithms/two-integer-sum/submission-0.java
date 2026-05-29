class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> numIndexMap = new HashMap<Integer,Integer>();

        int[] result = new int[2];
        for(int i=0; i<nums.length; i++) {
            int targetNum = target-nums[i];
            if(numIndexMap.containsKey(targetNum)) {
                result[0] = numIndexMap.get(targetNum);
                result[1] = i;
                break;
            }
            numIndexMap.put(nums[i], i);
        }
        return result;
    }
}
