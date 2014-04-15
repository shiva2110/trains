package trainroutes;

import org.junit.Test;

public class CityGraphTest {

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
	
	@Test
	public void testGraphSearchExactStop() {
		CityGraph G = CityGraph.buildGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		SearchConstraint searchConstraint = new ExactStopConstraint(4);
		G.findTrips("A", "C", searchConstraint);
	}
}
