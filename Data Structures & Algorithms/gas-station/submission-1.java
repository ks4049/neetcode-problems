class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas=0, totalCost=0;
        for(int i=0; i<gas.length; i++) {
            totalGas+=gas[i];
            totalCost+=cost[i];
        }
        if(totalCost>totalGas) {
            return -1;
        }
        int start=0, tank = gas[start], i=start+1, prevIndex=i-1, trips=0;
        while(trips < gas.length){
            if(i==gas.length) {
                i = i%gas.length;
                prevIndex=gas.length-1;
            } else {
                prevIndex=i-1;
            }
            System.out.println("before "+ tank + "prevIndex" + prevIndex);
            if(tank-cost[prevIndex] < 0) {
                System.out.println("Resetting the start to "+ (start+1));
                start = start+1;
                i=start+1;
                prevIndex=start;
                tank = gas[start];
                System.out.println("tank value and prevIndex "+ tank + "prevIndex" + prevIndex);
                trips=0;
            } else {
                tank = tank+gas[i]-cost[prevIndex];
                i++;
                trips++;
            }
        } 
        
        return start;
    }
}
