class Solution {

    public String encode(List<String> strs) {
      StringBuilder result = new StringBuilder();
      for(int i=0; i<strs.size(); i++) {
        result.append(strs.get(i).length());
        result.append("-");
        result.append(strs.get(i));
      }
      return result.toString();

    }

    public List<String> decode(String str) {
      List<String> result = new ArrayList<>();
      for(int i=0, len=str.length(); i<len;) {
        int j=i, strLen=0;
        while(j<len && str.charAt(j)!='-') {
          j++;
        }
        strLen = Integer.parseInt(str.substring(i,j));
        //jth index will be -
        result.add(str.substring(j+1, j+strLen+1));
        i=j+strLen+1;
      }
      return result;

    }
}
