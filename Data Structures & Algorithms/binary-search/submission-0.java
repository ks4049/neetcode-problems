class Solution {
    public int search(int[] nums, int target) {
        int resultIndex = -1;
        int low=0, high=nums.length-1, mid;
        while(low<=high) {
            mid = (low+high)/2;
            if(nums[mid] < target) {
                low = mid+1;
            } else if(nums[mid] > target) {
                high = mid-1;
            } else {
                resultIndex = mid;
                break;
            }
        }
        return resultIndex;
    }
}
