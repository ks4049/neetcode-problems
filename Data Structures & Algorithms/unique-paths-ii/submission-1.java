class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        int[][] pathCount = new int[obstacleGrid.length][obstacleGrid[0].length];
        int rowLen = obstacleGrid.length, colLen = obstacleGrid[0].length;
        // target is an obstacle
        if(obstacleGrid[rowLen-1][colLen-1] == 1) {
            return 0;
        }
        for(int i=rowLen-1; i>=0; i--) {
            for(int j=colLen-1; j>=0; j--) {
                if(i==rowLen-1 && j==colLen-1) {
                    pathCount[i][j]=1;
                } else if(i==rowLen-1) {
                    // last row
                    pathCount[i][j] = obstacleGrid[i][j]==1? 0: pathCount[i][j+1];
                } else if(j==colLen-1) {
                    //last column
                    pathCount[i][j] = obstacleGrid[i][j]==1? 0: pathCount[i+1][j];
                } else {
                    pathCount[i][j] = obstacleGrid[i][j]==1? 0: pathCount[i+1][j] + pathCount[i][j+1];
                }
            }
        }
        return pathCount[0][0];
        
    }
}