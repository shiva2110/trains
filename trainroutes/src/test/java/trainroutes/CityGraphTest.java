package trainroutes;

import org.junit.Test;

public class CityGraphTest {
	
	public static void main(String[] args) throws Exception {
		String graphPattern = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		CityGraph G = CityGraph.buildGraph(graphPattern);
		
		System.out.println("Running tests on graph " + graphPattern);
		System.out.println();
		
		G.findRouteDistance("A-B-C");
		G.findRouteDistance("A-D");
		G.findRouteDistance("A-D-C");
		G.findRouteDistance("A-E-B-C-D");
		G.findRouteDistance("A-E-D");
		G.findTrips("C", "C", new MaxStopConstraint(3));
		G.findTrips("A", "C", new ExactStopConstraint(4));
		G.shortestRoute("A", "C");
		G.shortestRoute("B", "B");
		G.findTrips("C", "C", new MaxDistanceConstraint(30));
		
	}

	/*@Test
	public void testGraphBuilder() {
		CityGraph G = CityGraph.buildGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		G.findRouteDistance("A-E-D");
	}*/
	
	/*@Test
	public void testGraphSearch() {
		CityGraph G = CityGraph.buildGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		SearchConstraint searchConstraint = new MaxDistanceConstraint(30);
		G.findTrips("C", "C", searchConstraint);
	}*/
	
/*	@Test
	public void testGraphSearchMaxStop() {
		CityGraph G = CityGraph.buildGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		SearchConstraint searchConstraint = new MaxStopConstraint(3);
		G.findTrips("C", "C", searchConstraint);
	}*/
	
	/*@Test
	public void testGraphSearchExactStop() {
		CityGraph G = CityGraph.buildGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		SearchConstraint searchConstraint = new ExactStopConstraint(4);
		G.findTrips("A", "C", searchConstraint);
	}*/
	
	@Test
	public void testShortestRoute() throws Exception {
		CityGraph G = CityGraph.buildGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		G.shortestRoute("A", "C");
	}
}
