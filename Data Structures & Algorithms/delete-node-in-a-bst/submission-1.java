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

    class NodeInfo {
        TreeNode parent;
        TreeNode node;
        public NodeInfo(TreeNode parent, TreeNode node) {
            this.parent = parent;
            this.node = node;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return null;
        }
        NodeInfo searchResult = searchNode(null, root, key);
        if(searchResult == null) {
            return root;
        }
        TreeNode parent = searchResult.parent;
        TreeNode node = searchResult.node;
        // deletion of root itself

        TreeNode replacedNode = null;
        
        if(node.left == null) {
            replacedNode = node.right;
        } else {
            replacedNode = node.left;
            TreeNode rightNode = replacedNode.right;
            replacedNode.right = node.right;
            
            // attach the right node on the leftmost of the right Sub tree
            if(rightNode != null) {
                TreeNode temp = replacedNode.right, prev=null;
                while(temp !=null) {
                    prev = temp;
                    temp = temp.left;
                }
                if(prev !=null) {
                    prev.left = rightNode;
                }
            }
        }

         if(parent != null) {
            if(parent.left == node) {
                parent.left = replacedNode;
            } else {
                parent.right = replacedNode;
            }
        } else {
            root = replacedNode;
        }

            // root will be root.left if not null
            // root.left.right will be the left of the right most node
            // root.left.right will be the new root
        
        return root;
 
        
    }

    private NodeInfo searchNode(TreeNode parent, TreeNode root, int key) {
        if(root == null) {
            return null;
        }
        if(root.val == key) {
            return new NodeInfo(parent, root);
        } else if(root.val > key) {
            return searchNode(root, root.left, key);
        } else {
            return searchNode(root, root.right, key);
        }
    }

}