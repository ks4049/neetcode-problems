class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> charMap = new HashMap<>();
        int len = s.length(), maxLen = 0, left=0;
        for(int i=0; i<len; i++) {
            if(!charMap.containsKey(s.charAt(i))) {
                charMap.put(s.charAt(i), i);
            } else {
                // shrink the window size to left+1;
                int index = charMap.get(s.charAt(i));
                while(left<=index) {
                    charMap.remove(s.charAt(left));
                    left++;
                }
                charMap.put(s.charAt(i), i);
            }
            maxLen = Math.max(maxLen, i-left+1);
        }
        return maxLen;
    }
}
