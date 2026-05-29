/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        // sort the meeting slots by start time
        if(intervals.size() <=1) {
            return true;
        }
        Collections.sort(intervals, (int1, int2) -> (Integer.compare(int1.start, int2.start)));
        Interval lastInterval = intervals.get(0);
        boolean canAttend=true;
        for(int i=1; i<intervals.size(); i++) {
            if(lastInterval.end > intervals.get(i).start) {
                canAttend=false;
                break;
            } else {
                lastInterval = intervals.get(i);
            }
        }
        return canAttend;

    }
}
