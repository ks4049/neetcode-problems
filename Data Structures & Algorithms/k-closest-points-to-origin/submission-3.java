/*
 * You are given an 2-D array points where points[i] = [xi, yi] represents the coordinates 
 * of a point on an X-Y axis plane. You are also given an integer k.
Return the k closest points to the origin (0, 0).

The distance between two points is defined as the Euclidean distance (sqrt((x1 - x2)^2 + (y1 - y2)^2)).

You may return the answer in any order.


 * 
 * */
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        if(k==0) {
          return new int[0][0];
        }
      	PriorityQueue<PointInfo> kNearest = new PriorityQueue<PointInfo>((p1, p2) -> Double.compare(p2.distance,p1.distance));
      	for(int i=0; i<points.length; i++) {
          
          double distance = Math.sqrt(Math.pow((points[i][0]-0), 2)+Math.pow((points[i][1]-0), 2));
          if(kNearest.size() < k) {
            kNearest.add(new PointInfo(points[i], distance));
          } else {
            // reached the k elements
            if(distance < kNearest.peek().distance) {
              List<PointInfo> tempList = new ArrayList<>();
              while(!kNearest.isEmpty() && kNearest.peek().distance >= distance) {
                tempList.add(kNearest.poll());
              }
              kNearest.add(new PointInfo(points[i], distance));
              int index = tempList.size()-1;
              while(index>=0 && kNearest.size() < k) {
                kNearest.add(tempList.get(index));
                index--;
              }
            } 
            // otherwise if distance is >= the top element, then this point even farway way from kNearest neighbors
            // no need to add this
          }
        }
      	int[][] result = new int[k][2];
      	int index=0;
      	while(!kNearest.isEmpty()) {
          PointInfo current = kNearest.poll();
          result[index][0] = current.point[0];
          result[index][1] = current.point[1];
          index++;
        }
      	return result;
    }
  
  	public class PointInfo {
      int[] point;
      double distance;
      public PointInfo(int[] point, double distance) {
        this.distance = distance;
        this.point = point;
      }
    }
}
