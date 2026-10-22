class MyStack {
    /*
    * Option 1: Implement stack using two queues
      Option 2: Implement stack using one double ended queue(doubly linked list)  
    */
    Queue<Integer> primaryQ;
    Queue<Integer> q2 = new LinkedList<Integer>();

    public MyStack() {
        primaryQ =  new LinkedList<Integer>();
        q2 = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        // enqueue all existing elements from primary to q2
        // add the new element to primary 
        // dequeue all elements back from q2 to primary
        while(!primaryQ.isEmpty()) {
            q2.add(primaryQ.remove());
        }
        primaryQ.add(x);
        while(!q2.isEmpty()) {
            primaryQ.add(q2.remove());
        }
    }
    
    public int pop() {
        return primaryQ.poll();
    }
    
    public int top() {
        return primaryQ.peek();
    }
    
    public boolean empty() {
        return primaryQ.isEmpty();
        
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