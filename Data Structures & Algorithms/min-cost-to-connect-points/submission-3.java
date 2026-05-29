class Solution {
    public int minCostConnectPoints(int[][] points) {
        if(points.length <=1 ){
            return 0;
        }
        PriorityQueue<Distance> edgeDistances = new PriorityQueue<Distance>((d1,d2) -> Integer.compare(d1.distance, d2.distance));
        edgeDistances.add(new Distance(points[0], 0));
        Set<int[]> visitedNodes = new HashSet<int[]>();
        int minCost=0;

        while(!edgeDistances.isEmpty() && visitedNodes.size()< points.length) {
            Distance currentNode = edgeDistances.poll();
            if(visitedNodes.contains(currentNode.point1)) {
                continue;
            }
            minCost+=currentNode.distance;
            visitedNodes.add(currentNode.point1);
            for(int i=0; i<points.length; i++) {
                if(visitedNodes.contains(points[i])) {
                    continue;
                }
                int distance = Math.abs(points[i][0]-currentNode.point1[0]) + Math.abs(points[i][1]-currentNode.point1[1]);
                edgeDistances.add(new Distance(points[i], distance));
            }
        }
        return minCost;
        
    }

    class Distance {
        int[] point1; 
        int distance;
        public Distance(int[] point1, int distance) {
            this.point1 = point1;
            this.distance = distance;
        }
    }
}
