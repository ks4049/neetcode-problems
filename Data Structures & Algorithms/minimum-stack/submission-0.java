class MinStack {
    Stack<Integer> actualOrder = new Stack<Integer>();
    Stack<Integer> minOrder = new Stack<Integer>();

    public MinStack() {   
    }
    
    public void push(int val) {
        actualOrder.push(val);
        if(minOrder.isEmpty()) {
            minOrder.push(val);
        } else {
            Stack<Integer> tempStack = new Stack<Integer>();
            while(!minOrder.isEmpty() && minOrder.peek() < val) {
                tempStack.push(minOrder.pop());
            }
            minOrder.push(val);
            while(!tempStack.isEmpty()) {
                minOrder.push(tempStack.pop());
            }
        }
    }
    
    public void pop() {
        int poppedEle = actualOrder.pop();
        Stack<Integer> tempStack = new Stack<Integer>();
        while(!minOrder.isEmpty() && minOrder.peek() != poppedEle) {
            tempStack.push(minOrder.pop());
        }
        if(!minOrder.isEmpty() && minOrder.peek() == poppedEle) {
            minOrder.pop();
            while(!tempStack.isEmpty()) {
                minOrder.push(tempStack.pop());
            }
        }
    }
    
    public int top() {
        return actualOrder.peek();
    }
    
    public int getMin() {
        return minOrder.peek();
    }

}
