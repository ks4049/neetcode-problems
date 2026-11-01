class Solution {
    public int maxTurbulenceSize(int[] arr) {
        if(arr.length <= 1) {
            return arr.length;
        }
        boolean prevIncreasing=false, prevDecreasing=false;
        int len = arr.length, left=0, maxLen =1; 
        for(int i=1; i<len; i++) {
            if((prevIncreasing && arr[i] >= arr[i-1]) || (prevDecreasing && arr[i] <= arr[i-1])) {
                // shrink the window to i-1 or i
                if(arr[i] == arr[i-1]) {
                    left = i;
                    prevIncreasing=false;prevDecreasing=false;
                } else {
                    left = i-1;
                }
            } else {
                if(arr[i] > arr[i-1]) {
                    prevIncreasing = true;
                    prevDecreasing = false;
                } else if(arr[i] < arr[i-1]){
                    prevIncreasing = false;
                    prevDecreasing = true;
                } else {
                    left = i;
                }
            }
            maxLen = Math.max(i-left+1, maxLen);
        }
        return maxLen;
        
    }
}