/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next==head) {
            return true;
        }
        if(head.next == null) {
            return false;
        }
        // with space complexity: O(N)
        // Set<ListNode> traversedNodes = new HashSet<ListNode>();
        // ListNode current = head;
        // while(current!= null && !traversedNodes.contains(current)) {
        //     traversedNodes.add(current);
        //     current = current.next;
        // }
        // return current!=null;

        // with space: O(1) Hare and tortoise algorithm
        ListNode slow=head, fast = head;
        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast) {
                break;
            }   
        }
        return slow==fast;
        
    }
}
