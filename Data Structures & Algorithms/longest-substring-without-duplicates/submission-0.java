class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()<=1) {
            return s.length();
        }
        int maxLen = Integer.MIN_VALUE;
        Map<Character,Integer> charIndexMap = new HashMap<Character,Integer>();
        int len=s.length(), st=0;
        for(int i=0; i<len;) {
            // traverse until duplicate found
            
            while(i<len && !charIndexMap.containsKey(s.charAt(i))) {
                charIndexMap.put(s.charAt(i), i);
                i++;
            }

            // record the length
            if((i-st) > maxLen) {
                maxLen = i-st;
            }
          
            int duplicateIndex = i;
            // until all consecutive duplicates are traversed
            while(duplicateIndex<len && charIndexMap.containsKey(s.charAt(duplicateIndex))) {
                if(duplicateIndex+1<len && s.charAt(duplicateIndex) == s.charAt(duplicateIndex+1)){
                    duplicateIndex++;
                } else {
                    duplicateIndex++;
                    break;
                }
            }
            if(duplicateIndex-i > 1) {
                st = duplicateIndex-1;
                i = duplicateIndex-1;
                charIndexMap = new HashMap<>();
            } else if(duplicateIndex!= len && duplicateIndex-i == 1 ) { // only one duplicate found
                int prevIndex = charIndexMap.get(s.charAt(duplicateIndex-1));
                charIndexMap.put(s.charAt(duplicateIndex-1), duplicateIndex-1);
                int newStartIndex = prevIndex+1;
                // clear all entries before the previous index
                while(st < newStartIndex-1) {
                    charIndexMap.remove(s.charAt(st));
                    st++;
                }
                st=newStartIndex; 
                i = duplicateIndex;
            } else if(duplicateIndex==len) {
                i = duplicateIndex;
            }

        }
        return maxLen;

    }
}
