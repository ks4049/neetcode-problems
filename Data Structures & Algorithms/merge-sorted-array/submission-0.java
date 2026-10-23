class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // make a copy of nums1 of length m
        int[] firstArrayCopy = copyArray(nums1, m);
        int k=0, i=0, j=0;

        for(i=0, j=0; i<m&&j<n;) {
            if(firstArrayCopy[i] <= nums2[j]) {
                nums1[k++] = firstArrayCopy[i++];
            } else {
                nums1[k++] = nums2[j++];
            }
        }
        while(i<m) {
            nums1[k++] = firstArrayCopy[i++];
        }
        while(j<n) {
            nums1[k++] = nums2[j++];
        }
    }

    int[] copyArray(int[] num, int len) {
        int[] copiedArray = new int[len];
        for(int i=0; i<len; i++) {
            copiedArray[i] = num[i];
        }
        return copiedArray;
    }
}