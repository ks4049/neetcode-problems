class Solution {
    public int swimInWater(int[][] grid) {
        /*
        look for the smallest element surrounding the current water level
        */
        PriorityQueue<Element> queue = new PriorityQueue<Element>((e1,e2) -> Integer.compare(e1.maxSoFar, e2.maxSoFar));
        queue.add(new Element(grid[0][0], 0, 0, grid[0][0]));
        Set<String> visitedPositions = new HashSet<String>();
        int lastRow = grid.length-1, lastCol = grid[lastRow].length, rowLen = grid.length, colLen = grid[0].length, maxSoFar=0;
        while(!queue.isEmpty()) {
            Element current = queue.poll();
            int currentRow = current.row, currentCol = current.col;
            //if current node's position is equal to bottom corner
            if(currentRow == rowLen-1 && currentCol == colLen-1) {
                maxSoFar = current.maxSoFar;
                break;
            }
            // explore the neighbors of the current node all four directions
            // bottom (2)
            if(currentRow+1 < rowLen && !visitedPositions.contains(String.format("%s,%s", currentRow+1, currentCol))) {
                Element bottom = new Element(grid[currentRow+1][currentCol], currentRow+1, currentCol, Math.max(current.maxSoFar, grid[currentRow+1][currentCol]));
                queue.add(bottom);
            }
            // top
            if(currentRow-1 >=0  && !visitedPositions.contains(String.format("%s,%s", currentRow-1, currentCol))) {
                Element top = new Element(grid[currentRow-1][currentCol], currentRow-1, currentCol, Math.max(current.maxSoFar, grid[currentRow-1][currentCol]));
                queue.add(top);
            }
            // right (1)
            if(currentCol+1 < colLen  && !visitedPositions.contains(String.format("%s,%s", currentRow, currentCol+1))) {
                Element right = new Element(grid[currentRow][currentCol+1], currentRow, currentCol+1, Math.max(current.maxSoFar, grid[currentRow][currentCol+1]));
                queue.add(right);
            }
            // left
            if(currentCol-1 >= 0  && !visitedPositions.contains(String.format("%s,%s", currentRow, currentCol-1))) {
                Element left = new Element(grid[currentRow][currentCol-1], currentRow, currentCol-1, Math.max(current.maxSoFar, grid[currentRow][currentCol-1]));
                queue.add(left);
            }
            visitedPositions.add(String.format("%s,%s", currentRow, currentCol));
        }
        return maxSoFar;
    }

    class Element {
        int element;
        int row;
        int col;
        int maxSoFar;
        public Element(int element, int row, int col, int maxSoFar) {
            this.element = element;
            this.row = row;
            this.col = col;
            this.maxSoFar = maxSoFar;
        }
    }
}
