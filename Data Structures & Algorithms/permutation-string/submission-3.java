class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) {
            return false;
        }
        Map<Character,Integer> s1Map= frequencyMap(s1);
        Map<Character,Integer> s2Map = new HashMap();
        Map<Character,Integer> s2IndexMap = new HashMap();
        int matchCount =0;

        // look for permutation in s2
        int len=s2.length(), s1Len = s1.length();
        boolean foundInclusion=false;
        for(int i=0; i<len; i++) {
            if(matchCount == s1Len){
                foundInclusion = true;
                break;
            } 
            if(s1Map.containsKey(s2.charAt(i))) {
                if(s2Map.getOrDefault(s2.charAt(i), 0)< s1Map.get(s2.charAt(i))) {
                    s2Map.put(s2.charAt(i), s2Map.getOrDefault(s2.charAt(i), 0)+1);
                    if(s2Map.get(s2.charAt(i))==1) {
                        s2IndexMap.put(s2.charAt(i), i); // store first occurrence of character in window
                    }
                    matchCount++;
                } else {
                    // in order to make for the char at index i, shrink window to first occurrence of char
                    int shrinkIdx = s2IndexMap.get(s2.charAt(i));
                    s2Map = new HashMap<>();//reset and then repopulate
                    s2IndexMap = new HashMap<>();
                    int k = shrinkIdx+1;
                    matchCount=0;
                    while(k <= i) {
                      if(!s2IndexMap.containsKey(s2.charAt(k))){
                        s2IndexMap.put(s2.charAt(k), k); // update the first occurrence of new window
                      }
                      s2Map.put(s2.charAt(k), s2Map.getOrDefault(s2.charAt(k), 0)+1);
                      matchCount++;
                      k++;
                    }
                }
            } else{
                s2Map = new HashMap<>(); //reset s2Map and continue 
                s2IndexMap = new HashMap<>();
                matchCount=0;
            }
        }
        
        return foundInclusion || matchCount == s1Len;
        
    }

    private Map<Character,Integer> frequencyMap(String s) {
        Map<Character,Integer> freqMap = new HashMap<>();
        
        for(char ch: s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
        }
        return freqMap;
    }
}
