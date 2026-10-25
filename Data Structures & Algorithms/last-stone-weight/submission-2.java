class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i=0; i<stones.length; i++) {
            pq.add(stones[i]); //Time: O(N log N)
        }
        while(pq.size() > 1) {
            int maxWt = pq.poll();
            int secondMaxWt = pq.poll();
            if(maxWt != secondMaxWt) {
                pq.add(maxWt-secondMaxWt);
            }
        }
        return pq.isEmpty()? 0: pq.poll();
    }
}
