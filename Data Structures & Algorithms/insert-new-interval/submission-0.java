/*
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [start_i, end_i] represents the start and the end time of the ith interval. intervals is initially sorted in ascending order by start_i.

You are given another interval newInterval = [start, end].

Insert newInterval into intervals such that intervals is still sorted in ascending order by start_i and also intervals still does not have any overlapping intervals. You may merge the overlapping intervals if needed.

Return intervals after adding newInterval.

Note: Intervals are non-overlapping if they have no common point. For example, [1,2] and [3,4] are non-overlapping, but [1,2] and [2,3] are overlapping.
*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
      boolean addNewInterval = false;
      int[] interval = newInterval;
      	for(int i=0; i<intervals.length; i++) {
          if(!isOverlapping(interval, intervals[i])){
            // comes before the intervals[i]
            if(!addNewInterval && interval[1] < intervals[i][0]) {
              result.add(interval);
              addNewInterval = true;
            }
            result.add(intervals[i]);
          } else {
            // found the merge point
            int newMinStartTime = Math.min(interval[0], intervals[i][0]);
            int newMaxEndTime = Math.max(interval[1], intervals[i][1]);
            interval = new int[]{newMinStartTime, newMaxEndTime};
          }
        }
        if(!addNewInterval) {
            result.add(interval);
        }
      	return result.toArray(new int[result.size()][2]);
    }
  private boolean isOverlapping(int[] interval1, int[] interval2) {
    return interval1[1] >= interval2[0] && interval1[0] <= interval2[1];
  }
}
