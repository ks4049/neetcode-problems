class Solution {
    public int[] replaceElements(int[] arr) {
        int maxSoFar = -1;
        for(int i=arr.length-1; i>=0; i--) {
            int currentMax = Math.max(maxSoFar, arr[i]);
            arr[i] = maxSoFar;
            maxSoFar = currentMax;
        }
        return arr;
    }
}