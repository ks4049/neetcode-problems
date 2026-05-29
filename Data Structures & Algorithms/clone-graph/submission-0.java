/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
    	if(node == null){
        return null;
      }
      Map<Integer, Node> newNodeMap = new HashMap<>();
      return cloneGraphUtil(node, newNodeMap);
    }
  
  	private Node cloneGraphUtil(Node node, Map<Integer, Node> newNodeMap) {
      if(node == null) {
        return node;
      }
      if(newNodeMap.containsKey(node.val)) {
        return newNodeMap.get(node.val);
      }
      Node newNode = new Node(node.val);
      newNodeMap.put(node.val, newNode);
      if(node.neighbors != null) {
        List<Node> newNodeNeighbors = new ArrayList<>();
      	for(int i=0; i<node.neighbors.size(); i++) {
        	Node newNodeNeighbor = cloneGraphUtil(node.neighbors.get(i), newNodeMap);
          newNodeNeighbors.add(newNodeNeighbor);
      	}
        newNode.neighbors = newNodeNeighbors;
      }
      return newNode;
    }
} 
