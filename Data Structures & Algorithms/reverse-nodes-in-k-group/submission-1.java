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
    public ListNode reverseKGroup(ListNode head, int k) {
        //identify the number of groups to reverse
        int groupCount = computeGroupCount(head, k);
        // System.out.println("Group count" + groupCount);
        int i=0;
        ListNode prevGroupEnd=null, lastGroupEnd=head, reversedHead=null, current=head;
        while(i<groupCount) {
            // group is ready to be merged
            lastGroupEnd = current;
            // reverse the current group upto k elements
            int j=0;
            ListNode currReversedHead=null;
            while(j<k && current!=null) {
                ListNode next = current.next;
                current.next = currReversedHead;
                currReversedHead = current;
                current = next;
                j++;
            }
            if(i==0) {
               reversedHead = currReversedHead; 
            }
            lastGroupEnd.next = current;
            if(prevGroupEnd != null) {
                prevGroupEnd.next = currReversedHead;
            }
            prevGroupEnd = lastGroupEnd;
            i++;
        }
        return reversedHead;
    }

    private int computeGroupCount(ListNode head, int k) {
        ListNode curr=head;
        int i=0, groups=0;
        while(curr!=null) {
            i++;
            if(i%k == 0) {
                groups++;
            }
            curr = curr.next;
        }
        return groups;
    }
}
