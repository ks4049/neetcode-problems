class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 1) {
            return intervals;
        }
        /// sort the intervals by start time
        Arrays.sort(intervals, (interval1, interval2)-> Integer.compare(interval1[0], interval2[0]));
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        int[] lastInterval = result.get(0);
        for(int i=1; i<intervals.length; i++) {
            // overlapping
            if(lastInterval[1] >= intervals[i][0]) {
                lastInterval[1] = Math.max(lastInterval[1], intervals[i][1]);
            } else {
                result.add(intervals[i]);
                lastInterval = intervals[i];
            }
        }
        return result.toArray(new int[result.size()][2]);

        
    }
}
