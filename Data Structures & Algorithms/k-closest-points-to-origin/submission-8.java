class Solution {
    /*
    * Option1: Choose a pivot and then trigger quick sort: O(N logN)
    * Option2: Use a priority queue to store the K elements in it.
    
    */
    class Point {
        double distance;
        int[] point;
        public Point(double distance, int[] point) {
            this.distance = distance;
            this.point = point;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        Comparator<Point> pointComparator = Comparator.comparingDouble(p -> p.distance);

        PriorityQueue<Point> pq = new PriorityQueue<Point>(Collections.reverseOrder(pointComparator)); // max heap(descending)
        for(int i=0; i<points.length; i++) {
            Point curr = new Point(computeDistance(points[i]), points[i]);
            if(pq.size() < k) {
                pq.add(curr);
            } else if(curr.distance < pq.peek().distance) {
                pq.remove();
                pq.add(curr);
            }
        }
        int[][] kClosest = new int[k][2]; 
        int i=0;
        while(!pq.isEmpty()) {
            kClosest[i++] = pq.remove().point;
        }
        return kClosest;

    }

    private double computeDistance(int[] point) {
        return Math.sqrt((point[0]*point[0]) + (point[1]*point[1]));
        
    }

  


}
