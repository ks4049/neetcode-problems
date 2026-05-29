class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.equals(s2)) {
            return true; // its a permutation
        } else if(s2.length() < s1.length()) {
            return false;
        }
        int[] s1Count = computeCharCount(s1);
        int[] s2Count = new int[26];
        int start=0;
        boolean isPermute=false;
        for(int i=start; i<s2.length(); i++) {
            s2Count[s2.charAt(i)-'a']++;
            if(i-start+1 >= s1.length()) {
                if(compareCharCount(s1Count, s2Count)) {
                    isPermute = true;
                    break;
                } else {
                    s2Count[s2.charAt(start)-'a']--;
                    int temp = start+1;
                    // shrink in front
                    while(temp<=i && s1Count[s2.charAt(temp)-'a'] == 0) {
                        s2Count[s2.charAt(temp)-'a']--;// remove from 
                        temp++;
                    }
                    start = temp;
                }
            }
        }
        return isPermute;

        
    }

    private int[] computeCharCount(String str) {
        // contains only lower case characters
        int[] count = new int[26];
        for(int i=0; i<str.length(); i++) {
            int charCode = str.charAt(i)-'a';
            count[charCode]++;
        }
        return count;
    }

    private boolean compareCharCount(int[] s1Count, int[] s2Count) {
        for(int i=0; i<26; i++) {
            if(s1Count[i] != s2Count[i]) {
                return false;
            }
        }
        return true;
    }
}
    
