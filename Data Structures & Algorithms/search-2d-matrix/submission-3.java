class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
      boolean isFound = false;
      
      int rowLen=matrix.length, colLen=matrix[0].length, low=0, high=rowLen*colLen-1, mid;
      
      while(low<=high) {
        mid = (low+high)/2;
        int rowIndex = mid/colLen;
        int colIndex = mid - rowIndex*colLen;
        if(matrix[rowIndex][colIndex] < target) {
          low = mid+1;
        } else if(matrix[rowIndex][colIndex] > target) {
          high = mid-1;
        } else {
          isFound = true;
          break;
        }
      }
      return isFound;    
        
    }
}
