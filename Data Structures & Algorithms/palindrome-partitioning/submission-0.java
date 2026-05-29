class Solution {
    public List<List<String>> partition(String s) {
      if(s.length() == 0) {
        return new ArrayList<>();
      } 
      List<List<String>> resultList = new ArrayList<>();
      partitionPalindromeUtil(s, 0, new ArrayList<String>(), resultList);
      return resultList;
    }
  /*
   * (s, 0, [], [])
   * 		(s, 1 [a], [])
   * 			(s, 2 [a, b], [])
   * 				(s, 3 [a, b, b], [])
   *  				(s, 4 [a, b, b, a], [])
   * 		(s, 1, [a, bb, c])
 * */ 	
  
  	private void partitionPalindromeUtil(String s, int startIndex, List<String> currentList, List<List<String>> resultList) {
      if(startIndex == s.length()) {
        resultList.add(new ArrayList<>(currentList));
        return;
      }
      for(int i=startIndex; i<s.length(); i++) {
        // check if startIndex to i is a palindrome
        String currentSubStr = s.substring(startIndex, i+1);
        if(isValidPalindrome(currentSubStr)) {
          currentList.add(currentSubStr);
          partitionPalindromeUtil(s, i+1, currentList, resultList);
          currentList.remove(currentList.size()-1);
        }
      }
    }
  
  	private boolean isValidPalindrome(String str) {
      if(str.length() <=1) {
        return true;
      }
      int st=0, end=str.length()-1;
      boolean isPalindrome = false;
      while(st< end) {
        if(str.charAt(st) == str.charAt(end)) {
          isPalindrome = true;
          st++;
          end--;
        } else {
          isPalindrome = false;
          break;
        }
      }
      return isPalindrome;
    }
}
