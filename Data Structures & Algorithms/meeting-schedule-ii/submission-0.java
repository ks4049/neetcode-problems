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
    public int minMeetingRooms(List<Interval> intervals) {
        if(intervals.size() <=1) {
            return intervals.size();
        }
        Collections.sort(intervals, (int1, int2) -> Integer.compare(int1.start, int2.start));
        PriorityQueue<Integer> rooms = new PriorityQueue<Integer>();

        for(int i=0; i<intervals.size(); i++) {
            Interval currentInterval = intervals.get(i);
            assignInterval(rooms, currentInterval);
        }
        return rooms.size();
    }

    private void assignInterval(PriorityQueue<Integer> rooms, Interval currentInterval) {
        List<Integer> tempList = new ArrayList<Integer>();
        while(!rooms.isEmpty() && rooms.peek()>currentInterval.start) {
            tempList.add(rooms.poll());
        }
        if(rooms.isEmpty()) {
            // assign meeting on next day
            rooms.add(currentInterval.end);
        } else {
            // can assign to some existing day
            int lastMeetingTime = rooms.poll();
            rooms.add(Math.max(currentInterval.end, lastMeetingTime));
        }
        int index=tempList.size()-1;
        while(index>=0 &&!tempList.isEmpty()) {
            rooms.add(tempList.get(index));
            index--;
        }
    }
}
