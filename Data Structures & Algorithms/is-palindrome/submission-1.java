class Solution {
    public boolean isPalindrome(String s) {
        int low=0, high =s.length()-1, len=s.length();
        while(low < high) {
            boolean isLowAlphaNumeric = isAlphaNumeric(s.charAt(low));
            boolean isHighAlphaNumeric = isAlphaNumeric(s.charAt(high));
            if(isLowAlphaNumeric && isHighAlphaNumeric) {
                if(Character.toLowerCase(s.charAt(low)) != Character.toLowerCase(s.charAt(high))) {
                    return false;
                }
                low++;
                high--;
            } else {
                if(!isLowAlphaNumeric) {
                    low++;
                }
                if(!isHighAlphaNumeric) {
                    high--;
                }
            }
        }
        return true;
    }

    boolean isAlphaNumeric(char ch) {
        return (ch >= 'a' && ch <='z') || (ch >='A' && ch<='Z') || (ch >='0' && ch<='9');
    }
}
