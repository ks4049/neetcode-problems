class NumMatrix {
    int[][] rowSum;

    public NumMatrix(int[][] matrix) {
        // compute the row sum for every row
        // using the row sum, identify the subarray sum for each of the considered row
        rowSum = computeRowSum(matrix);
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int rowLen = rowSum.length, colLen = rowSum[0].length;
        int regionSum = 0;
        for(int i=row1; i<=row2; i++) {
            int subRowSum = rowSum[i][col2] - (col1==0? 0: rowSum[i][col1-1]); 
            regionSum+=subRowSum;
        }
        return regionSum;
    }

    int[][] computeRowSum(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for(int i=0; i<matrix.length; i++) {
            int rowSum=0;
            for(int j=0; j<matrix[i].length; j++) {
                rowSum+=matrix[i][j];
                result[i][j] = rowSum;
            }
        }
        return result;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */