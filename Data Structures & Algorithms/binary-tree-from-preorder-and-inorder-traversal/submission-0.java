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
  	int preorderIndex=0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      if(preorder.length==0 || inorder.length ==0){
        return null; // no elements to build
      }
      if(preorder.length == 1 && inorder.length==1) {
        return new TreeNode(preorder[0]);
      }
      Map<Integer,Integer> inorderMap = new HashMap<>();
      for(int i=0; i<inorder.length; i++) {
        inorderMap.put(inorder[i], i);
      }
      
      return buildBinaryTreeUtil(preorder, inorder, inorderMap, 0, inorder.length-1);
    }
  
  	private TreeNode buildBinaryTreeUtil(int[] preorder, int[] inorder, Map<Integer,Integer> inorderMap, int start, int end) {
      if(preorderIndex == preorder.length || start>end) {
        return null;
      }
      TreeNode newNode = new TreeNode(preorder[preorderIndex]);
      preorderIndex++;
      newNode.left = buildBinaryTreeUtil(preorder, inorder, inorderMap, start, inorderMap.get(newNode.val)-1);
      newNode.right = buildBinaryTreeUtil(preorder, inorder, inorderMap, inorderMap.get(newNode.val)+1, end);
      return newNode;
    }
}
