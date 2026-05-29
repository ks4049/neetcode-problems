class Solution {
    public boolean isValid(String s) {
        Stack<Character> charStack = new Stack<Character>();
        for(int i=0; i<s.length(); i++) {
            if(isOpenBracket(s.charAt(i))) {
                charStack.push(s.charAt(i));
            } else {
                // if close bracket validate the close and open brackets
                if(charStack.isEmpty()) {
                    return false;
                } else {
                    char top = charStack.pop();
                    if((s.charAt(i) == ']' && top != '[') || (s.charAt(i) == '}' && top != '{') || (s.charAt(i) == ')' && top != '(')) {
                        return false;
                    }
                }
            }
        }
        return charStack.isEmpty();
        
    }

    boolean isOpenBracket(char ch) {
        return ch == '[' || ch == '{' || ch == '(';
    }
}
