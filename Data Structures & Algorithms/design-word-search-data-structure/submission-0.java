/*
Design Add and Search Word Data Structure
Design a data structure that supports adding new words and searching for existing words.

Implement the WordDictionary class:

void addWord(word) Adds word to the data structure.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
*/
class WordDictionary {

  	Node root;
    public WordDictionary() {
      this.root = new Node('0', false, new Node[26]);
    }
   

    public void addWord(String word) {
      if(word.length() == 0) {
        return;
      }
      Node parent = root;
      for(int i=0; i<word.length(); i++) {
        char ch = word.charAt(i);
        boolean endOfWord = i!=word.length()-1? false: true;
        if(parent.children[ch-'a'] == null) {
          parent.children[ch-'a'] = new Node(ch, endOfWord, new Node[26]);
        }
         parent = parent.children[ch-'a'];
      }

    }

    public boolean search(String word) {
      if(word.length()==0) {
        return false;
      }
     	return searchUtil(word, root, 0);
    }
  
  	private boolean searchUtil(String word, Node parent, int index) {
      if(index == word.length()) {
        return true;
      }
      char ch = word.charAt(index);
      if(ch != '.') {
        if(parent.children==null || parent.children[ch-'a']==null) {
			return false;
        } else { 
          if(index == word.length()-1) {
          	return parent.children[ch-'a'].endOfWord;  
          }
          return searchUtil(word, parent.children[ch-'a'], index+1);
        }
      } else {
        if(parent.children==null){
          return false;
        }
        boolean findSuffix = false;
        boolean isLastIndex = index == word.length()-1;
        for(int i=0; i<parent.children.length; i++) {
          if(parent.children[i]!=null) {
            if(!isLastIndex) {
            	findSuffix = searchUtil(word, parent.children[i], index+1);
            } else {
              findSuffix = parent.children[i].endOfWord;
            }
           if(findSuffix) {
              break;
            }  
          }
        }
        return findSuffix;
      }
    }
  
   public class Node {
    char value;
    boolean endOfWord;
    Node[] children;
    
    public Node(char value, boolean endOfWord, Node[] children) {
      this.value = value;
      this.endOfWord = endOfWord;
      this.children = children;
    }
    
    public Node(char value, boolean endOfWord) {
      this.value = value;
      this.endOfWord = endOfWord;
    }
    
  }
}
