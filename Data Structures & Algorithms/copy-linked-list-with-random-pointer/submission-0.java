/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head==null) {
            return head;
        }
        Map<Node, Node> oldNewNodeMap = new HashMap<Node,Node>();

        Node current = head, prevNewNode=null;
        while(current != null) {
            // make a new node for current node & current's random
            Node newNode;
            if(oldNewNodeMap.containsKey(current)) {
                newNode = oldNewNodeMap.get(current);
            } else {
                newNode = new Node(current.val);
                oldNewNodeMap.put(current, newNode);
            }
            Node currentRandom = current.random;
            if(currentRandom != null) {
                if(!oldNewNodeMap.containsKey(currentRandom)) {
                    Node newRandomNode = new Node(current.random.val);
                    newNode.random = newRandomNode;
                    oldNewNodeMap.put(current.random, newRandomNode);
                } else {
                    newNode.random = oldNewNodeMap.get(currentRandom);
                }
            }
            if(prevNewNode != null) {
                prevNewNode.next = newNode;
            }
            prevNewNode = newNode;
            current = current.next;
        }
        return oldNewNodeMap.get(head);
        
    }
}
