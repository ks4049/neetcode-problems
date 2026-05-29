class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        PriorityQueue<Airport> queue = new PriorityQueue<Airport>((a1,a2) -> Integer.compare(a1.priceSoFar, a2.priceSoFar));
        Map<Integer, List<Airport>> adjacencyMap = computeAdjacencyMap(n, flights);
        queue.add(new Airport(src, 0, 0));
        int cheapestPrice=Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            Airport current = queue.poll();
            // if already reached the destination, return the cheapest price so far
            if(current.destination == dst) {
                cheapestPrice = current.priceSoFar;
                break;
            }
            List<Airport> neighbors = adjacencyMap.getOrDefault(current.destination, new ArrayList<>());
            for(int i=0; i<neighbors.size(); i++) {
                if(current.stops < k || neighbors.get(i).destination == dst) {
                    queue.add(new Airport(neighbors.get(i).destination, current.priceSoFar + neighbors.get(i).priceSoFar, neighbors.get(i).destination==dst? current.stops: current.stops+1));
                }
            }
        }
        return cheapestPrice==Integer.MAX_VALUE?-1:cheapestPrice;

        
    }


    Map<Integer,List<Airport>> computeAdjacencyMap(int n, int[][] flights) {
        Map<Integer, List<Airport>> adjacencyMap = new HashMap<>();
        for(int i=0; i<flights.length; i++) {
            List<Airport> airports = adjacencyMap.getOrDefault(flights[i][0], new ArrayList<>());
            airports.add(new Airport(flights[i][1], flights[i][2], 0));
            adjacencyMap.put(flights[i][0], airports);
        }
        return adjacencyMap;
    }

    public class Airport {
        int destination;
        int priceSoFar;
        int stops;

        Airport(int destination, int priceSoFar, int stops) {
            this.destination = destination;
            this.priceSoFar = priceSoFar;
            this.stops = stops;
        }
    }
}
