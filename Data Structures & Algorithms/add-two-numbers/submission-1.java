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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null && l2==null) {
            return null;
        } else if(l1==null || l2 == null) {
            return l1==null? l2: l1;
        }

        ListNode currentL1=l1, currentL2=l2, lastL1=null;
        int currentSum=0, carryOver=0;
        // doing in memory, consider l1 as the result list
        while(currentL1!=null && currentL2!=null) {
            currentSum = currentL1.val+currentL2.val+carryOver;
            currentL1.val = currentSum%10;
            carryOver = currentSum/10;
            lastL1 = currentL1;
            currentL1 = currentL1.next;
            currentL2 = currentL2.next;
        }
        while(currentL2!=null) {
            currentSum = carryOver + currentL2.val;
            currentL2.val = currentSum%10;
            carryOver = currentSum/10;
            lastL1.next = currentL2;
            lastL1 = currentL2;
            currentL2 = currentL2.next;
        }

        while(currentL1!=null) {
            currentSum = carryOver + currentL1.val;
            currentL1.val = currentSum%10;
            carryOver = currentSum/10;
            lastL1.next = currentL1;
            lastL1 = currentL1;
            currentL1 = currentL1.next;
        }

        while(carryOver > 0) {
            ListNode newNode = new ListNode(carryOver%10);
            carryOver = carryOver/10;
            lastL1.next = newNode;
            lastL1 = newNode;
        }
        return l1;

        
    }
}
