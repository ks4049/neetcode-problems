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
    
    public int pairSum(ListNode head) {
        /*
            1. Compute the mid node
            2. Identify the (n-i-1)th node for every ith node until mid
            3. Compute the twin sum
            4. Track the maximum twin sum
        */
        if(head == null) {
            return 0;
        }
        if(head.next == null) {
            return head.val;
        }
        Stack<ListNode> firstHalf = new Stack<ListNode>();
        int len = computeMidNode(head, firstHalf);
        int maxPairSum = Integer.MIN_VALUE;
        ListNode midNode = firstHalf.peek(), temp;
        if(len%2 == 1 && !firstHalf.isEmpty()) {
            maxPairSum = Math.max(maxPairSum, firstHalf.pop().val);
            temp = midNode.next;
        } else {
            temp = firstHalf.pop(); // midNode is part of second half
        }
        while(!firstHalf.isEmpty() && temp!= null) {
            ListNode top = firstHalf.pop();
            maxPairSum = Math.max(maxPairSum, (top.val+temp.val));
            temp = temp.next;
        }

        return maxPairSum;

        
    }

    private int computeMidNode(ListNode head, Stack<ListNode> firstHalf) {
        ListNode slow=head, fast=head;
        int len =0;
        firstHalf.add(slow);
        while(fast != null && fast.next!= null) {
            slow = slow.next;
            firstHalf.add(slow);
            fast = fast.next.next;
            len+=2;
        }
        if(fast!= null && fast.next == null) {
            len+=1;//odd
        }
        return len;
    }
}