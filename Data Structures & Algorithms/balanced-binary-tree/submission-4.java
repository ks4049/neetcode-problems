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
        if(root == null) {
            return true;
        }

        boolean isLeftSubTreeBalanced = isBalanced(root.left);
        int leftHeight=0, rightHeight=0;
        if(root.left != null) {
            leftHeight = 1+heightOfTree(root.left);
        }
        
        boolean isRightSubTreeBalanced = isBalanced(root.right);
        if(root.right!=null) {
            rightHeight =1+heightOfTree(root.right);
        }
        
        return isLeftSubTreeBalanced && isRightSubTreeBalanced && Math.abs(leftHeight-rightHeight)<=1;

    }
    public boolean isBalancedUtil(TreeNode root) {
        if(root == null) {
            return true;
        }
        int leftHeight=0,rightHeight=0;
        if(root.left != null) {
            leftHeight = 1+heightOfTree(root.left);
            
        }
        if(root.right != null) {
            rightHeight = 1+heightOfTree(root.right);
        }
        System.out.println("left height & right height "+ leftHeight + "  "+rightHeight);
        return Math.abs(leftHeight-rightHeight)<=1;
    }

    private int heightOfTree(TreeNode root) {
        if(root.left == null && root.right == null) {
            return 0;
        }
        int leftHeight=0, rightHeight=0;
        if(root.left != null) {
            leftHeight = 1+ heightOfTree(root.left);
        }
        if(root.right != null) {
            rightHeight = 1+ heightOfTree(root.right);
        }
        return Math.max(leftHeight, rightHeight);
    }
}
