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
    public int kthSmallest(TreeNode root, int k) {
      if(k==0) {
        return -1;
      }
      List<Integer> inorderList = new ArrayList<>();
      kthSmallestUtil(root, k, inorderList);
      return inorderList.isEmpty()? -1: inorderList.get(inorderList.size()-1);
    }
  
  private void kthSmallestUtil(TreeNode root, int k, List<Integer> inorderList) {
    if(root == null || inorderList.size()==k){
      return;
    } 
    kthSmallestUtil(root.left, k, inorderList);
    if(inorderList.size()<k) {
    	inorderList.add(root.val);  
    }
    kthSmallestUtil(root.right, k, inorderList);
  }
}