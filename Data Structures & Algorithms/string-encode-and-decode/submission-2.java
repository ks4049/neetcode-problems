class Solution {


     public String encode(List<String> strs) {
        StringBuilder strBuilder = new StringBuilder();
        for(int m=0, len2=strs.size(); m<len2; m++) {
            String str = strs.get(m);
            strBuilder.append(str.length());
            strBuilder.append("-");
            strBuilder.append(str);
        }
        return strBuilder.toString();
    }

    public List<String> decode(String str) {
        List<String> wordList = new ArrayList<>();
        for(int i=0, len=str.length(); i<len; ) {
            int wordLengthIndex = str.substring(i).indexOf('-')+i;
            int wordLength = Integer.parseInt(str.substring(i, wordLengthIndex));
            wordList.add(str.substring(wordLengthIndex+1, wordLengthIndex+1+wordLength));
            i = wordLengthIndex+1+wordLength;
        }
        return wordList;
    }
}
