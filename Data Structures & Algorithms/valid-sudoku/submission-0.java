class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0; i<9; i++) {
            if(!isValidRowOrCol(board, i, true)  || !isValidRowOrCol(board, i, false)) {
                return false;
            }
        }
        //System.out.println("Validated rows and columns");
        // validate sub boxes
        for(int i=0; i<9; i+=3) {
            for(int j=0; j<9; j+=3) {
                if(!isValidSubBox(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isValidRowOrCol(char[][] board, int index, boolean isRow) {
        Set<Character> visited = new HashSet<>();
        if(isRow) {
            for(int i=0; i<9; i++) {
                if(board[index][i] != '.') {
                     if(visited.contains(board[index][i])) {
                        return false; // contains duplicate
                    }
                    visited.add(board[index][i]);
                }
            }
        } else {
            // validate column
             for(int i=0; i<9; i++) {
                if(board[i][index] != '.') {
                    if(visited.contains(board[i][index])) {
                        return false; // contains duplicate
                    }
                    visited.add(board[i][index]);
                }
            }
        }
        
        return true;
    }

    boolean isValidSubBox(char[][] board, int row, int col) {
        Set<Character> visited = new HashSet<>();
        //System.out.println("Validating sub box for indices "+ row + ", "+ col);
        for(int i=row; i<row+3; i++) {
            for(int j=col; j<col+3; j++) {
                if(board[i][j] != '.') {
                    if(visited.contains(board[i][j])) {
                        return false;
                    }
                    visited.add(board[i][j]);
                }
            }
        }
        return true;
    }
}
