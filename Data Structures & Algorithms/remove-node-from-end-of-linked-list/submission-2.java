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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || (n==1 && head.next == null)){
            return null;
        }
        ListNode fast=head, slow=head, prevSlow=null;
        int i=1;
        // traverse k nodes
        while(i<n) {
            fast=fast.next;
            i++;
        }

        // traverse n-k nodes
        while(fast !=null && fast.next != null) {
            prevSlow =slow;
            slow = slow.next;
            fast = fast.next;
        }

        if(prevSlow==null) {
            head = head.next;
        } else {
            prevSlow.next = slow.next;
        }
        return head;
    }
}
