class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
        int minLen = Math.min(nums.length, k);
        for(int i=0; i<minLen; i++) {
            pq.add(nums[i]);
        }
        if(nums.length < k) {
            return new int[]{pq.peek()};
        }
        int clearedIndex = -1;
        int[] result = new int[nums.length-k+1];
        result[0] = pq.peek();
        //System.out.println("At 0 " + 0 + " pq is "+pq);

        for(int i=k; i<nums.length; i++) {
            
            if(pq.peek() < nums[i]) {
                // removes all elements from queue and adds the new max element
                clearedIndex = i-1;
                pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
            } else {
                // max remains the same but is max== i-kth element
                if(i-k > clearedIndex) {
                    // then remove nums[i-k] from heap
                    if(nums[i-k] == pq.peek()) {
                        pq.poll();
                    } else {
                        List<Integer> tempList = new ArrayList<Integer>();
                        boolean removed = false;
                        while(!pq.isEmpty()) {
                            if(!removed && pq.peek() == nums[i-k]) {
                                removed = true;
                                pq.poll();
                            } else {
                                tempList.add(pq.poll());
                            }
                        }
                        for(int j=0; j<tempList.size(); j++) {
                            pq.add(tempList.get(j));
                        }
                    }
                }
            }
            pq.add(nums[i]);
            result[i-k+1] = pq.peek();
            //System.out.println("At i " + i + " pq is "+pq);
        }
        return result;
        
    }
}
