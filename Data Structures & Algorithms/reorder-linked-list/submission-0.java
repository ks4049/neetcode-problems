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
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }
        /*
         1. Identify the mid element(prev and second half list head)
         2. Reverse the second half of the list from mid to end
         3. Reorder the list with pointers from first half and second half
        */
        ListNode prevMidNode = identifyMidNode(head);
        ListNode reversedSecondHalf=null, secondHalfHead=prevMidNode.next;
        // reverse the second half
        prevMidNode.next = null;
        while(secondHalfHead != null) {
            ListNode temp = secondHalfHead;
            secondHalfHead = secondHalfHead.next;
            temp.next = reversedSecondHalf;
            reversedSecondHalf = temp;
        }
        // reorder list starting from head and reversedSecondHalf
        ListNode firstHalf = head;
        while(firstHalf!=null && reversedSecondHalf!=null) {
            ListNode firstNode = firstHalf;
            firstHalf = firstHalf.next;
            ListNode secondNode = reversedSecondHalf;
            reversedSecondHalf = reversedSecondHalf.next;
            secondNode.next = firstHalf;
            firstNode.next = secondNode;
        }
    }

    private ListNode identifyMidNode(ListNode head) {
        ListNode slow = head, fast = head, prevSlow=null;
        while(fast!=null && fast.next!=null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast==null) {
            return prevSlow;// even list
        } else {
            return slow; // odd list
        }


    }
}
