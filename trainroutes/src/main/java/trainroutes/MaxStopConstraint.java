package trainroutes;

public class MaxStopConstraint implements SearchConstraint{

	int maxStops;
	
	public MaxStopConstraint(int maxStops) {
		this.maxStops = maxStops;
	}
	
	public boolean isValidTrip(SearchStats stats) {
		if(stats.stopsCovered >0 && stats.stopsCovered<=maxStops) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEndSearch(SearchStats stats) {
		if(stats.stopsCovered >= maxStops) {
			return true;
		} else {
			return false;
		}
	}

}
