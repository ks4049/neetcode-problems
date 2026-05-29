/*
 * Given an unsorted array of integers nums and an integer k, return the kth largest element in the array.

By kth largest element, we mean the kth largest element in the sorted order, not the kth distinct element.

Follow-up: Can you solve it without sorting?

[2,3,1,1,5,5,4], k = 3

Output: 4

 * 
 * */
class Solution {
    public int findKthLargest(int[] nums, int k) {
      if(k==0){
        return -1001;
      }
      PriorityQueue<Integer> kLargest = new PriorityQueue<Integer>();
      for(int i=0; i<nums.length; i++) {
        if(kLargest.size() < k) {
          kLargest.add(nums[i]); //1,2,3
        } else {
          if(nums[i] > kLargest.peek()) {
            List<Integer> tempList = new ArrayList<>();
            while(!kLargest.isEmpty() && kLargest.peek() <= nums[i]){
              tempList.add(kLargest.poll());
            }
            kLargest.add(nums[i]);
            int index=tempList.size()-1;
            while(index>=0 && kLargest.size()<k) {
              kLargest.add(tempList.get(index));
              index--;
            }
          }
        }
      }
      return kLargest.peek();
        
        
    }
}
