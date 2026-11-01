class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int left=0,right,i=0,len=arr.length, curSum=0, subArrayCount=0;

        while(i<len) {
            right = i;
            if(right-left+1 > k) {
                curSum = curSum - arr[left];
                left = left+1;
            }
            curSum = curSum + arr[right];
            if(right-left+1 == k && (curSum/k) >= threshold) {
                subArrayCount++;
            }
            i++;
        }
        return subArrayCount;
    }
}