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
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        diameterOfBinaryTreeUtil(root);
        return maxDiameter;
        
    }

    public int diameterOfBinaryTreeUtil(TreeNode root) {
        if(root.left == null && root.right == null) {
        return 0;
        }
        int leftDiameter = 0, rightDiameter=0;
        if(root.left != null) {
            leftDiameter = 1+diameterOfBinaryTreeUtil(root.left);
        }
        if(root.right != null) {
            rightDiameter = 1+diameterOfBinaryTreeUtil(root.right);
        }
        if(maxDiameter < (leftDiameter+rightDiameter)) {
            maxDiameter = leftDiameter+rightDiameter;
        }
        return Math.max(leftDiameter,rightDiameter);
    }
}
