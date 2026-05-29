/*
 * You are given a 2-D matrix board containing 'X' and 'O' characters.
	If a continous, four-directionally connected group of 'O's is surrounded by 'X's, 
  	it is considered to be surrounded.
	Change all surrounded regions of 'O's to 'X's and do so in-place by modifying the input board.
 * 
 * 
 * */
class Solution {
    public void solve(char[][] board) {
        // ignore obvious O's in the border
      	Set<String> visitedNodes = new HashSet<String>();
      	for(int i=0; i<board.length; i++) {
          for(int j=0; j<board[i].length; j++) {
            // if the cell is an O and is contained in the corner 
            if((i==0 || j==0 || i==board.length-1 || j==board[i].length-1) && board[i][j] == 'O' && !visitedNodes.contains(String.format("%s,%s", i, j))) {
              exploreUnsurroundedRegions(board, i, j, visitedNodes);
            }
          }
        }
      	markSurroundedRegionsAsX(board, visitedNodes);
    }
  
  	private void exploreUnsurroundedRegions(char[][] board, int row, int col, Set<String> visitedNodes) {
      if(row<0 || row==board.length || col<0 || col==board[row].length || visitedNodes.contains(String.format("%s,%s", row, col)) || board[row][col] == 'X') {
        return;
      }
      
        visitedNodes.add(String.format("%s,%s", row, col));
        exploreUnsurroundedRegions(board, row+1, col, visitedNodes);
        exploreUnsurroundedRegions(board, row-1, col, visitedNodes);
        exploreUnsurroundedRegions(board, row, col+1, visitedNodes);
        exploreUnsurroundedRegions(board, row, col-1, visitedNodes);
      
    }
  
  	private void markSurroundedRegionsAsX(char[][] board, Set<String> unexploredRegions) {
      for(int i=0; i<board.length; i++) {
        for(int j=0; j<board[i].length; j++) {
          if(!unexploredRegions.contains(String.format("%s,%s",i,j)) && board[i][j] == 'O') {
            board[i][j] = 'X';
          } 
        }
      }
    }

}
