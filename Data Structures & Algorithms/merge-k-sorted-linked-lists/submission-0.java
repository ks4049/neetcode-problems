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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        } else if(lists.length == 1) {
            return lists[0]; // only one list, nothing to merge
        }

        ListNode mergedHead = lists[0];
        for(int i=1; i<lists.length; i++) {
            ListNode currentList = lists[i];
            mergedHead = mergeLists(mergedHead, currentList);
        }
        return mergedHead;
    }

    private ListNode mergeLists(ListNode list1, ListNode list2) {
        if(list1==null && list2==null) {
            return null;
        } else if(list1==null) {
            return list2;
        } else if(list2==null) {
            return list1;
        }

        ListNode currentList1 = list1, currentList2=list2, prevNodeList1=null, list1Head = list1;

        while(currentList1!=null && currentList2!=null) {
            if(currentList1.val <=currentList2.val) {
                prevNodeList1 = currentList1;
                currentList1 = currentList1.next;
            } else {
                ListNode nextNodeList2 = currentList2.next;
                currentList2.next = currentList1;
                if(prevNodeList1 != null) {
                    prevNodeList1.next = currentList2;
                } else {
                    list1Head = currentList2;
                }
                prevNodeList1 = currentList2;
                currentList2 = nextNodeList2;
            }
        }
        // if list2 still exists
        if(currentList2 != null) {
            if(prevNodeList1 !=null) {
                prevNodeList1.next = currentList2;  
            }
        }
        return list1Head;

    }
}
