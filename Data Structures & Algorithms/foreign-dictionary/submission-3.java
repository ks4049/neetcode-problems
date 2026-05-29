class Solution {
    public String foreignDictionary(String[] words) {
     if(words.length==1 || (words.length == 2 && words[0].equals(words[1]))) {
        return words[0];
     }
      Set<Character> distinctLetters = new HashSet<Character>();
      for(int i=0; i<words.length; i++) {
        for(int j=0; j<words[i].length(); j++) {
            distinctLetters.add(words[i].charAt(j));
        }
      }
      /* 
      1. perform the comparison between the consecutive strings
      2. compute the edge
      3. Check if by adding the edge it forms a cycle or not?
      4. If cycle can be formed, return empty string(as no valid ordering can be formed)
      5. If no cycle, then add the edge to the adjacencyMap
      6. Finally compute the topological sorting for the adjacencyMap and also add the left over letters from the dictionary
      7. Return the final ordering
      */
      Map<Character,Set<Character>> adjacencyMap = new HashMap<>();
      for(int i=1; i<words.length; i++) {
        char[] edge = computeCharOrdering(words[i-1], words[i]);
        if(edge[0] =='-' && edge[1] == '-') {
            if(words[i-1].length() > words[i].length()) {
                return "";
            }
            continue;
        }
        if(!canCycleBeFormed(edge, adjacencyMap)) {
           // add edge to adjacencyMap
           Set<Character> existingEdges = adjacencyMap.getOrDefault(edge[0], new HashSet<Character>());
           existingEdges.add(edge[1]);
           adjacencyMap.put(edge[0], existingEdges);
        } else {
            return "";
        }
      }
      Set<Character> visitedChars = new HashSet<Character>();
      // add remaining characters
      List<Character> resultList = new ArrayList<Character>();
      for(char ch: adjacencyMap.keySet()) {
        if(!visitedChars.contains(ch)) {
            topologicalSort(ch, adjacencyMap, visitedChars, resultList);
        }
      }
      System.out.println("Topological sort" + resultList);
      //visitedChars = new HashSet<>();
      // reverse the resultList order
      StringBuilder result = new StringBuilder();
      for(int i=resultList.size()-1; i>=0; i--) {
        result.append(resultList.get(i));
      }
      // remaining chars to be added
      for(char ch: distinctLetters) {
        if(!visitedChars.contains(ch)) {
            result.append(ch);
        }
      }
      return result.toString();
    }

    char[] computeCharOrdering(String s1, String s2) {
        int i =0, j=0, len1=s1.length(), len2=s2.length();
        while(i<len1 && j<len2 && s1.charAt(i) == s2.charAt(j)) {
            i++;
            j++;
        }
        if(i<len1 && j<len2) {
            return new char[]{s1.charAt(i), s2.charAt(j)};
        }
        return new char[]{'-','-'};
    }


    void topologicalSort(char current, Map<Character,Set<Character>> adjacencyMap, Set<Character> visitedChars, List<Character> resultList) {
        if(visitedChars.contains(current)) {
            return;
        }
        visitedChars.add(current);
        Set<Character> childNodes = adjacencyMap.getOrDefault(current, new HashSet<>());
        for(char child: childNodes) {
            topologicalSort(child, adjacencyMap, visitedChars, resultList);
        }
        resultList.add(current);
    }

    boolean canCycleBeFormed(char[] edge, Map<Character,Set<Character>> adjacencyMap) {
        char initialParent;
        if(!adjacencyMap.containsKey(edge[0]) && !adjacencyMap.containsKey(edge[1])) {
            return false;
        } else if(adjacencyMap.containsKey(edge[0])) {
            // start with edge[1]
            initialParent = edge[0];
        } else {
            //start with edge[0]
            initialParent = edge[1];
        }
        return canCycleBeFormedUtil(initialParent, '-', adjacencyMap);
    }


    boolean canCycleBeFormedUtil(char initialNode, char current, Map<Character,Set<Character>> adjacencyMap) {
        if(initialNode == current) {
            return true;
        }
        if(current == '-') {
            current = initialNode;
        }
        Set<Character> children = adjacencyMap.getOrDefault(current, new HashSet<Character>());
        boolean cycleFormed = false;
        for(char ch: children) {
            if(canCycleBeFormedUtil(initialNode, ch, adjacencyMap)) {
                cycleFormed = true;
                break;
            }
        }
        return cycleFormed;


    }
}
