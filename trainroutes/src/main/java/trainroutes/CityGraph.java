package trainroutes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityGraph {

		private Map<String, City> cities = new HashMap<String, City>();
		private static final String NO_ROUTE_MSG = "NO SUCH ROUTE";
		
		private CityGraph() {
			
		}
		
		public static void buildGraphFromFile(String file) {
			
		}
		
		public static CityGraph buildGraph(String pattern) {
			String[] routes = pattern.split(",");			
			CityGraph G = new CityGraph();
			
			for(String routeStr: routes) {
				routeStr = routeStr.trim();
				if(routeStr.length()!=3) {
					System.out.println("The pattern " + routeStr + " is wrong!");
				}
				
				try {
					String id1 = String.valueOf(routeStr.charAt(0));
					String id2 = String.valueOf(routeStr.charAt(1));
					String distStr = String.valueOf(routeStr.charAt(2));				
					int dist = Integer.parseInt(distStr);
					
					G.addCity(id1);
					G.addCity(id2);
					G.addConnections(id1, id2, dist);
					
				} catch(Exception e) {
					System.out.println("The pattern " + routeStr + " is wrong! Failed to parse with an exception " + e.getMessage());
				}			
			}
			
			return G;
		}
 		
		private void addCity(City city) {
			if(!cities.containsKey(city.cityId)) {
				cities.put(city.cityId, city);
			}			
		}
		
		private void addCity(String cityId) {
			if(!cities.containsKey(cityId)) {
				cities.put(cityId, new City(cityId));
			}
		}
		
		private void addConnections(String A, String B, int dist) {
			if(cities.containsKey(A) && cities.containsKey(B)) {
				 City cityA = cities.get(A);
				 cityA.addConnection(B, dist);
			}
		}
		
		public String findRouteDistance(String pathPattern) {
			
			if(pathPattern == null || pathPattern.length()==0) {
				throw new IllegalArgumentException("pathPattern is null or invalid");
			}
			
			String[] path = pathPattern.split("-");
			
			
			City curCity = cities.get(path[0]);
			if(curCity==null) {
				System.out.println(NO_ROUTE_MSG);
				return NO_ROUTE_MSG;
			}
			
			int index = 1;
			int distance = 0;
			while(index < path.length) {				
				if(!curCity.connections.containsKey(path[index])) {
					System.out.println("The distance of the route " + pathPattern + ": " + NO_ROUTE_MSG);
					return NO_ROUTE_MSG;
				} 
				
				distance = distance + curCity.connections.get(path[index]);
				curCity = cities.get(path[index]);
				index++;
			}
			
			String distStr = String.valueOf(distance);
			System.out.println("The distance of the route " + pathPattern + ": " + distStr);
			return distStr;			
		}
		
		public int findTrips(String source, String destination, SearchConstraint searchConstraint) {
			
			if(!cities.containsKey(source) || !cities.containsKey(destination)) {
				throw new IllegalArgumentException("The source and destination does not exist in this graph!");
			}
			
			List<String> allValidTrips = new ArrayList<String>(); 
			findTripsDFS(source, destination, searchConstraint, new SearchStats(0,0), "", allValidTrips);
			
			System.out.println("Number of trips from " + source + " to " + destination + ": " + allValidTrips.size());
			System.out.print("\t");
			for(String trip: allValidTrips) {
				System.out.print(trip + ", ");
			}
			System.out.println();
			
			return allValidTrips.size();
			
		}
		
		private void findTripsDFS(String curNode, 
				String destination,
				SearchConstraint searchConstraint, 
				SearchStats stats, 
				String curTrip,
				List<String> trips) {
			
			
			if(curNode.equals(destination) && searchConstraint.isValidTrip(stats)) {
				trips.add(curTrip + "-" + curNode);
			}
			
			if(searchConstraint.isEndSearch(stats)) {
				return;
			}
			
			City curCity = cities.get(curNode);
			for(String nextNode: curCity.connections.keySet()) {
				int dist = stats.distanceCovered + curCity.connections.get(nextNode);
				SearchStats updatedStats = new SearchStats(dist, stats.stopsCovered+1);
				findTripsDFS(nextNode, destination, searchConstraint, updatedStats, curTrip + "-" + curNode , trips);
			}
		}
		
		public class Path {
			public String cityid;
			public String parentCityId;	
			
			public Path(String cityId, String parent) {
				this.cityid = cityId;
				this.parentCityId = parent;
			}
		}
		
		public int shortestRoute(String source, String dest) throws Exception {
			
			if(!cities.containsKey(source) || !cities.containsKey(dest)) {
				throw new IllegalArgumentException("The source and destination does not exist in this graph!");
			}
			
			Map<String, Path> exploredNodes = new HashMap<String, Path>();
			MinHeap<Path> minHeap = new MinHeap<Path>();
			
			
			Path curNode = new Path(source, null);
			exploredNodes.put(curNode.cityid, curNode);
			int distanceToCurNode =  0;
			
			do {
				
				City curCity = cities.get(curNode.cityid);
				for(String adjCity: curCity.connections.keySet()) {
					if(adjCity.equals(dest)) {
						String cityStr = adjCity + "(target)";
						int adjCityDist = curCity.connections.get(adjCity);
						Path path = new Path(cityStr, curNode.cityid);
						minHeap.add(path, adjCityDist + distanceToCurNode);
					} else if(!exploredNodes.containsKey(adjCity)) {
						int adjCityDist = curCity.connections.get(adjCity);
						Path path = new Path(adjCity, curNode.cityid);
						minHeap.add(path, adjCityDist + distanceToCurNode);
					} 
				}
				
				HeapNode<Path> nextHeapNode = minHeap.extractMin();
				if(nextHeapNode == null) {
					break;
				}
				exploredNodes.put(nextHeapNode.data.cityid, nextHeapNode.data);
				curNode = nextHeapNode.data;
				distanceToCurNode = nextHeapNode.val;
			} while(!curNode.cityid.equals(dest + "(target)"));
				
			if(curNode.cityid.equals(dest + "(target)")) {
				System.out.println("Shortest distance from " + source + " to " + dest + ": " + distanceToCurNode);
				String s = "";
				Path path = exploredNodes.get(curNode.cityid);
				while(path!=null) {
					s = path.cityid + "->" + s;
					path = exploredNodes.get(path.parentCityId);
				}
				System.out.println("\t path: " + s);
			} else {
				throw new Exception("Destination is not found within the search limits!");
			}
			
			
			return distanceToCurNode;
			
		}
}
