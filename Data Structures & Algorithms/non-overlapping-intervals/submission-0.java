class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length==1) {
            return 0;
        }
        Arrays.sort(intervals, (int1, int2) -> Integer.compare(int1[0], int2[0]));
        int[] lastInterval = intervals[0];
        int erasedCount=0;
        for(int i=1; i<intervals.length; i++) {
            // if lastInterval is strictly > than the current interval(overlapping)
            if(lastInterval[1] > intervals[i][0]) {
                // decide which one to remove, pick the one with least end time
                if(lastInterval[1] > intervals[i][1]) {
                    lastInterval = intervals[i]; // erase last interval
                    erasedCount++;
                } else {
                    // erase the intervals[i] and move forward
                    erasedCount++;
                }
            } else {
                // non overlapping
                lastInterval = intervals[i];
            }
        }
        return erasedCount;
    }
}
