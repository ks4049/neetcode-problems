class Solution {
    public int search(int[] nums, int target) {
        int low =0, high=nums.length-1, searchIndex = -1;
        while(low <= high) {
            int mid = (low + high)/2;
            if(nums[mid] == target) {
                searchIndex = mid;
                break;
            } else if(target < nums[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return searchIndex;
    }
}
