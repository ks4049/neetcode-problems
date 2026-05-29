class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        for(int i=0; i<numbers.length; i++) {
            int otherPair = target-numbers[i];
            int searchIndex = binarySearch(numbers, i+1, numbers.length-1, otherPair);
            if(searchIndex != -1) {
                result[0] = i+1;
                result[1] = searchIndex+1;
                break;
            }
        }
        return result;
    }

    private int binarySearch(int[] numbers, int low, int high, int target) {
        int searchIndex = -1, mid;
        while(low<=high) {
            mid = (low+high)/2;
            if(numbers[mid] == target) {
                searchIndex = mid;
                break;
            } else if(numbers[mid] < target) {
                low=mid+1;
            } else {
                high = mid-1;
            }
        }
        return searchIndex;
    }
}
