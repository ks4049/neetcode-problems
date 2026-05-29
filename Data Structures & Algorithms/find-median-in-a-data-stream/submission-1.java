class MedianFinder {
    int arrSize;
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        this.arrSize=0;
        this.maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        this.minHeap = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        arrSize++;
        if(arrSize ==1) {
            maxHeap.add(num);
        } else {
            int maxHeapSize = arrSize%2==0? arrSize/2: (arrSize/2) +1;
            int minHeapSize = arrSize-maxHeapSize;
            if(num < maxHeap.peek()) {
                // num needs to be pushed to maxHeap
                List<Integer> tempList = new ArrayList<>();
                while(!maxHeap.isEmpty() && maxHeap.peek()> num) {
                    tempList.add(maxHeap.poll());
                }
                maxHeap.add(num);
                int index=tempList.size()-1;
                while(index>=0 && maxHeap.size()<maxHeapSize) {
                    maxHeap.add(tempList.get(index));
                    index--;
                }
                // add the remaining elements in minHeap
                while(index >=0 && minHeap.size()< minHeapSize) {
                    minHeap.add(tempList.get(index));
                    index--;
                }
            } else {
                // num needs to be in minHeap
                if(minHeap.isEmpty()) {
                    minHeap.add(num);
                }else if(num > minHeap.peek()) {
                    List<Integer> tempList = new ArrayList<>();
                    while(!minHeap.isEmpty() && minHeap.peek() < num) {
                        tempList.add(minHeap.poll());
                    }
                    minHeap.add(num);
                    int index=tempList.size()-1;
                    while(index>=0 && minHeap.size()<minHeapSize) {
                        minHeap.add(tempList.get(index));
                        index--;
                    }
                    // add the remaining elements in minHeap
                    while(index >=0 && maxHeap.size()< maxHeapSize) {
                        maxHeap.add(tempList.get(index));
                        index--;
                    }
                } else {
                    if(maxHeap.size() < maxHeapSize) {
                        maxHeap.add(num);
                    } else {
                        minHeap.add(num);
                    }
                }
            }
        }
    }
    
    public double findMedian() {
        if(arrSize %2 == 1) {
            return maxHeap.peek();
        } else {
            return (double)(maxHeap.peek() + minHeap.peek())/2.0;
        }
        
    }
}
