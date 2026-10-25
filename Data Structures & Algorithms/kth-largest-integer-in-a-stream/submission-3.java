class KthLargest {
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for(int i=0; i<nums.length; i++) {
            if(minHeap.size() < k) {
                minHeap.add(nums[i]);
            } else {
                if(nums[i] > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.add(nums[i]);
                }
            }
        }
    }
    
    public int add(int val) {
        if(minHeap.size() < k) {
            minHeap.add(val);
        } else {
            if(val > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(val);
            }
        }
        return minHeap.isEmpty()? -1: minHeap.peek();
    }
}
