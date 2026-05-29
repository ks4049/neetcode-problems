class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        PriorityQueue<Interval> queue = new PriorityQueue<Interval>((int1,int2) -> (Integer.compare(int1.difference, int2.difference)));
        for(int i=0; i<intervals.length; i++) {
            queue.add(new Interval(intervals[i], (intervals[i][1]-intervals[i][0]+1)));
        }
        // trigger the queries on the queue
        int[] result = new int[queries.length];
        for(int i=0; i<queries.length; i++) {
            result[i] = assignInterval(queries[i], queue);
        }
        
        return result;
    }

    private int assignInterval(int query, PriorityQueue<Interval> queue) {
        int intervalLen=-1;
        List<Interval> tempList = new ArrayList<>();
        while(!queue.isEmpty() && isNotInRange(queue.peek(), query)) {
            tempList.add(queue.poll());
        }
        if(!queue.isEmpty()) {
            intervalLen = queue.peek().difference;
        }
        // adding the intervals back to queue
        int index = tempList.size()-1;
        while(index>=0 && !tempList.isEmpty()) {
            queue.add(tempList.get(index));
            index--;
        }
        return intervalLen;
    }

    private boolean isNotInRange(Interval interval, int query) {
        return query<interval.time[0] || query>interval.time[1];
    }


    class Interval {
        int[] time;
        int difference;
        public Interval(int[] time, int difference) {
            this.time = time;
            this.difference = difference;
        }
    }
}
