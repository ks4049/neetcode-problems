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
    public TreeNode invertTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return root;
        }
        return invertTreeUtil(root);
    }

    private TreeNode invertTreeUtil(TreeNode root) {
         if(root == null) {
            return root;
         }

         TreeNode newNode = new TreeNode(root.val);
         if(root.left != null) {
            newNode.right = invertTreeUtil(root.left);
         }
         if(root.right != null) {
            newNode.left = invertTreeUtil(root.right);
         }
         return newNode;
    }
}
