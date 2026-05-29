class Solution {
  
  	Map<Character, String> digitCharMap = new HashMap<>(){{
      put('2', "abc");
      put('3', "def");
      put('4', "ghi");
      put('5', "jkl");
      put('6', "mno");
      put('7', "pqrs");
      put('8', "tuv");
      put('9', "wxyz");
    }};
    public List<String> letterCombinations(String digits) {
      if(digits.length() == 0) {
        return new ArrayList<>();
      }
      List<String> resultList = new ArrayList<>();
      
      generateLetterCombinationsUtil(digits, 0, new StringBuilder(), resultList);
      return resultList;
    }
  	
  	private void generateLetterCombinationsUtil(String digits, int startIndex, StringBuilder stBuilder, List<String> resultList) {
      if(startIndex == digits.length()) {
        resultList.add(new String(stBuilder));
        return;
      }
      
      String digitChars = digitCharMap.getOrDefault(digits.charAt(startIndex), "");
      for(int j = 0; j<digitChars.length(); j++) {
        stBuilder.append(digitChars.charAt(j));
        generateLetterCombinationsUtil(digits, startIndex+1, stBuilder, resultList); // 
        stBuilder.deleteCharAt(stBuilder.length()-1);
      }
    }
  
  /* 34 
   * 3 -> def
   * 			d -> dg, dh, di
   * 			e -> eg, eh, ei
   * 
   * */
  
}
