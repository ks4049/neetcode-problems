class Solution {

    class Point{
        int[] point;
        double distance;
        
        public Point(int[] point, double distance) {
            this.point = point;
            this.distance = distance;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        Point[] distances = computeDistances(points);
        quickSort(distances, 0, points.length-1);
        int[][] result = new int[k][2];
        for(int i=0; i<k; i++) {
            result[i] = distances[i].point;
        }
        return result;
    }

    private Point[] computeDistances(int[][] points) {
        Point[] distances = new Point[points.length];
        for(int i=0; i<points.length; i++) {
            double dist = Math.sqrt((points[i][0]*points[i][0]) + (points[i][1]*points[i][1]));
            distances[i] = new Point(points[i], dist);
        }
        return distances;
    }

    private void quickSort(Point[] distances, int start, int pivotIndex) {
        if(pivotIndex-start <=0) {
            return;
        }

        int left=start;
        double pivotEle = distances[pivotIndex].distance;
        for(int i=start; i<pivotIndex; i++) {
            if(distances[i].distance < pivotEle) {
                // swap with the left pointer
                Point temp = distances[left];
                distances[left] = distances[i];
                distances[i] = temp;
                left++;
            }
        }
        // swap pivotIndex with left
        Point temp1 = distances[pivotIndex];
        distances[pivotIndex] = distances[left];
        distances[left] = temp1;

        quickSort(distances, start, left-1);
        quickSort(distances, left+1, pivotIndex);

    }


}
