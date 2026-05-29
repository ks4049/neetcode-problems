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
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next == null) {
            return head;
        }
        ListNode current = head, reversedHead=null;
        while(current!=null) {
            ListNode temp = current;
            current = current.next;
            temp.next = reversedHead;
            reversedHead = temp;
        }
        return reversedHead;
    }
}
