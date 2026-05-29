/*
A prefix tree (also known as a trie) is a tree data structure used to efficiently store and retrieve keys in a set of strings. 
Some applications of this data structure include auto-complete and spell checker systems.

Implement the PrefixTree class:

PrefixTree() Initializes the prefix tree object.
void insert(String word) Inserts the string word into the prefix tree.
boolean search(String word) Returns true if the string word is in the prefix tree (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
*/

class PrefixTree {
	
    Node root;
    public PrefixTree() {
      this.root = new Node('0', false, new Node[26]);
    }

    public void insert(String word) {
      if(word.length() == 0) {
        return;
      }
      Node parent = root;
      for(int i=0; i<word.length(); i++) {
        char ch = word.charAt(i);
        boolean endOfWord = i!=word.length()-1? false: true;
        if(parent.children[ch-'a'] == null) {
          parent.children[ch-'a'] = new Node(ch, endOfWord, new Node[26]);
        } else if(endOfWord){
            parent.children[ch-'a'].endOfWord = endOfWord;
        }
         parent = parent.children[ch-'a'];
      }
    }

    public boolean search(String word) {
      Node parent = root;
      int len = word.length();
      for(int i=0; i<len; i++) {
        char ch = word.charAt(i);
        if(parent.children==null || parent.children[ch-'a']==null) {
          return false;
        }
        if(i==len-1) {
          return parent.children[ch-'a'].endOfWord;
        }
        parent = parent.children[ch-'a'];
      }
      return false;
    }

    public boolean startsWith(String prefix) {
      Node parent = root;
      int len = prefix.length();
      for(int i=0; i<len; i++) {
        char ch = prefix.charAt(i);
        if(parent.children==null || parent.children[ch-'a']==null) {
          return false;
        }
        parent = parent.children[ch-'a'];
      }
      return true;
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
