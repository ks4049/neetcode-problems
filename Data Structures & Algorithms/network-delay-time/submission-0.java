class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        if(k>n){
            return -1;
        }
        int[] minDist = new int[n];
        fillMinDist(minDist, Integer.MAX_VALUE);
        minDist[k-1]=0;
        Map<Integer, List<Node>> adjacencyMap = computeAdjacencyMap(times,n);
        PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>((n1, n2) -> Integer.compare(n1.weight, n2.weight));
        nodeQueue.add(new Node(k,0));

        while(!nodeQueue.isEmpty()) {
            Node current = nodeQueue.poll();
            List<Node> neighbors = adjacencyMap.getOrDefault(current.node, new ArrayList<>());
            for(int i=0; i<neighbors.size(); i++) {
                Node nei = neighbors.get(i);
                if(minDist[nei.node-1] > minDist[current.node-1]+ nei.weight) {
                    minDist[nei.node-1] = minDist[current.node-1]+nei.weight;
                    nodeQueue.add(new Node(nei.node, minDist[nei.node-1]));
                }
            }
        }
        int maxDistValue = Integer.MIN_VALUE;
        for(int i=0; i<minDist.length; i++) {
            if(maxDistValue < minDist[i]) {
                maxDistValue = minDist[i];
            }
        }
        return maxDistValue == Integer.MAX_VALUE? -1: maxDistValue;
    }

    private void fillMinDist(int[] minDist, int value) {
        for(int i=0; i<minDist.length; i++) {
            minDist[i] = value;
        }
    } 

    Map<Integer, List<Node>> computeAdjacencyMap(int[][] times, int n) {
        Map<Integer,List<Node>> adjacencyMap = new HashMap<>();
        for(int i=0; i<times.length; i++) {
            List<Node> adjacentNodes = adjacencyMap.getOrDefault(times[i][0], new ArrayList<>());
            adjacentNodes.add(new Node(times[i][1], times[i][2]));
            adjacencyMap.put(times[i][0], adjacentNodes);
        }
        return adjacencyMap;
    }

    class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
