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
        ListNode mergedHead = null, prevNode = null, temp;
        if(list1 == null && list2 == null) {
            return null;
        } else if(list1 == null) {
            return list2;
        } else if(list2 == null) {
            return list1;
        }

        while(list1!=null && list2!=null) {
            if(list1.val<= list2.val) {
                temp = list1;
                list1 = list1.next;
            } else {
                temp = list2;
                list2 = list2.next;
                temp.next = list1;
            }
            if(mergedHead == null) {
                mergedHead = temp;
            } else {
                prevNode.next = temp;
            }
            prevNode = temp;
        }
        if(list1 != null) {
            prevNode.next = list1; //already taken care as the merged list is in list1
        }
        if(list2 != null) {
            prevNode.next = list2;
        }
        return mergedHead;
    }
}