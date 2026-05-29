class Solution {
    public int maxArea(int[] heights) {
        int maxArea = Integer.MIN_VALUE;
        int low =0, high=heights.length-1;
        while(low < high) {
            int area = Math.min(heights[low], heights[high])*(high-low);
            if(maxArea < area) {
                maxArea = area;
            }
            if(heights[low] < heights[high]) {
                low++;
            } else {
                high--;
            }
        }
        return maxArea;
    }
}
