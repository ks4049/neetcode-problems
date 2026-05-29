class Solution {
    public boolean validTree(int n, int[][] edges) {
        // if(edges.length==0 && n==1) {
        //     return true;
        //     // all nodes must be connected to form a valid tree
        //}
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        for(int i=0; i<edges.length; i++) {
            List<Integer> existingEdges = edgeMap.getOrDefault(edges[i][0], new ArrayList<>());
            existingEdges.add(edges[i][1]);
            edgeMap.put(edges[i][0], existingEdges);
        }
        Set<Integer> globalNodes = new HashSet<Integer>();
        Set<Integer> componentNodes = new HashSet<Integer>();
        for(int i=0; i<n; i++) {
            componentNodes = new HashSet<>();
            if(globalNodes.contains(i)) {
                continue;
            }
            boolean isValidComponent = validTreeUtil(i, edgeMap, componentNodes);
            if(!isValidComponent ) {
               return false;
            } else {
                if(componentNodes.size() == n) {
                    return true;
                }
                Set<Integer> intersection = new HashSet<>(globalNodes);

                if(globalNodes.isEmpty()) {
                    globalNodes.addAll(componentNodes);
                } else {
                    intersection.retainAll(componentNodes);
                    if(intersection.isEmpty()) {
                        return false;
                    } else {
                        globalNodes.addAll(componentNodes);
                    }
                }
            }

        }
        
        
        return globalNodes.size()==n;
    }

    private boolean validTreeUtil(int currentIndex, Map<Integer,List<Integer>> edgeMap, Set<Integer> globalNodes) {
        if(globalNodes.contains(currentIndex)) {// forms a cycle with those undirected edges
           return false;
        }
        globalNodes.add(currentIndex);
        List<Integer> edges = edgeMap.getOrDefault(currentIndex, new ArrayList<>());
        boolean isValidTree = true;
        for(int i=0; i<edges.size(); i++) {
            boolean isValidPath = validTreeUtil(edges.get(i), edgeMap, globalNodes);
            if(!isValidPath) {
               isValidTree = false;
               break;
            }
        }
        return isValidTree;
    }
}
