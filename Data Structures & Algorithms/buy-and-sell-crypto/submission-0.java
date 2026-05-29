class Solution {
    public int maxProfit(int[] prices) {
        int buyPrice = Integer.MAX_VALUE, salePrice = Integer.MIN_VALUE, maxProfit = Integer.MIN_VALUE;

        for(int i=0; i<prices.length; i++) {
            if(buyPrice > prices[i]) {
                buyPrice = prices[i];
                salePrice = prices[i];
            } else if(salePrice < prices[i]){
                salePrice = prices[i];
            }
            if(maxProfit < salePrice-buyPrice) {
                maxProfit = salePrice-buyPrice;
            }
        }
        return maxProfit;
        
    }
}
