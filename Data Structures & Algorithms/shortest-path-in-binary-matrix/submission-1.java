class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] != 0) {
            return -1;
        }
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        Queue<int[]> bfs = new LinkedList<>();
        int[][] positions = new int[8][2];
        positions[0]= new int[]{0,1}; positions[1]= new int[]{1, 0};
        positions[2]= new int[]{0,-1}; positions[3]= new int[]{-1, 0};
        positions[4]= new int[]{1,1}; positions[5]= new int[]{-1, -1};
        positions[6]= new int[]{1,-1}; positions[7]= new int[]{-1, 1};

        bfs.add(new int[]{0,0});
        visit[0][0] = true;
        int level =1;
        while(!bfs.isEmpty()) {
            int queueSize = bfs.size();
            for(int i=0; i<queueSize; i++) {
                int[] curr = bfs.poll();
                if(curr[0] == grid.length-1 && curr[1] == grid[0].length-1) {
                    return level;
                }

                for(int j=0; j<positions.length; j++) {
                    int[] pos = positions[j];
                    int nextRow = pos[0]+curr[0]; int nextCol = pos[1]+ curr[1];
                    if(nextRow < 0 || nextRow == grid.length || nextCol < 0 || nextCol == grid.length || visit[nextRow][nextCol] || grid[nextRow][nextCol] != 0) {
                        continue;
                    }
                    visit[nextRow][nextCol] = true;
                    bfs.add(new int[]{nextRow, nextCol});
                }
            }
            level++;
        }
        return -1;
    }
}