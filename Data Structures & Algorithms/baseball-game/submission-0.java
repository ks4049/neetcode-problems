class Solution {
    public int calPoints(String[] operations) {
        /*
        Option 1: Using a stack data structure by calling push/pop/peek() operations that are O(1)
        Option 2: Use dynamic arrays/Lists which has similar time complexity
            - Push: Add it to the dynamic array
            - Pop: Remove it from the dynamic array
            - Peek: Look at last element in the dynanmic arrya
        */
        Stack<Integer> opStack = new Stack<Integer>();
        for(int i=0; i<operations.length; i++) {
            switch(operations[i]) {
                case "+": {
                    int lastEle = opStack.pop();
                    int sum = opStack.peek() + lastEle;
                    opStack.push(lastEle);
                    opStack.push(sum);
                    break;
                }
                case "D": {
                    opStack.push(2*opStack.peek());
                    break;
                }
                case "C": {
                    opStack.pop();
                    break;
                }
                default: {
                    opStack.push(Integer.parseInt(operations[i]));
                }
            }
        }

        int totalSum=0;
        while(!opStack.isEmpty()) {
            totalSum+=opStack.pop();
        }
        return totalSum;
        
    }
}