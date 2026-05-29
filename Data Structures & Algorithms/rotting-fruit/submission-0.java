/*
 * You are given a 2-D matrix grid. Each cell can have one of three possible values:

0 representing an empty cell
1 representing a fresh fruit
2 representing a rotten fruit
Every minute, if a fresh fruit is horizontally or vertically adjacent to a rotten fruit, 
then the fresh fruit also becomes rotten.

Return the minimum number of minutes that must elapse until there are zero fresh fruits remaining. 
If this state is impossible within the grid, return -1.
 * 
 * */

class Solution {
    public int orangesRotting(int[][] grid) {
        if(grid.length==0 || grid[0].length == 0) {
          return -1;
        }
      	int[][] minDistance = new int[grid.length][grid[0].length];
      	fillMaxValueForFresh(minDistance, grid, Integer.MAX_VALUE);
      	for(int i=0; i<grid.length; i++) {
          for(int j=0; j<grid[i].length; j++) {
            if(grid[i][j] == 2) { // if rotten then explore all the fresh fruits
              orangeRottingUtil(grid, i, j, minDistance);
            }
          }
        }
      	int maxValue = computeMaxMinutes(minDistance);
      	return maxValue == Integer.MAX_VALUE? -1: maxValue;
    }
  
  private void fillMaxValueForFresh(int[][] minDistance, int[][] grid, int value) {
    for(int i=0; i<grid.length; i++) {
      for(int j=0; j<grid[i].length; j++){
        if(grid[i][j] == 1) {
          minDistance[i][j] = value;
        }
      }
    }
  }
  
  private int computeMaxMinutes(int[][] minDistance) {
    int maxValue = Integer.MIN_VALUE;
    for(int i=0; i<minDistance.length; i++) {
      for(int j=0; j<minDistance[i].length; j++){
        if(minDistance[i][j] > maxValue) {
          maxValue = minDistance[i][j];
        }
      }
    }
    return maxValue;
  }
  	
  	private void orangeRottingUtil(int[][] grid, int row, int col, int[][] minDistance) {
      Set<String> visitingNodes = new HashSet<String>();
      Queue<String> traversalQueue = new LinkedList<String>();
      String currentRottenNode = String.format("%s,%s",row, col);
      visitingNodes.add(currentRottenNode);
      traversalQueue.add(currentRottenNode);
      
      while(!traversalQueue.isEmpty()) {
      	String currentNode = traversalQueue.poll();
        String[] positionStr = currentNode.split(",");
        int rowIdx = Integer.parseInt(positionStr[0]), colIdx = Integer.parseInt(positionStr[1]);
        // bottom
        String bottomNode = String.format("%s,%s", rowIdx+1, colIdx);
        if(!visitingNodes.contains(bottomNode) && rowIdx+1<grid.length && grid[rowIdx+1][colIdx] == 1) {
          // fill the minDistance array accordingly(all fresh oranges to be infinity)
          minDistance[rowIdx+1][colIdx] = Math.min(minDistance[rowIdx+1][colIdx], minDistance[rowIdx][colIdx]+1);
          visitingNodes.add(bottomNode);
          traversalQueue.add(bottomNode);
        }
        // right
        String rightNode = String.format("%s,%s", rowIdx, colIdx+1);
        if(!visitingNodes.contains(rightNode) && colIdx+1<grid[rowIdx].length && grid[rowIdx][colIdx+1] == 1) {
          minDistance[rowIdx][colIdx+1] =  Math.min(minDistance[rowIdx][colIdx+1], minDistance[rowIdx][colIdx]+1);
          visitingNodes.add(rightNode);
          traversalQueue.add(rightNode);
        }
        // top
        String topNode = String.format("%s,%s", rowIdx-1, colIdx);
        if(!visitingNodes.contains(topNode) && rowIdx-1>= 0 && grid[rowIdx-1][colIdx] == 1) {
          // fill the minDistance array accordingly(all fresh oranges to be infinity)
          minDistance[rowIdx-1][colIdx] = Math.min(minDistance[rowIdx-1][colIdx], minDistance[rowIdx][colIdx]+1);
          visitingNodes.add(topNode);
          traversalQueue.add(topNode);
        }
        // left
         String leftNode = String.format("%s,%s", rowIdx, colIdx-1);
        if(!visitingNodes.contains(leftNode) && colIdx-1 >=0 && grid[rowIdx][colIdx-1] == 1) {
          minDistance[rowIdx][colIdx-1] =  Math.min(minDistance[rowIdx][colIdx-1], minDistance[rowIdx][colIdx]+1);
          visitingNodes.add(leftNode);
          traversalQueue.add(leftNode);
        }
      }
    }
  
}
