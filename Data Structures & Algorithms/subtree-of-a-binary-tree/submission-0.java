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
     public boolean isSubtree(TreeNode root, TreeNode subRoot) {
      if((root==null && subRoot==null) || subRoot == null) {
        return true;
      } else if(root == null)  {
      	return false;
      }
      if(root.val == subRoot.val) {
        boolean isSameTree = isSameTree(root, subRoot);
        if(isSameTree) {
					return true;
        }
      } 
      boolean leftTree = isSubtree(root.left, subRoot);
      if(!leftTree) {
        boolean rightTree = isSubtree(root.right, subRoot);
        return rightTree;
      } else {
        return leftTree;
      }
    }
  
    private boolean isSameTree(TreeNode root1, TreeNode root2) {
        if(root1==null && root2==null) {
        return true;
        } else if(root1==null || root2==null) {
        return false;
        }
        
        return root1.val==root2.val && isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }
}
