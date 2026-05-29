class Solution {
    public int characterReplacement(String s, int k) {
        int len = s.length(), maxWindowSize = Integer.MIN_VALUE, start=0, maxFrequencySoFar = Integer.MIN_VALUE;
        Map<Character,Integer> frequencyMap = new HashMap<Character,Integer>();
        boolean shrinkWindow = false;

        for(int i=start; i<len;) {
            if(!shrinkWindow) {
                frequencyMap.put(s.charAt(i), frequencyMap.getOrDefault(s.charAt(i), 0)+1);
                if(maxFrequencySoFar < frequencyMap.get(s.charAt(i))) {
                    maxFrequencySoFar = frequencyMap.get(s.charAt(i));
                }
            }
                
            if(i-start-maxFrequencySoFar+1 <= k) {
                // valid window
                if(maxWindowSize < (i-start+1)) {
                    maxWindowSize = i-start+1;
                }
                i++;
                shrinkWindow = false;
            } else {
                // invalid window
                // move the window from front
                frequencyMap.put(s.charAt(start), frequencyMap.get(s.charAt(start))-1);
                if(frequencyMap.get(s.charAt(start))==0) {
                    frequencyMap.remove(s.charAt(start));
                }
                start = start+1;
                shrinkWindow = true;
            }
        }
        return maxWindowSize;
    }


    private Map<Character,Integer> computeFrequencyMap(String str) {
        Map<Character,Integer> charMap = new HashMap<Character,Integer>();
        for(int i=0, len=str.length(); i<len; i++) {
            charMap.put(str.charAt(i), charMap.getOrDefault(str.charAt(i), 0)+1);
        }
        return charMap;
    }


    private int performCharacterReplacement(String s, int k, FrequencyInfo[] frequencyInfoArr) {
        int maxLen = 0, len = s.length();
        for(int i=0; i<26; i++) {
            if(frequencyInfoArr[i] != null) {
                // go over the frequencies
                if(frequencyInfoArr[i].frequency+k < maxLen) {
                    continue;
                }
                int st=-1, stIndex=-1,end=-1, replacements=0; 
                
                for(int j=0; j<frequencyInfoArr[i].occurrences.size();) {
                    int[] currentOccurrence = frequencyInfoArr[i].occurrences.get(j);
                    if(k==0) {
                        // then no need to replace
                        if(maxLen < (currentOccurrence[1]-currentOccurrence[0]+1)) {
                            maxLen = currentOccurrence[1]-currentOccurrence[0]+1;
                        }
                        j++;
                    } else {
                        if(j==0) { // first occurrence
                        st=currentOccurrence[0];
                        stIndex = j;
                        end=currentOccurrence[1];
                        j++;
                    } else {
                        // can current be merged with previous?
                        if(currentOccurrence[0]-end-1 <= (k-replacements)) {
                            replacements+= currentOccurrence[0]-end-1;
                            end = currentOccurrence[1];
                            j++;
                        } else {
                            // cannot be merged?
                            if(maxLen < (end-st+1+(k-replacements))) {
                                maxLen = end-st+1+k-replacements; // record the max so far
                            } 
                            // deduct the replacements and move the window
                            stIndex++;
                            st = frequencyInfoArr[i].occurrences.get(stIndex)[0];
                            end = frequencyInfoArr[i].occurrences.get(stIndex)[0];
                            if(replacements > 0)
                                replacements-=st-frequencyInfoArr[i].occurrences.get(stIndex-1)[1]-1;
                            if(stIndex==j){
                                j++;
                            }
                        }        
                    }

                    }
                    
                }
                if(replacements==0 || k-replacements >= 0) {
                    // no replacement or remaining replacements present performed so far then check if can replace before or after
                    int temp = replacements==0? k: k-replacements;
                    if(st-temp >= 0  || end+temp < len || (st+len-1-end >=temp)) {
                        if(maxLen < end-st+1+temp) {
                            maxLen = end-st+1+temp;
                        }
                    } else {
                        if(maxLen < len) {
                            maxLen = len;
                        }
                    }
                }
            }
        }
        return maxLen;
    }

    private void computeFrequencyInfoArray(String s, FrequencyInfo[] frequencyInfoArr) {
        for(int i=0; i<s.length(); i++) {
            int charIndex = (int)(s.charAt(i) - 'A');
            if(frequencyInfoArr[charIndex] == null) {
                FrequencyInfo frequencyInfo = new FrequencyInfo(1, new ArrayList<>());
                frequencyInfo.occurrences.add(new int[]{i,i});
                frequencyInfoArr[charIndex] = frequencyInfo;
            } else {
                // existing frequency info
                FrequencyInfo frequencyInfo = frequencyInfoArr[charIndex];
                frequencyInfo.frequency++;
                int occurrencesSize = frequencyInfo.occurrences.size();
                // consecutive occurrence, update the existing range
                if(i-frequencyInfo.occurrences.get(occurrencesSize-1)[1] == 1) {
                    frequencyInfo.occurrences.get(occurrencesSize-1)[1] = i;
                } else {
                    // add new entry in occurrences
                    frequencyInfo.occurrences.add(new int[]{i,i});
                }
            }
        }
    }

    public void printResult(FrequencyInfo[] frequencyInfoArr) {
        for(int i=0; i<26; i++) {
            if(frequencyInfoArr[i] != null) {
                System.out.println("For character "+ (char)(i+65) + " the frequency is: " + frequencyInfoArr[i].frequency + " list of occcurences are as follows" );
                for(int j=0; j<frequencyInfoArr[i].occurrences.size(); j++) {
                    System.out.println("[" + frequencyInfoArr[i].occurrences.get(j)[0] + ", " + frequencyInfoArr[i].occurrences.get(j)[1] + "]");
                }
            }
        }
    }

    public static class FrequencyInfo {
        int frequency;
        List<int[]> occurrences = new ArrayList<>();

        FrequencyInfo(int frequency, List<int[]> occurrences) {
            this.frequency = frequency;
            this.occurrences = occurrences;
        }
    }
}
