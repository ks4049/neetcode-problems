class Solution {
    public int jump(int[] nums) {
        int minSteps=0, start=0,end=0;

        for(int i=start; i<nums.length-1;) {
            int farthestIndex=-1;
            if(end >= nums.length-1){
                break;
            }
            boolean foundFartherIndex=false;
            while(i<=end && i<nums.length) {
                farthestIndex = Math.max(farthestIndex, i+nums[i]);
                foundFartherIndex=true;
                i++;
            }
            
            minSteps++;
            i=end+1;
            end = farthestIndex;
        }
        return minSteps;
        
    }
}
