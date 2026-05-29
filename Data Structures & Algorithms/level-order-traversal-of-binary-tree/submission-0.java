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
    public List<List<Integer>> levelOrder(TreeNode root) {
      if(root == null) {
        return new ArrayList<>();
      }
      Queue<TreeNode> levelOrder = new LinkedList<TreeNode>();
      levelOrder.add(root);
      List<List<Integer>> resultList = new ArrayList<>();
      
      while(!levelOrder.isEmpty()) {
        int currentLevelSize = levelOrder.size();
        List<Integer> currentList = new ArrayList<>();
        for(int i=0; i<currentLevelSize; i++) {
          TreeNode current = levelOrder.poll();
          currentList.add(current.val);
          if(current.left != null) {
            levelOrder.add(current.left);
          }
          if(current.right !=null) {
            levelOrder.add(current.right);
          }
        }
        resultList.add(currentList);
      }
      
      return resultList;
    }
}
