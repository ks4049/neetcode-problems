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
        return invertTreeUtilBfs(root);
        //return invertTreeUtilRecursive(root);
    }

    private TreeNode invertTreeUtilBfs(TreeNode root) {
        Queue<NodeInfo> bfsTraverse = new LinkedList<NodeInfo>();
        NodeInfo rootInfo = new NodeInfo(root, new TreeNode(root.val));
        bfsTraverse.add(rootInfo);
        while(!bfsTraverse.isEmpty()) {
            NodeInfo nodeInfo = bfsTraverse.poll();
            TreeNode currentNode = nodeInfo.currentNode;
            TreeNode newNode = nodeInfo.newNode;
            if(currentNode.left != null) {
                TreeNode newRightNode = new TreeNode(currentNode.left.val);
                newNode.right = newRightNode;
                bfsTraverse.add(new NodeInfo(currentNode.left, newRightNode));
            } 
            if(currentNode.right != null) {
                TreeNode newLeftNode = new TreeNode(currentNode.right.val);
                newNode.left = newLeftNode;
                bfsTraverse.add(new NodeInfo(currentNode.right, newLeftNode));
            }
        }
        return rootInfo.newNode;

    }

    private TreeNode invertTreeUtilRecursive(TreeNode root) {
         if(root == null) {
            return root;
         }

         TreeNode newNode = new TreeNode(root.val);
         if(root.left != null) {
            newNode.right = invertTreeUtilRecursive(root.left);
         }
         if(root.right != null) {
            newNode.left = invertTreeUtilRecursive(root.right);
         }
         return newNode;
    }

    class NodeInfo {

        TreeNode currentNode;
        TreeNode newNode;

        public NodeInfo(TreeNode currentNode, TreeNode newNode) {
            this.currentNode = currentNode;
            this.newNode = newNode;
        }
        
    }
}
