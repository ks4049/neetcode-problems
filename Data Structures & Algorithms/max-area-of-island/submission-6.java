class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        int[][] positions = new int[4][2];
        positions[0] = new int[]{0,1};
        positions[1] = new int[]{1, 0}; 
        positions[2] = new int[]{0, -1}; 
        positions[3] = new int[]{-1, 0};
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(grid[i][j] == 1) {
                    visit[i][j] = true;
                    int area = traverseIsland(grid, visit, positions, i, j);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea; 
    }


    private int traverseIsland(int[][] grid, boolean[][] visit, int[][] positions, int row, int col) {
        grid[row][col] = 2;
        
        int area =1;
        for(int i=0; i<positions.length; i++) {
            int nextRow = positions[i][0]+row;
            int nextCol  = positions[i][1]+col;
            if(nextRow <  0 || nextRow==grid.length || nextCol<0 || nextCol==grid[0].length || visit[nextRow][nextCol] || grid[nextRow][nextCol] != 1) {
                continue;
            }
            
            visit[nextRow][nextCol] = true;
            area = area +traverseIsland(grid, visit, positions, nextRow, nextCol);
        }
        return area;
    } 

}
