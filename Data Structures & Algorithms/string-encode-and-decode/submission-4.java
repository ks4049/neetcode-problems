class Solution {

    public String encode(List<String> strs) {
        if(strs.size()==0){
            return "";
        }
        StringBuilder encodedStr= new StringBuilder();
        for(int i=0; i<strs.size(); i++) {
            encodedStr.append(strs.get(i).length());
            encodedStr.append("-");
            encodedStr.append(strs.get(i));
        }
        return encodedStr.toString();
    }

    public List<String> decode(String str) {
        if(str.length()==0) {
            return new ArrayList<>();
        }
        List<String> decodedStrs = new ArrayList<>();
        for(int i=0,len=str.length(); i<len; i++) {
            // loop through string until you find the first hyphen
            int j=i;
            String frequency = "";
            while(str.charAt(j) != '-') {
                frequency+=str.charAt(j);
                j++;
            }
            int countAsNum = Integer.valueOf(frequency);
            String actualStr = str.substring(j+1, j+1+countAsNum);
            i = j+countAsNum;
            decodedStrs.add(actualStr);
        }
        return decodedStrs;

    }
}
