class KthLargest {
    PriorityQueue<Integer> pq;
    int k;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<Integer>();
        for(int i=0; i<nums.length; i++) {
            add(nums[i]);
        }
        System.out.println("pq "+ pq);
    }
    
    public int add(int val) {
        if(pq.size() < k) {
            pq.add(val);
            System.out.println("pq "+ pq);
        } else {
            // if size >= k
            List<Integer> tempList = new ArrayList<>();
            if(val >= pq.peek()) {
                while(!pq.isEmpty() && pq.peek() <= val) {
                    tempList.add(pq.poll());
                }
                // no need to add the new value as it is least
                
                tempList.add(val);
                while(!pq.isEmpty()) {
                    tempList.add(pq.poll());
                }
                
                for(int i=tempList.size()-1; i>=(tempList.size()-k); i--) {
                    pq.add(tempList.get(i));
                }
            } else {
                // current value is minimum so no need to add
            }
        }
        return pq.peek();
        
    }
}
