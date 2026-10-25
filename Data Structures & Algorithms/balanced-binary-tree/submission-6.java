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
    public boolean isBalanced(TreeNode root) {
        if(root == null || (root.left==null && root.right == null)) {
            return true;
        }
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(height(root.left)-height(root.right)) <=1;
    }

    private int height(TreeNode root) {
        if(root == null) {
            return 0;
        } else if(root.left == null && root.right == null) {
            return 1;
        } else if(root.left == null) {
            return 1+ height(root.right);
        } else if(root.right == null){
            return 1 + height(root.left);
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }
}
