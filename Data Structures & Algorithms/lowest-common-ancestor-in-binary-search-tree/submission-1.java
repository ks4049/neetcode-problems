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
    List<TreeNode> pAncestors = new ArrayList<>();
    List<TreeNode> qAncestors = new ArrayList<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if((p==null && q==null) || root==null) {
            return null;
        } else if( p==null || q==null) {
          return p!=null? p: q;
        }
      	if(root==p || root==q) {
          return root;
        }
       boolean foundP =searchNode(root, p, true);
       boolean foundQ = searchNode(root, q, false);
       // compute the LCA from the list of ancestors 
       if(foundP && foundQ) {
        return computeLCAFromAncestors(pAncestors, qAncestors);
       } else {
        return null;
       }
    }
  
  private boolean searchNode(TreeNode root, TreeNode node, boolean isPNode) {
    if(root==null) {
	    return false;
    }
    if(root.val == node.val) {
      if(isPNode) {
        pAncestors.add(root);
      } else {
        qAncestors.add(root);
      }
      return true;
    }
    
    boolean foundRight = false;
    boolean foundLeft = searchNode(root.left, node, isPNode);
    if(foundLeft) {
      if(isPNode) {
        pAncestors.add(root);
      } else {
        qAncestors.add(root);
      }
    } else {
      // find node on right size
       foundRight = searchNode(root.right, node, isPNode);
       if(foundRight) {
         if(isPNode) {
           pAncestors.add(root);
				 } else {
           qAncestors.add(root);
         }
       }
    }
    return foundLeft||foundRight;  
  }
  
  private TreeNode computeLCAFromAncestors(List<TreeNode> pAncestors, List<TreeNode> qAncestors) {
    int len1 = pAncestors.size(), len2= qAncestors.size();
    TreeNode lcaNode = null;
    
    for(int i=0, j=0; i<len1&&j<len2;) {
      if(pAncestors.get(i) == qAncestors.get(j)) {
        lcaNode = pAncestors.get(i);
        break;
      } else {
        if(len1-i-1 <= len2-j-1) {
          j++;
        } else {
          i++;
        }
      }
    }
    return lcaNode;
  }
}
