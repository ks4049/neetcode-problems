class Solution {
    Map<Integer,Integer> stepsMap = new HashMap<>();
    public int climbStairs(int n) {
        return climbStairsUtil(n);
    }
    /*
    * Top Down Recursive approach
    */
    private int climbStairsUtil(int n) {
        if(n == 0) {
            return 1;
        }
        if(stepsMap.containsKey(n)) {
            return stepsMap.get(n);
        }
        int possibilities = 0;
        if((n-1) >= 0) {
            possibilities+= climbStairsUtil(n-1);
        }
        if((n-2) >=0) {
            possibilities+=climbStairsUtil(n-2);
        }
        stepsMap.put(n, possibilities);
        return possibilities;
    }


}
