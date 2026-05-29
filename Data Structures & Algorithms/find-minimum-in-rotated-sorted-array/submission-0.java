class Solution {
    public int findMin(int[] nums) {
      int low=0, high=nums.length-1, mid, minimumSoFar=1001;
      
      while(low<=high) {
        mid = (low+high)/2;
        if(nums[mid]<=nums[high]) {
          //record the min so far and move forward
          if(minimumSoFar > nums[mid]) {
            minimumSoFar = nums[mid];
          }
          high = mid-1;
        } else { // rotated part
          low = mid+1;
        }        
      }
      return minimumSoFar;
        
    }
}
