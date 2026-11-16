class WordDictionary {
    class TrieNode {
        char ch;
        boolean endOfWord;
        TrieNode[] children;
        public TrieNode(char ch) {
            this.ch = ch;
        }
    }
    TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode('.');
    }

    public void addWord(String word) {
        TrieNode curr = root;
        int len = word.length();
        for(int i=0; i<len; i++) {
            int charIdx = word.charAt(i)-'a';
            if(curr.children == null) {
                curr.children = new TrieNode[26];
            }
            if(curr.children[charIdx] == null) {
                curr.children[charIdx] = new TrieNode(word.charAt(i));
                curr.children[charIdx].endOfWord = i==len-1;
            } else if(!curr.children[charIdx].endOfWord){
                curr.children[charIdx].endOfWord = i==len-1;
            }
            curr = curr.children[charIdx];
        }
    }

    public boolean search(String word) {
        /*
        * if . is present then go over all the children search from a particular node and index start and end
        */
        return searchUtil(root, word, 0, word.length());
    }

    private boolean searchUtil(TrieNode curr, String word, int start, int end) {
        if(curr == null || start > end){
            return false;
        }
        if(start == end) {
            return curr.endOfWord;
        }
        if(curr.children == null) {
            return false;
        }
        
        if(word.charAt(start) == '.') {   
            boolean searchNextLevel = false;
            for(int i=0; i<curr.children.length; i++) {
                if(curr.children[i] !=null) {
                    searchNextLevel = searchUtil(curr.children[i], word, start+1, end);
                    if(searchNextLevel) {
                        break;
                    }
                }
            }
            return searchNextLevel;
        } else {
            int charIdx = word.charAt(start)-'a'; 
            return curr.children[charIdx]!=null && curr.children[charIdx].ch == word.charAt(start) && searchUtil(curr.children[charIdx], word, start+1, end);
        }
    }
}
