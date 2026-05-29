class Solution {
    public boolean checkValidString(String s) {
        /*
        (()()))
        ((*)))
        */
        Map<Character,Integer> frequencyMap = computeFrequencyMap(s);
        //(=2,)=1.*=2
        boolean isValid = false;
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else if(s.charAt(i) == ')') {
                if(stack.isEmpty()) {
                    isValid = false;
                    System.out.println("breaking");
                    break;
                }
                stack.pop();
            } else {
                //'*'
                if(frequencyMap.getOrDefault('(',0) < frequencyMap.getOrDefault(')', 0)) {
                    // use the '*' as open braces, push to the stack
                    frequencyMap.put('(', frequencyMap.getOrDefault('(', 0)+1);
                    stack.push('(');
                } else if(frequencyMap.getOrDefault('(',0) > frequencyMap.getOrDefault(')', 0)){
                    // use * as close braces and pop from stack
                    if(stack.isEmpty()) {
                        isValid = false;
                        System.out.println("breaking");
                        break;
                    }
                    frequencyMap.put(')', frequencyMap.getOrDefault(')', 0)+1);
                    stack.pop();
                } else {
                    // consider it as empty string and proceed
                }
                frequencyMap.put('*', frequencyMap.getOrDefault('*', 0)-1);
            }
        }
        System.out.println(frequencyMap);
        return stack.isEmpty() && noLeftOver(frequencyMap);
    }

    private boolean noLeftOver(Map<Character,Integer> frequencyMap) {
        return frequencyMap.getOrDefault('*',0)%2==0 && frequencyMap.getOrDefault(')',0)== frequencyMap.getOrDefault('(',0);
    }
    private Map<Character,Integer> computeFrequencyMap(String s) {
        Map<Character,Integer> frequencyMap= new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            int frequency = frequencyMap.getOrDefault(s.charAt(i), 0);
            frequencyMap.put(s.charAt(i), frequency+1);
        }
        return frequencyMap;
    }
}
