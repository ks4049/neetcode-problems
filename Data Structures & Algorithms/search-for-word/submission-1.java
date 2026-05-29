/*Given a 2-D grid of characters board and a string word, 
 * return true if the word is present in the grid, otherwise return false.

For the word to be present it must be possible to form it with a path in the board 
with horizontally or vertically neighboring cells. The same cell may not be used more than once in a word.
*/

class Solution {
  public boolean exist(char[][] board, String word) {
    if(word.length()==0) {
      return true;
    }
    for(int i=0; i<board.length; i++) {
      for(int j=0; j<board[i].length; j++) {
        if(word.charAt(0) == board[i][j]) {
          	Set<String> positions = new HashSet<String>();
          	boolean wordExists = checkWordExistenceUtil(board, i, j, positions, word, 0);
          	if(wordExists) {
              return true;
            }
        }
      }
    }
    return false;
  }
  
  private boolean checkWordExistenceUtil(char[][] board, int row, int col, Set<String> positions, String word, int currentWordIndex) {
    if(currentWordIndex == word.length()) {
        return true;
    }
    if(row < 0 || row == board.length || col < 0 || col==board[row].length) {
      return false;
    }
    if(positions.contains(String.format("%s,%s", row, col))){ 
      return false;
    }
    if(board[row][col] != word.charAt(currentWordIndex)) {
        return false;
    }
    positions.add(String.format("%s,%s", row, col));
    
    boolean checkWordExistence = checkWordExistenceUtil(board, row+1, col, positions, word, currentWordIndex+1) 
    || checkWordExistenceUtil(board, row, col+1, positions, word, currentWordIndex+1) 
    || checkWordExistenceUtil(board, row-1, col, positions, word, currentWordIndex+1) 
    || checkWordExistenceUtil(board, row, col-1, positions, word, currentWordIndex+1);
    positions.remove(String.format("%s,%s", row,col));
    return checkWordExistence;
  } 
    
}
