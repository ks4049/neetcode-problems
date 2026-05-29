/*
 * There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b] means that there is an edge between node a and node b in the graph.

The nodes are numbered from 0 to n - 1.

Return the total number of connected components in that graph.
 * 
 * 
 * */
class Solution {
    public int countComponents(int n, int[][] edges) {
      if(n==1) {
        return 1;
      }
      Map<Integer,List<Integer>> nodeMap = new HashMap<>();
      for(int i=0; i<edges.length; i++) {
        List<Integer> existingEdges = nodeMap.getOrDefault(edges[i][0], new ArrayList<>());
        existingEdges.add(edges[i][1]);
        nodeMap.put(edges[i][0], existingEdges);
      }
      Set<Integer> visitedNodes = new HashSet<>();
      int numComponents = 0;
      int[] result = new int[n];
      fillResult(result, -1);
      Set<Integer> allNodes= new HashSet<>();
      for(int i=0; i<n; i++) {
        visitedNodes = new HashSet<>();
        if(!allNodes.contains(i)) {
          traverseComponent(i, nodeMap, visitedNodes);
          electLeaderForComponent(result, visitedNodes, i);
          allNodes.addAll(visitedNodes);
        }
      }
      return computeNumComponents(result);
    }

    private void fillResult(int[] result, int value) {
        for(int i=0; i<result.length; i++) {
            result[i] = value;
        }
    }

    private int computeNumComponents(int[] result) {
        Set<Integer> distinctComp = new HashSet<>();
        for(int i=0; i<result.length; i++) {
            if(!distinctComp.contains(result[i])) {
                distinctComp.add(result[i]);
            }
        }
        return distinctComp.size();
    }

    private void electLeaderForComponent(int[] result, Set<Integer> visitedNodes, int expectedLeader) {
        Set<Integer> unionLeaders = new HashSet<>();
        for(int node: visitedNodes) {
            if(result[node] != -1) {
                unionLeaders.add(result[node]); // found another leader, can union with this leader
            }
        }
        if(unionLeaders.isEmpty()) {
            for(int node: visitedNodes) {
                result[node] = expectedLeader;
            }
        } else {
            // choose one leader and union the elements with this leader
            int finalLeader = expectedLeader;
            for(int leader: unionLeaders) {
                finalLeader = leader;
                break;
            }
            for(int i=0; i<result.length; i++) {
                if((result[i]!=finalLeader && unionLeaders.contains(result[i])) || (result[i]==-1 && visitedNodes.contains(i))) {
                    result[i] = finalLeader;
                }
            }
        }
        //print(result);
    }

    private void print(int[] result) {
        for(int i=0; i<result.length; i++) {
            System.out.print(result[i] + ", ");
        }
        System.out.println();
    }
  
  	private void traverseComponent(int node, Map<Integer, List<Integer>> nodeMap, Set<Integer> visitedNodes) {
      if(visitedNodes.contains(node)) {
        return;
      }
      visitedNodes.add(node);
      List<Integer> nodeEdges = nodeMap.getOrDefault(node, new ArrayList<>());
      for(int i=0; i<nodeEdges.size(); i++) {
        traverseComponent(nodeEdges.get(i), nodeMap, visitedNodes);
      }
    }
  
}
