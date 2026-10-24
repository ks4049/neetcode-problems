class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // look at the matrix as a flatten 1D matrix
        int low=0, high = (matrix.length*(matrix[0].length))-1;
        int rowLen = matrix.length, colLen = matrix[0].length;
        boolean found=false;
        while(low<=high) {
            int mid = (low+high)/2;
            int row = mid/colLen, col = mid%colLen;
            if(target == matrix[row][col]) {
                found=true;
                break;
            } else if(target > matrix[row][col]) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return found;

    }
}
