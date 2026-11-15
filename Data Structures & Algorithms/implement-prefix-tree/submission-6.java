class PrefixTree {

    TrieNode root;
    
    class TrieNode {
        char ch;
        boolean endOfWord;
        TrieNode[] children;

        public TrieNode(char ch, boolean endOfWord) {
            this.ch = ch;
            this.endOfWord = endOfWord;
        }
    }

    public PrefixTree() {
        this.root = new TrieNode('.', false);
    }

    public void insert(String word) {
        int len = word.length();
        TrieNode curr = root;
        for(int i=0; i<len; i++) {
            int charIndex = word.charAt(i) - 'a';
            if(curr.children != null) {
                if(curr.children[charIndex] == null) {
                    curr.children[charIndex] = new TrieNode(word.charAt(i), (i==len-1));
                } else if(i==len-1){
                    curr.children[charIndex].endOfWord = true;
                }
            } else {
                curr.children = new TrieNode[26];
                curr.children[charIndex] = new TrieNode(word.charAt(i), (i==len-1));
            }
            curr = curr.children[charIndex];
        }
    }

    public boolean search(String word) {
        int len = word.length();
        TrieNode curr = root; 
        for(int i=0; i<len; i++) {
            int charIndex = word.charAt(i) - 'a';
            if(curr == null || curr.children == null || curr.children[charIndex] == null || curr.children[charIndex].ch != word.charAt(i) || (i==len-1 && !(curr.children[charIndex].endOfWord))) {
                return false;
            }
            curr = curr.children[charIndex];
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        int len = prefix.length();
        TrieNode curr = root; 
        for(int i=0; i<len; i++) {
            int charIndex = prefix.charAt(i) - 'a';
            if(curr == null || curr.children == null || curr.children[charIndex] == null || curr.children[charIndex].ch != prefix.charAt(i)) {
                return false;
            }
            curr = curr.children[charIndex];
        }
        return true;
    }
}
