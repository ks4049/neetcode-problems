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
    public boolean isSameTree(TreeNode p, TreeNode q) {
      //return isSameTreeRecursiveUtil(p,q); 
      return isSameTreeBfs(p,q); 
    }
  
  
  	// DFS approach
  	private boolean isSameTreeRecursiveUtil(TreeNode p, TreeNode q) {
      if(p == null && q == null) {
        return true;
      }else if(p == null || q == null) {
        return false;
      } else {
        return (p.val == q.val && isSameTreeRecursiveUtil(p.left, q.left) && isSameTreeRecursiveUtil(p.right, q.right));
      }
    }

    //BFS approach
    private boolean isSameTreeBfs(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
        return true;
        } else if(p == null || q == null) {
        return false;
        }
        Queue<TreeNode> tree1 = new LinkedList<TreeNode>();
        Queue<TreeNode> tree2 = new LinkedList<TreeNode>();
        tree1.add(p);
        tree2.add(q);
        boolean isSameTree = true;
        while(!tree1.isEmpty() && !tree2.isEmpty()) {
        TreeNode pNode = tree1.poll();
        TreeNode qNode = tree2.poll();
        if(pNode.val != qNode.val) {
            isSameTree = false;
            break;
        }
        if(pNode.left!=null && qNode.left!=null) {
            tree1.add(pNode.left);  
            tree2.add(qNode.left);
        } else if(pNode.left!=null || qNode.left!=null) {
            isSameTree = false;
            break;
        }
        
        if(pNode.right!=null && qNode.right!=null) {
            tree1.add(pNode.right);  
            tree2.add(qNode.right);
        } else if(pNode.right!=null || qNode.right!=null) {
            isSameTree = false;
            break;
        }
        }
    return isSameTree;
  }
}
