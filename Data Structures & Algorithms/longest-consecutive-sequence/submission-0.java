class Solution {
   public int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        Set<Integer> numSet = new HashSet<Integer>();
        Set<Integer> processedSet = new HashSet<Integer>();
        int maxLen = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++) {
            numSet.add(nums[i]);
        }

        for(int i=0; i<nums.length; i++) {
            if(!processedSet.contains(nums[i])) {
                int sequenceLen = 1;
                // check for front and back
                processedSet.add(nums[i]);
                boolean containsFront = numSet.contains(nums[i]-1);
                boolean containsBack = numSet.contains(nums[i]+1);
                if(containsFront || containsBack) {
                    int initialIncrement = containsBack ? 1: -1;
                    int currentIncrements = initialIncrement;
                    while(numSet.contains(nums[i]+currentIncrements)) {
                        processedSet.add(nums[i]+currentIncrements);
                        currentIncrements+=initialIncrement;
                        sequenceLen++;
                    }
                }
                // update the sequence len to max, if larger
                if(maxLen < sequenceLen) {
                    maxLen = sequenceLen;
                }
            } 
        }
        return maxLen;
        
    }
}
