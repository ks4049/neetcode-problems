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
        if(preorder.length == 0 || inorder.length != preorder.length) {
            return null;
        }
        Map<Integer, Integer> inorderIndexMap = computeInorderIndexMap(inorder);

        return buildTreeUtil(preorder, inorder, inorderIndexMap, 0, inorder.length-1);
    }


    private TreeNode buildTreeUtil(int[] preorder, int[] inorder, Map<Integer, Integer> inorderIndexMap, int low, int high) {
        if(preorderIndex == preorder.length || high<low) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preorderIndex]);
        int inorderRootIndex = inorderIndexMap.get(preorder[preorderIndex]);
        if(inorderRootIndex-1 >= low) {
            preorderIndex++;
            root.left = buildTreeUtil(preorder, inorder, inorderIndexMap, low, inorderRootIndex-1);
        }
        if(inorderRootIndex+1 <= high) {
            preorderIndex++;
            root.right = buildTreeUtil(preorder, inorder, inorderIndexMap, inorderRootIndex+1, high);
        }
        return root;
    }


    private Map<Integer,Integer> computeInorderIndexMap(int[] inorder) {
        Map<Integer, Integer> inorderIndexMap = new HashMap<Integer,Integer>();
        for(int i=0; i<inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return inorderIndexMap;
    }
}
