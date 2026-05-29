/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class Codec {

    // Encodes a tree to a single string.
  	/*
  	 * comma separated values for all the nodes, until you reach all the nodes, if null then capture it as N
  	 * */
    public String serialize(TreeNode root) {
      if(root == null) {
        return "";
      }
      StringBuilder serializedStr = new StringBuilder();
      Queue<TreeNode> bfsTraverse = new LinkedList<TreeNode>();
      bfsTraverse.add(root);
      
      
      while(!bfsTraverse.isEmpty()) {
        TreeNode current = bfsTraverse.poll();
        if(current !=null) {
        	serializedStr.append(current.val);
          serializedStr.append(',');
          bfsTraverse.add(current.left);
          bfsTraverse.add(current.right);
        } else {
          serializedStr.append('N');
          serializedStr.append(',');
        }
      }
      // remove extra comma at the end 
      //1,2,3,N,N,4,5,9,10,N,N,
      return serializedStr.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")) {
          return null;
        }
      	String[] valueNodes = data.split(",");
      	Queue<TreeNode> bfsTree = new LinkedList<TreeNode>();
      	TreeNode rootValueNode = new TreeNode(Integer.parseInt(valueNodes[0]));
      	bfsTree.add(rootValueNode);
      	int i=1;
      	while(!bfsTree.isEmpty() && i<valueNodes.length) {
          TreeNode currentNode = bfsTree.poll();
          // process the children of currentNode
          TreeNode leftNode=null, rightNode=null;
          if(!valueNodes[i].equals("N")) {
          	leftNode = new TreeNode(Integer.parseInt(valueNodes[i]));
            bfsTree.add(leftNode);
          }
          currentNode.left = leftNode;
          if(!valueNodes[i+1].equals("N")) {
            rightNode = new TreeNode(Integer.parseInt(valueNodes[i+1]));
            bfsTree.add(rightNode);
          }
          currentNode.right = rightNode;
          i+=2;
        }
        return rootValueNode;
    }
}
