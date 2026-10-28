class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[][] positions = new int[4][2];
        positions[0] = new int[]{-1,0};
        positions[1] = new int[]{1,0};
        positions[2] = new int[]{0,-1};
        positions[3] = new int[]{0,1};
        int originalPixelValue = image[sr][sc];
        if(originalPixelValue == color) {
            return image;
        }
        Queue<int[]> bfs = new LinkedList<>();
        bfs.add(new int[]{sr,sc});
        int rowLen = image.length, colLen = image[0].length;

        while(!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            image[curr[0]][curr[1]] = color;

            for(int i=0; i<positions.length; i++) {
                int nextRow = curr[0] + positions[i][0];
                int nextCol = curr[1] + positions[i][1];
                if(nextRow< 0 || nextRow==rowLen || nextCol==colLen|| nextCol<0 || image[nextRow][nextCol] != originalPixelValue) {
                    continue;
                }
                bfs.add(new int[]{nextRow, nextCol});
            }
        }
        return image;
    }
}