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
    int inorderSize = 0, minKthValue = -1;
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) {
            return -1;
        }
        kthSmallestUtil(root, k);
        return minKthValue;
    }

    private void kthSmallestUtil(TreeNode root, int k) {
        if(root == null) {
            return;
        }
        kthSmallestUtil(root.left, k);
        inorderSize = inorderSize+1;
        if((inorderSize) == k) {
            minKthValue = root.val;
        }
        kthSmallestUtil(root.right, k);
    }
}
