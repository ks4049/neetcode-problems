/*
 * You are given a 
m×n 2D grid initialized with these three possible values:

-1 - A water cell that can not be traversed.
0 - A treasure chest.
INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest then the value should remain INF.

Assume the grid can only be traversed up, down, left, or right.

Modify the grid in-place.
 * 
 * */
class Solution {
    public void islandsAndTreasure(int[][] grid) {
			if(grid.length==0 || grid[0].length==0) {
        return;
      }
      for(int i=0; i<grid.length; i++) {
        for(int j=0; j<grid[i].length; j++) {
          if(grid[i][j] == 0) {
            	visitAllNeighborsOfTreasury(grid, i, j);
          }
        }
      }
    }
  	private void visitAllNeighborsOfTreasury(int[][] grid, int row, int col) {
      Set<String> visitingNodes = new HashSet<String>();
      Queue<String> traversalQueue = new LinkedList<String>();
      traversalQueue.add(String.format("%s,%s", row, col));
      while(!traversalQueue.isEmpty()) {
        String currentNode = traversalQueue.poll();
        String[] positionStr = currentNode.split(",");
        int rowIdx = Integer.parseInt(positionStr[0]), colIdx = Integer.parseInt(positionStr[1]);
        // look for the neighbors of the current node
        //bottom
        String bottomNode = String.format("%s,%s", rowIdx+1, colIdx);
        if(rowIdx+1 < grid.length && grid[rowIdx+1][colIdx] > 0 && !visitingNodes.contains(bottomNode)) {
          grid[rowIdx+1][colIdx] = Math.min(grid[rowIdx+1][colIdx], grid[rowIdx][colIdx]+1);
          visitingNodes.add(bottomNode);
          traversalQueue.add(bottomNode);
        }
        //top
        String topNode = String.format("%s,%s", rowIdx-1, colIdx);
        if(rowIdx-1 >=0 && grid[rowIdx-1][colIdx] > 0 && !visitingNodes.contains(topNode)) {
          grid[rowIdx-1][colIdx] = Math.min(grid[rowIdx-1][colIdx], grid[rowIdx][colIdx]+1);
          visitingNodes.add(topNode);
          traversalQueue.add(topNode);
        }
        //right
        String rightNode = String.format("%s,%s", rowIdx, colIdx+1);
        if(colIdx+1 < grid[rowIdx].length && grid[rowIdx][colIdx+1] > 0 && !visitingNodes.contains(rightNode)) {
          grid[rowIdx][colIdx+1] = Math.min(grid[rowIdx][colIdx+1], grid[rowIdx][colIdx]+1);
          visitingNodes.add(rightNode);
          traversalQueue.add(rightNode);
        }
        //left
        String leftNode = String.format("%s,%s", rowIdx, colIdx-1);
        if(colIdx-1 >= 0 && grid[rowIdx][colIdx-1] > 0 && !visitingNodes.contains(leftNode)) {
          grid[rowIdx][colIdx-1] = Math.min(grid[rowIdx][colIdx-1], grid[rowIdx][colIdx]+1);
          visitingNodes.add(leftNode);
          traversalQueue.add(leftNode);
        }
      }
    }
}
