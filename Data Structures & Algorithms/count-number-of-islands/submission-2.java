class Solution {
    public int numIslands(char[][] grid) {
        Set<String> visit = new HashSet<>();
        int islandCount = 0;
        int[][] positions = new int[4][2];
        positions[0] = new int[]{0,1};
        positions[1] = new int[]{1, 0}; 
        positions[2] = new int[]{0, -1}; 
        positions[3] = new int[]{-1, 0};
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    visit.add(String.format("%s,%s", i, j));
                    traverseIsland(grid, visit, positions, i, j);
                    islandCount++;
                }
            }
        }
        return islandCount; 
    }


    private void traverseIsland(char[][] grid, Set<String> visit, int[][] positions, int row, int col) {
        grid[row][col] = '*';

        for(int i=0; i<positions.length; i++) {
            int nextRow = positions[i][0]+row;
            int nextCol  = positions[i][1]+col;
            if(nextRow <  0 || nextRow==grid.length || nextCol<0 || nextCol==grid[0].length || visit.contains(String.format("%s,%s", nextRow, nextCol)) || grid[nextRow][nextCol] != '1') {
                continue;
            }
            visit.add(String.format("%s,%s", nextRow, nextCol));
            traverseIsland(grid, visit, positions, nextRow, nextCol);
        }

    }
}
