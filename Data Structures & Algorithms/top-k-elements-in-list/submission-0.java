class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numFrequencyMap = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++) {
            numFrequencyMap.put(nums[i], numFrequencyMap.getOrDefault(nums[i], 0)+1);
        }
        //Group numbers by frequency
        Map<Integer, List<Integer>> frequencyNumMap = new HashMap<Integer, List<Integer>>();
        for(int num: numFrequencyMap.keySet()) {
            int frequency = numFrequencyMap.get(num);
            List<Integer> numsByFrequency = frequencyNumMap.getOrDefault(frequency, new ArrayList<>());
            numsByFrequency.add(num);
            frequencyNumMap.put(frequency, numsByFrequency);
        }
        int[] kFrequentElements = new int[k];
        int m=0;
        // go through the frequencies from highest to lowest
        for(int i=nums.length; i>=1 && m<k ; i--) {
            List<Integer> numsByFrequency = frequencyNumMap.getOrDefault(i, new ArrayList<>());
            for(int j=0; j<numsByFrequency.size()&& m<k; j++) {
                kFrequentElements[m++] = numsByFrequency.get(j);
            }
        }
        return kFrequentElements;
        
    }
}
