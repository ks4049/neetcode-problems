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

class Solution {
  	int goodNodes = 0;
    public int goodNodes(TreeNode root) {
      if(root==null){
        return 0;
      }else if(root.left == null && root.right == null) {
        return 1;
      }
      goodNodesUtilDfs(root, root.val); 
      return goodNodes;
    }
  
  private void goodNodesUtilDfs(TreeNode root, int pathMaxSoFar) {
    if(root == null) {
      return;
    }
    if(root.val >= pathMaxSoFar) {
      goodNodes++;
    }
    goodNodesUtilDfs(root.left, Math.max(root.val, pathMaxSoFar));
    goodNodesUtilDfs(root.right, Math.max(root.val, pathMaxSoFar));
  }
}
