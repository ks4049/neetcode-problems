class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> evalStack = new Stack<Integer>();
        for(int i=0,len=tokens.length; i<len; i++) {
            if(isOperator(tokens[i])) {
                int num1 = evalStack.pop(), num2 = evalStack.pop(), result=0;
                switch(tokens[i]) {
                    case "+": result=num2+num1; break;
                    case "-": result=num2-num1; break;
                    case "*": result=num2*num1; break;
                    case "/": result=num2/num1; break;
                }
                evalStack.push(result);
            } else {
                // operand
                evalStack.push(Integer.parseInt(tokens[i]));
            }
        }
        return evalStack.isEmpty()? -1: evalStack.pop();
    }

    private boolean isOperator(String str) {
        return str.equals("*") || str.equals("/") || str.equals("+") || str.equals("-");
    }
}
