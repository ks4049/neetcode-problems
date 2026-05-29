class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> tempStack = new Stack<Integer>();
        int len = temperatures.length;
        int[] result = new int[len];
        result[len-1] = 0;
        tempStack.push(len-1);
        for(int i=len-2; i>=0; i--) {
            int currentTemp = temperatures[i];
            while(!tempStack.isEmpty() && temperatures[tempStack.peek()] <= currentTemp) {
                tempStack.pop();
            }
            if(!tempStack.isEmpty()) {
                result[i] = tempStack.peek()-i;
            } 
            tempStack.push(i);
        }
        return result;
        
    }
}
