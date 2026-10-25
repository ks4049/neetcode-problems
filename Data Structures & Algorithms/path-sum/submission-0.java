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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }
        return hasPathSumUtil(root, targetSum, 0);
    }

    private boolean hasPathSumUtil(TreeNode root, int targetSum, int currentSum) {
        if(root==null) {
            return false;
        }
        currentSum+= root.val;
        if(root.left == null && root.right == null) {
            return currentSum == targetSum;
        }
        System.out.println("At root "+ root.val + " current sum so far "+ currentSum);
        return hasPathSumUtil(root.left, targetSum, currentSum) || hasPathSumUtil(root.right, targetSum, currentSum); 
    }
}