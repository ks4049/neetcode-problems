/*
 * 
 * 
 * Given a 2D grid grid where '1' represents land and '0' represents water, count and return the number of islands.
 * An island is formed by connecting adjacent lands horizontally or vertically and is surrounded by water. 
 * You may assume water is surrounding the grid (i.e., all the edges are water).
*/

class Solution {
    public int numIslands(char[][] grid) {
      if(grid.length==0 || grid[0].length==0) {
        return 0;
      }
      int numberOfIslands = 0;
      Set<String> visitedNodes = new HashSet<String>();
      for(int i=0; i<grid.length; i++) {
        for(int j=0; j<grid[i].length; j++) {
          String currentNode = String.format("%s,%s",i,j);
          if(grid[i][j] == '1' && !visitedNodes.contains(currentNode)) {
          	numIslandsUtil(grid, i, j, visitedNodes);
            numberOfIslands++;
          }
        }
      }
      return numberOfIslands;
    }
  	
  	private void numIslandsUtil(char[][] grid, int row, int col, Set<String> visitedNodes) {
      if(row<0 || row==grid.length || col<0 || col==grid[0].length || grid[row][col]=='0' || visitedNodes.contains(String.format("%s,%s",row, col))) {
        return;
      }
      visitedNodes.add(String.format("%s,%s", row, col));
      // visit all the four directions
      numIslandsUtil(grid, row+1, col, visitedNodes);
      numIslandsUtil(grid, row, col+1, visitedNodes);
      numIslandsUtil(grid, row-1, col, visitedNodes);
      numIslandsUtil(grid, row, col-1, visitedNodes);
    }

  
}
