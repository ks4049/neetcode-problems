class Solution {
     public int minEatingSpeed(int[] piles, int h) {
      int lowRate=1, highRate=Integer.MIN_VALUE, midRate, minimumEatingSpeed;
      for(int i=0; i<piles.length; i++) {
        if(piles[i] > highRate) {
          highRate = piles[i];
        }
      }
      // after finding the min and max, identify the minimum rate that is less than h
      minimumEatingSpeed = highRate;
      while(lowRate<=highRate) {
        midRate = (lowRate+highRate)/2;
        int hoursTaken = computeHoursForRate(piles, midRate);
        System.out.println("number of hours taken for rate "+ midRate + " is "+ hoursTaken);
        if(hoursTaken <= h) {
          // record the time and look for larger numnber of hours, minimize the rate
          if(minimumEatingSpeed > midRate){
            minimumEatingSpeed = midRate;
          }
          highRate = midRate-1;
        } else {
          // look for lesser number of hours, maximize the rate
          lowRate = midRate+1;
        }
      }
      return minimumEatingSpeed;
    }
  
  	private int computeHoursForRate(int[] piles, int rate) {
      int totalHours=0;
      for(int i=0; i<piles.length; i++) {
        totalHours+= (piles[i]/rate)+ (piles[i]%rate == 0? 0:1);
      }
      return totalHours;
    }
}
