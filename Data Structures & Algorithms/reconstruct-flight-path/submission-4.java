// form the itinerary
        /*
        Form the adjacency list for the given tickets Map<String, List<String>>
        Compute the string value(sum of the ascii codes of the characters)
        Visited tickets: (source,target)
        Maintain a priority queue and add the source(JFK)
            JFK
                HOU,SEA
                HOU
                    JFK
                JFK
                    SEA
                    
        */
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
      Map<String,PriorityQueue<String>> adjacencyList = computeAdjacencyList(tickets);
      
      
      List<String> itinerary = new ArrayList<String>();
      dfs(adjacencyList, "JFK", itinerary);
      Collections.reverse(itinerary);
      return itinerary;
    }


    private void dfs(Map<String, PriorityQueue<String>> adjacencyMap, String node, List<String> itinerary) {
        PriorityQueue<String> edges = adjacencyMap.getOrDefault(node, new PriorityQueue<String>());
        while(!edges.isEmpty()) {
            String neighbor = edges.poll();
            dfs(adjacencyMap, neighbor, itinerary);
        }
        itinerary.add(node);
    }
  
  	private Map<String,PriorityQueue<String>> computeAdjacencyList(List<List<String>> tickets) {
      Map<String,PriorityQueue<String>> adjacencyMap = new HashMap<>();
      for(int i=0; i<tickets.size(); i++) {
        List<String> currentTicket = tickets.get(i);
        PriorityQueue<String> currentTickets = adjacencyMap.getOrDefault(currentTicket.get(0), new PriorityQueue<>());
        currentTickets.add(currentTicket.get(1));
        adjacencyMap.put(currentTicket.get(0), currentTickets);
      }
      return adjacencyMap;
    }
}
