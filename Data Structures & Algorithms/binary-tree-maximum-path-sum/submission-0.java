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
  	int maxPathSum = -1001;
    public int maxPathSum(TreeNode root) {
      if(root == null) {
        return 0;
      }
      maxPathSumUtil(root); // -15
      return maxPathSum;
    }
  
		private int maxPathSumUtil(TreeNode root) {
      if(root == null){
        return 0;
      }
      int leftPathSum = maxPathSumUtil(root.left); // 10 -> LS:0, RS:0
      int rightPathSum = maxPathSumUtil(root.right); // 20(LS:15, RS:5) ->15(LS: -5, RS:0)->-5(0,0)_
      int currentMaxSum = Math.max(Math.max(root.val, root.val+leftPathSum+rightPathSum), Math.max(root.val+leftPathSum, root.val+rightPathSum));
      if(maxPathSum < currentMaxSum) {
        maxPathSum = currentMaxSum; //10, 15, 
      }
      // return a valid max path sum to the ancestor that includes the current root
      return Math.max(root.val, Math.max(root.val+leftPathSum, root.val+rightPathSum));
    }
}

