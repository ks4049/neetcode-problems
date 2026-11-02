class Solution {
    public int subarraySum(int[] nums, int k) {
        int prefixSum=0;
        int len = nums.length;
        int subarraySum=0;
        Map<Integer,Integer> sumMap = new HashMap<>();
        prefixSum = 0;
        for(int i=0; i<len; i++) {
            prefixSum = prefixSum+nums[i];
            
            if(prefixSum == k) {
                subarraySum++;
            }
            if(sumMap.containsKey(prefixSum-k)) {
                subarraySum+= sumMap.get(prefixSum-k);
            }
            sumMap.put(prefixSum, sumMap.getOrDefault(prefixSum, 0)+1);
        }
        return subarraySum;
    }
}