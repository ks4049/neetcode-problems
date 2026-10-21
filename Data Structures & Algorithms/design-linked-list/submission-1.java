class MyLinkedList {
    ListNode head, tail;
    int listLen=0;

    class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    public MyLinkedList() {
        this.head =null;
        this.tail = null;
    }
    
    public int get(int index) {
        if(index >= listLen) {
            return -1 ;
        } else if(index==0) {
            return head!=null? head.val: -1;
        } else if(index ==listLen-1) {
            return tail!=null? tail.val: -1;
        } 
        // traverse from the head until index position
        int i=0;
        ListNode curr = head;
        while(curr!= null && i<index) {
            curr = curr.next;
            i++;
        }
        if(i==index && curr!=null) {
            return curr.val;
        }
        return -1;
        
    }
    
    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        if(head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        listLen++;
    }
    
    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        if(tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        listLen++;
    }
    
    public void addAtIndex(int index, int val) {
        if(index > listLen) {
            return;
        } else if(index==0) {
            addAtHead(val);
        } else if(index ==listLen) {
            addAtTail(val);
        } else {
            // traverse from the head until index position
            int i=0;
            ListNode curr = head, prevNode=null;
            while(curr!= null && i<index) {
                prevNode = curr;
                curr = curr.next;
                i++;
            }
            if(i==index && prevNode!=null) {
                ListNode newNode = new ListNode(val);
                prevNode.next = newNode;
                newNode.prev = prevNode;
                newNode.next = curr;
                if(curr!=null) {
                    curr.prev = newNode;
                }
                listLen++;
            }
        }
    }
    
    public void deleteAtIndex(int index) {
         if(index >= listLen) {
            return;
        } else if(index==0) {
            deleteAtHead();
        } else if(index ==listLen-1) {
            deleteAtTail();
        } else {
            // traverse from the head until index position
            int i=0;
            ListNode curr = head, prevNode=null;
            while(curr!= null && i<index) {
                prevNode = curr;
                curr = curr.next;
                i++;
            }
            if(i==index && prevNode!=null) {
               prevNode.next = curr.next;
               if(curr.next != null) {
                curr.next.prev = prevNode;
               }
               listLen--;
            }
        }
    }

    private void deleteAtHead() {
        if(head != null) {
            head = head.next;
            head.prev = null;
            if(listLen == 1) {
                tail = head;
            }
            listLen--;
        }
    }

     private void deleteAtTail() {
        if(tail != null) {
            tail = tail.prev;
            tail.next = null;
            if(listLen == 1) {
                head = tail;
            }
            listLen--;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */