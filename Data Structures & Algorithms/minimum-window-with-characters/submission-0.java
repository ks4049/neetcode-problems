class Solution {
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) {
            return "";
        } else if(s.equals(t)) {
            return s;
        }
        int[] tCount = computeCharCount(t);
        int[] sCount = new int[52];
        int len = s.length(), tLen = t.length(), start=0, temp=start, minLen = Integer.MAX_VALUE, minStart=-1, minEnd=-1;
        boolean shrinkWindow=false, reachedFinal=false;
      
        for(int i=start; i<len;) {
            // if shrink window then it indicates i still remains the same and is already recorded
            if(!shrinkWindow && !reachedFinal) {
                int charIndex = resolveCharIndex(s.charAt(i));
                sCount[charIndex]++;
            }
            
            if(i-start+1 >= tLen) {
                //System.out.println("In here for i "+ i + " and start "+ start);
                if(compareCharCount(sCount, tCount)) {
                    //printCharCounts(sCount, tCount);
                    // record the minimum length
                    //System.out.println("Recording here with start as" + start + " end as " + i+1 + " and substring value is " + s.substring(start, i+1));
                    if(minLen > i-start+1) {
                        minLen = i-start+1;
                        minStart = start;
                        minEnd = i+1;
                    }
                    // reduce the window size
                    int charIndexAtStart = resolveCharIndex(s.charAt(start));
                    sCount[charIndexAtStart]--;
                    temp = start+1;
                    // jump to the next index which exists in t
                    while(temp<len && temp <= i && tCount[resolveCharIndex(s.charAt(temp))] == 0) {
                        sCount[resolveCharIndex(s.charAt(temp))]--;
                        temp++;
                    }
                    start = temp;
                    shrinkWindow = true;
                    
                } else {
                    shrinkWindow = false;
                    if(reachedFinal) {
                        break;
                    }
                    if(i!=len-1) {
                        i++;
                    } else {
                        reachedFinal = true;
                    }
                     // expand the window size until condition is met
                    
                }
            } else {
                shrinkWindow = false;
                if(reachedFinal) {
                    break;
                }
                if(i!= len-1)
                    i++;
                else 
                    reachedFinal = true;
            }
        }
        return minLen == Integer.MAX_VALUE? "": s.substring(minStart, minEnd); 
    }

    private boolean compareCharCount(int[] sCount, int[] tCount) {
        for(int i=0; i<52; i++) {
            if(tCount[i]!=0 && (sCount[i] < tCount[i])) {
                return false;
            }
        }
        return true;
    }

    // compute the character count in the string
    private int[] computeCharCount(String str) {
        int[] charCount = new int[52];
        
        for(int i=0; i<str.length(); i++) {
            int charCode = resolveCharIndex(str.charAt(i));
            charCount[charCode]++;
        }
        // Upper case letters followed by lower case letters
        return charCount;
    }

    private void printCharCounts(int[] sCount, int[] tCount) {
        for(int i=0; i<52; i++) {
            if(tCount[i] != 0) {
                System.out.println("Counts for character are sCount and tCount: "+sCount[i] + ", "+ tCount[i]) ;
            }
        }

    }

    private int resolveCharIndex(char ch) {
        int charCode = -1;
        if(ch >= 'a' && ch <='z') {
            charCode = (int)(ch - 'a')+26;
        } else if(ch >= 'A' && ch<= 'Z'){
            charCode = (int)(ch - 'A');
        }
        return charCode;
    }
}
