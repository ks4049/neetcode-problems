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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1==null && list2== null) {
            return null;
        } else if(list1 == null) {
            return list2;
        } else if(list2 == null) {
            return list1;
        }
        ListNode mergedHead=null, mergedTail=null, currA = list1, currB = list2;
        while(currA!=null && currB !=null) {
            ListNode temp;
            if(currA.val <=  currB.val) {
                temp = currA;
                currA = currA.next;
            } else {
                temp = currB;
                currB = currB.next;
            }
            
            temp.next = null;
            if(mergedHead==null) {
                mergedHead = temp;
                mergedTail = temp;
            } else {
                mergedTail.next = temp;
                mergedTail = mergedTail.next;
            }
        }
        if(currA!= null) {
            mergedTail.next = currA;
        }
        if(currB !=null) {
            mergedTail.next = currB;
        }
        return mergedHead;
    }
}