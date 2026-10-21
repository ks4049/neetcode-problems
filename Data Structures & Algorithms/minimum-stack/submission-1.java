class MinStack {
    Stack<Integer> primaryStack;
    Stack<Integer> minStack;

    public MinStack() {
        primaryStack  = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int val) {
        primaryStack.push(val);
        if(minStack.isEmpty()) {
            minStack.push(val);
        } else {
            if(minStack.peek() > val) {
                minStack.push(val);
            } else {
                minStack.push(minStack.peek());
            }
        }
    }
    
    public void pop() {
        primaryStack.pop();
        minStack.pop();
    }
    
    public int top() {
        return primaryStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
