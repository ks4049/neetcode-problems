/*
 * You are given an array of integers stones where stones[i] represents the weight of the ith stone.

We want to run a simulation on the stones as follows:

At each step we choose the two heaviest stones, with weight x and y and smash them togethers
If x == y, both stones are destroyed
If x < y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
Continue the simulation until there is no more than one stone remaining.

Return the weight of the last remaining stone or return 0 if none remain.
 * 
 * */

class Solution {
    public int lastStoneWeight(int[] stones) {
    	int lastStoneWeight =0;
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
      for(int i=0; i<stones.length; i++) {
        pq.add(stones[i]);
      }
      while(!pq.isEmpty()) {
        // poll first two largest elements
        if(pq.size() == 1) {
          lastStoneWeight = pq.poll();
          break;
        } else {
          int firstGreatestWt = pq.poll();
          int secondGreatestWt = pq.poll();
          pq.add(firstGreatestWt-secondGreatestWt);
        }
      }
      return lastStoneWeight;
    }
}
