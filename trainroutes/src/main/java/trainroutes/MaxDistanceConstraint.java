package trainroutes;

public class MaxDistanceConstraint implements SearchConstraint{

	int maxDist;
	
	public MaxDistanceConstraint(int maxDist) {
		this.maxDist = maxDist;
	}
	
	public boolean isValidTrip(SearchStats stats) {
		if(stats.distanceCovered>0 && stats.distanceCovered <= maxDist) {
			return true;
		} else {
			return false;
		}
		
	}

	public boolean isEndSearch(SearchStats stats) {
		if(stats.distanceCovered >= maxDist) {
			return true;
		} else {
			return false;
		}		
	}

}
