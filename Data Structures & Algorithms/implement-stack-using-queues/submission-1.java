class MyStack {
    /*
    * Option 1: Implement stack using two queues
      Option 2: Implement stack using one double ended queue(doubly linked list)  
    */
    ListNode head, tail;
    class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public MyStack() {
        head = null; tail = null;
    }
    
    public void push(int x) {
        ListNode newNode = new ListNode(x);
       if(tail == null) {
            head = newNode;
            tail = newNode;
       } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode; 
       }
    }
    
    public int pop() {
        int poppedEle = -1;
        if(tail != null) {
            poppedEle = tail.val;
            ListNode newTail = tail.prev;
            if(head == tail) {
                head = newTail;
            }
            tail = newTail;
        }
        return poppedEle;
    }
    
    public int top() {
        return tail!=null? tail.val: -1;
    }
    
    public boolean empty() {
        return head==null;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */