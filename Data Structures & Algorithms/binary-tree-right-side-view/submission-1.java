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
    public List<Integer> rightSideView(TreeNode root) {
      // recursive solution
      List<Integer> resultList = new ArrayList<Integer>();
      if(root == null) {
        return resultList;
      }
      //rightSideViewUtilDfs(root, resultList);
      rightSideViewUtilBfs(root, resultList);
      return resultList;  
    }
  
  	private void rightSideViewUtilDfs(TreeNode root, List<Integer> resultList) {
      if(root == null) {
        return;
      }
      resultList.add(root.val);
      if(root.right != null) {
        rightSideViewUtilDfs(root.right, resultList);
      } else if(root.left != null) {
        rightSideViewUtilDfs(root.left, resultList);
      }
    }
  
  	private void rightSideViewUtilBfs(TreeNode root, List<Integer> resultList) {
      Queue<TreeNode> bfsTraverse = new LinkedList<TreeNode>();
      bfsTraverse.add(root);
      while(!bfsTraverse.isEmpty()) {
        int levelSize = bfsTraverse.size();
        for(int i=0; i<levelSize; i++) {
        	TreeNode current = bfsTraverse.poll();
          if(i==levelSize-1) {
            resultList.add(current.val);
          }
          if(current.left!=null) {
          	bfsTraverse.add(current.left);
        	}
          if(current.right!=null) {
            bfsTraverse.add(current.right);
          }
        }
      }
    }
}
