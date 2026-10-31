class Solution {
    public int climbStairs(int n) {
        if(n <=2) {
            return n;
        }
        int step1 = 1, step2=2, i=3, totalWays=0;   
        while(i<=n) {
            int temp = step1;
            step1 = step2;
            step2 = temp+step1;
            i++;
        }
        return step2;
    }


}
