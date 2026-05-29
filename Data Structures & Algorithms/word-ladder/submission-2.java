class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if(beginWord.equals(endWord) || (!wordList.contains(endWord))) {
            return 0;
        }
        Set<String> traversingNodes = new HashSet<String>();

        Queue<Node> bfsTraverse = new LinkedList<Node>();
        bfsTraverse.add(new Node(beginWord, 1));
        while(!bfsTraverse.isEmpty()) {
            Node current = bfsTraverse.poll();
            traversingNodes.add(current.word);
            if(current.word.equals(endWord)) {
                return current.count;
            }
            List<String> nextWords = computeNextTransformation(current.word, wordList, traversingNodes);
            
            for(String word: nextWords) {
                bfsTraverse.add(new Node(word, current.count+1));
            }
        }
        return 0;
        
    }

    List<String> computeNextTransformation(String currentWord, List<String> wordList, Set<String> traversingNodes) {
        List<String> transformations = new ArrayList<String>();
        for(int i=0; i<wordList.size(); i++) {
            if(!traversingNodes.contains(wordList.get(i)) && differByOneChar(currentWord, wordList.get(i))) {
                transformations.add(wordList.get(i));
            }
        }
        return transformations;
    }

    boolean differByOneChar(String word, String otherWord) {
        int diffCount=0;
        for(int i=0; i<word.length(); i++) {
            if(word.charAt(i) != otherWord.charAt(i)) {
                diffCount++;
            }
            if(diffCount > 1) {
                return false;
            }
        }
        return diffCount==1;
    }

    class Node {
        String word;
        int count;
        public Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}
