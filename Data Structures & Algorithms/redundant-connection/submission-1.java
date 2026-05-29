class Solution {
    public int[] findRedundantConnection(int[][] edges) {
      	int[] leaders = new int[edges.length];
        fillLeaders(leaders, -1);
        for(int i=0; i<edges.length; i++) {
          if(!canUnion(edges[i], leaders)) {
            return edges[i];
          }
        }
      return new int[0];
        
    }
  
  	private void fillLeaders(int[] leaders, int value) {
      for(int i=0; i<leaders.length; i++){
        leaders[i] = -1;
      }
    }
  
  	private boolean canUnion(int[] edge, int[] leaders)	{
    /*
    
    
    */
      int u = edge[0]-1;
      int v = edge[1]-1;
      // not added yet to the component
      if(leaders[u]== -1 && leaders[v] == -1) {
        leaders[u] = Math.min(u,v)+1;
        leaders[v] = Math.min(u,v)+1;
      } else if(leaders[u] == leaders[v]) {
        return false;
      } else {
        if(leaders[u] == -1) {
          leaders[u] = leaders[v];
        } else if(leaders[v] == -1) {
          leaders[v] = leaders[u];
        } else {
        	int expectedLeader = Math.min(leaders[u], leaders[v]);
            int otherLeader = Math.max(leaders[u], leaders[v]);
          	// update the leaders with the new expectedValue 
          	for(int i=0; i<leaders.length; i++) {
              if(leaders[i]==otherLeader) {
				leaders[i] = expectedLeader;	                
              }
            }
        }
      }
      print(leaders);
      return true;
    }

    void print(int[] leaders) {
        for(int i=0; i<leaders.length; i++) {
            System.out.print(leaders[i] + ",");
        }
        System.out.println();
    }
  
}
