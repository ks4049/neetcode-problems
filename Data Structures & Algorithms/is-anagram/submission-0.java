class Solution {
    public boolean isAnagram(String s, String t) {
        int[] charFrequency = new int[26]; // input can contain only lowercase characters
        for(int i=0, len=s.length(); i<len; i++) {
            charFrequency[s.charAt(i)-'a']++;
        }
        for(int i=0, len=t.length(); i<len; i++) {
            charFrequency[t.charAt(i)-'a']--;
        }
        // check the frequencies are all zero for anagram
        boolean isAnagram = true;
        for(int i=0; i<26; i++) {
            if(charFrequency[i] != 0 ) {
                isAnagram = false;
                break;
            }
        }
        return isAnagram;
    }
}
