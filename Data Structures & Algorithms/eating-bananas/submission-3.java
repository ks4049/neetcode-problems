class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low=1, high = Integer.MIN_VALUE;
        // compute the max value
        for(int i=0; i<piles.length; i++) {
            if(high < piles[i]) {
                high = piles[i];
            }
        }
        int minSpeed = Integer.MAX_VALUE;
        while(low<=high){
            int mid = low + ((high - low)/2);
            int canComplete = canCompleteBananas(mid, h, piles);
            if(canComplete == 1 || canComplete == 0) {
                // try to minimize the speed to find min speed
                minSpeed = mid;
                high = mid-1;
            } else if(canComplete == -1) {
                // taking more hours to complete, increase the speed
                low = mid+1;
            }
        }
        return minSpeed; 
    }

    private int canCompleteBananas(int speed, int hours, int[] piles) {
        System.out.println("Current speed "+ speed);
        long actualHrs = 0;
        for(int i=0; i<piles.length; i++) {
            actualHrs+= (piles[i]/speed) + (piles[i]%speed == 0? 0: 1);
        }
        return actualHrs==hours? 0: (actualHrs>hours)? -1: 1;

    }
}
