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
    public boolean isValidBST(TreeNode root) {
        return isValidBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBSTUtil(TreeNode root, int minValue, int maxValue) {
      if(root==null) {
        return true;
      }
      return(root.val > minValue && root.val <maxValue) && isValidBSTUtil(root.left, minValue, root.val) && isValidBSTUtil(root.right, root.val, maxValue);
    }
}
