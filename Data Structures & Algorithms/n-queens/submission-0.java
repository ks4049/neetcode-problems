class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> resultList = new ArrayList<>();
      	Set<Integer> rowSet = new HashSet<Integer>();
      	Set<Integer> colSet = new HashSet<Integer>();
      	List<int[]> positions = new ArrayList<>();
      	for(int i=0; i<n; i++) {
        	solveNQueensUtil(n, 0, i, rowSet, colSet, positions, resultList);
        }
      	return resultList;
    }
  
  	private boolean solveNQueensUtil(int n, int row, int col, Set<Integer> rowSet, Set<Integer> colSet, List<int[]> positions, List<List<String>> resultList) {
      if(row==n || col==n || rowSet.contains(row) || colSet.contains(col) || isDiagonalOf(row, col, positions)) {
        return false;
      }
      rowSet.add(row);
      colSet.add(col);
      int[] currentPos = new int[]{row, col};
      positions.add(currentPos);
      
      boolean placeNextQueen=false;
      if(row+1 < n) {
         for(int j=0; j<n; j++) {
            solveNQueensUtil(n, row+1, j, rowSet, colSet, positions, resultList);
        }
      } else {
        addCurrentPossibility(n, positions, resultList);
      }
      rowSet.remove(row);
      colSet.remove(col);
      positions.remove(currentPos);
      
      System.out.println("Row set and col set "+rowSet + " "+ colSet);
      print(positions);
      
      return placeNextQueen;
    }
  
  	private boolean isDiagonalOf(int row, int col, List<int[]> positions) {
      for(int i=0; i<positions.size(); i++) {
        if(Math.abs(row-positions.get(i)[0]) == Math.abs(col-positions.get(i)[1])) {
          return true;
        }
      }
      return false;
    }
  
  	private void addCurrentPossibility(int n, List<int[]> positions, List<List<String>> resultList) {
      String[] positionsList = new String[n];
      for(int i=0; i<positions.size(); i++) {
        int[] currentPosition = positions.get(i);
        StringBuilder strBuilder = new StringBuilder();
        for(int k=0; k<n; k++) {
          if(k == currentPosition[1]) {
            strBuilder.append('Q');
          } else {
            strBuilder.append('.');
          }
        }
        positionsList[currentPosition[0]] = strBuilder.toString();
      }
      resultList.add(Arrays.asList(positionsList));      
    }

    private void print(List<int[]> positions) {
        for(int[] position: positions) {
            System.out.println("position is "+ position[0] + "  "+ position[1]);
        }
    }
}
