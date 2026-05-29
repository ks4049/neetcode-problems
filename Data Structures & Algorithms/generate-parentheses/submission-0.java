/*
 * Generate Parentheses
   You are given an integer n. Return all well-formed parentheses strings 
   that you can generate with n pairs of parentheses.
 * */

class Solution {
    public List<String> generateParenthesis(int n) {
      if(n==0) {
        return new ArrayList<>();
      }
    	if(n==1) {
        return Arrays.asList("()");
      }
      int maxLevel = n,currLevel=1;
      Set<String> visited = new HashSet<String>();
      Queue<String> levelOrder = new LinkedList<String>();
      visited.add("()");
      levelOrder.add("()");
      while(!levelOrder.isEmpty() && currLevel<maxLevel) {
        int levelSize = levelOrder.size();
        for(int i=0; i<levelSize; i++) {
          // generate the parantheses
          String curr = levelOrder.poll();
          generateParantheses(levelOrder, curr, visited);
        }
        currLevel++;
      }
      List<String> result = new ArrayList<>();
      while(!levelOrder.isEmpty()) {
        result.add(levelOrder.poll());
      }
      return result;
    }
  
  private void generateParantheses(Queue<String> levelOrder, String curr, Set<String> visited) {
    
    for(int i=0; i<curr.length(); i++) {
      if(curr.charAt(i) == ')') {
        String frontParan = String.format("%s()%s", curr.substring(0,i), curr.substring(i));
        if(!visited.contains(frontParan)) {
          visited.add(frontParan);
          levelOrder.add(frontParan);
        }
       
          String backParan = String.format("%s()%s", curr.substring(0,i+1), (i+2)>curr.length()? "":curr.substring(i+1));
          if(!visited.contains(backParan)) {
            visited.add(backParan);
            levelOrder.add(backParan);
          }
       
      }
    }
    String surroundParan = String.format("(%s)", curr);
    if(!visited.contains(surroundParan)) {
      visited.add(surroundParan);
      levelOrder.add(surroundParan);
    }
  }
  
  
}
