class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] productResult = new int[nums.length];
        productResult[0]=1;
        for(int i=1; i<nums.length; i++) {
            // left product
            productResult[i] = productResult[i-1]*nums[i-1];
        }

        int rightProductSoFar=1;
        for(int i=nums.length-2; i>=0; i--) {
            //right product
            rightProductSoFar = rightProductSoFar*nums[i+1];
            productResult[i] = productResult[i]*rightProductSoFar;
        }
        return productResult;
        
    }
}  
