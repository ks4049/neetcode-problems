class Solution {
    public int search(int[] nums, int target) {
      int low=0, high=nums.length-1, mid, minimumSoFar=-1001, foundIndex =-1;
      
      
      while(low <=high) {
        mid=(low+high)/2;
        if(nums[mid] == target) {
            foundIndex = mid;
            break;
        } else if(nums[low] < nums[mid]) {
            if(target >=nums[low] && target<nums[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        } else if(nums[high] > nums[mid]) {
            if(target > nums[mid] && target <= nums[high]) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        } else { // descending order
            if(target < nums[mid] && target >=nums[high]) {
                low = mid+1;
            } else {
                high = low-1;
            }
        }
      }
        return foundIndex;
    }
}
