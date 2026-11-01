class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] subsequenceLength = new int[text1.length()][text2.length()];
        int text1Len = text1.length(), text2Len = text2.length();
        for(int i=0; i<text1Len; i++) {
            for(int j=0; j<text2Len; j++) {
                boolean isSameChar =(text2.charAt(j)==text1.charAt(i));
                if(i==0) {
                    subsequenceLength[i][j] = isSameChar ? 1: ((j >0)? subsequenceLength[i][j-1]: 0);
                } else if(j==0) {
                    subsequenceLength[i][j] =  isSameChar ? 1: subsequenceLength[i-1][j];
                } else {
                    int currMax = (i>0 && j>0)? (Math.max(subsequenceLength[i-1][j], subsequenceLength[i][j-1])): (i>0 ? subsequenceLength[i-1][j]: subsequenceLength[i][j-1] );
                    if(isSameChar) {
                        currMax = i >0 && j>0? Math.max(currMax, 1+ subsequenceLength[i-1][j-1]): currMax;
                    }
                    subsequenceLength[i][j] = currMax;
                }
            }
        }
        return subsequenceLength[text1Len-1][text2Len-1];
    }
}
