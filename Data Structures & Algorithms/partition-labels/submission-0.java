class Solution {
    public List<Integer> partitionLabels(String s) {
        /*
        - compute the last occurrence of every character in the string
        - Traverse through the string
        - For every character expand the the farthest index upto which the characters will be present in current parition
        - Add the parition to the result and move the pointer to next partition
        */
        int[] lastOccurrence = computeLastOccurrence(s);
        List<Integer> partitions = new ArrayList<>();
        int start,end;
        for(int i=0; i<s.length(); ) {
            start=i;
            end=lastOccurrence[s.charAt(i)-'a'];
            while(i<=end) {
                end = Math.max(end, lastOccurrence[s.charAt(i)-'a']);
                i++;
            }
            partitions.add(end-start+1);
        }
        return partitions;
    }

    private int[] computeLastOccurrence(String s) {
        int[] lastOccurrence = new int[26];
        for(int i=0; i<s.length(); i++) {
            int charCode = (int)s.charAt(i)-'a';
            lastOccurrence[charCode] = i;
        }
        return lastOccurrence;
    }
}
