class TimeMap {
    Map<String, List<ValueTime>> timeMap;

    public TimeMap() {
        timeMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        List<ValueTime> valueTimes = timeMap.getOrDefault(key, new ArrayList<ValueTime>());
        valueTimes.add(new ValueTime(value, timestamp));
        timeMap.put(key, valueTimes);
    }
    
    public String get(String key, int timestamp) {
        List<ValueTime> valueTimes = timeMap.getOrDefault(key, new ArrayList<ValueTime>());
        if(valueTimes.isEmpty()) {
            return "";
        } else {
            int low=0, high = valueTimes.size()-1, foundIndex=-1;
            ValueTime maxSoFar = new ValueTime("", 0);
            // perform binary search on identifying the max timestamp that is <= current timestamp
            while(low<=high) {
                int mid = (low+high)/2;
                ValueTime midValTime = valueTimes.get(mid);
                if(midValTime.timestamp == timestamp) {
                    maxSoFar = midValTime;
                    break;
                } else if(timestamp > midValTime.timestamp) {
                    // record the maxTime so far
                    if(maxSoFar.timestamp <  midValTime.timestamp) {
                        maxSoFar = midValTime;
                    }
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
            return maxSoFar.value;
        }
        
    }

    class ValueTime {
        String value;
        int timestamp;

        public ValueTime(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}
