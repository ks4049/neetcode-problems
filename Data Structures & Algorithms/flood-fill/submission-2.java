class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalPixelValue = image[sr][sc];
        if(originalPixelValue == color) {
            return image;
        }
        int[][] positions = new int[4][2];
        positions[0] = new int[]{-1,0};
        positions[1] = new int[]{1,0};
        positions[2] = new int[]{0,-1};
        positions[3] = new int[]{0,1};
        Set<String> visit = new HashSet<>();
        visit.add(String.format("%s,%s", sr,sc));
        return dfsTraversal(image, positions, originalPixelValue, color, sr, sc, visit);
        //return bfsTraversal(image, int[][] positions, originalPixelValue, color, sr, sc);   
    }

    private int[][] dfsTraversal(int[][] image, int[][] positions, int originalPixelValue, int color, int row, int col, Set<String> visit) {
        if(row<0 || row == image.length || col < 0 || col == image[0].length)  {
            return image;
        }
        image[row][col] = color;
        
        for(int i=0; i<positions.length; i++) {
            int nextRow = row+positions[i][0], nextCol = col+positions[i][1];
            if(nextRow<0 || nextRow == image.length || nextCol < 0 || nextCol == image[0].length || image[nextRow][nextCol] != originalPixelValue || visit.contains(String.format("%s,%s", nextRow, nextCol)))  {
                continue;
            }
            visit.add(String.format("%s,%s", nextRow, nextCol));
            dfsTraversal(image, positions, originalPixelValue, color, nextRow, nextCol, visit);
        }
        return image;
    }

    private int[][] bfsTraversal(int[][] image, int[][] positions, int originalPixelValue, int color, int sr, int sc) {
       
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