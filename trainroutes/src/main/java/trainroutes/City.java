package trainroutes;

import java.util.HashMap;
import java.util.Map;

public class City {
	public String cityId;
	Map<String, Integer> connections = new HashMap<String, Integer>();
	
	public City(String id) {
		this.cityId = id;
	}
	
	public void addConnection(String id, int dist) {
		this.connections.put(id, dist);
	}
	
}
