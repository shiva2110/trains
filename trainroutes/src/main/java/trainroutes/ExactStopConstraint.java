package trainroutes;

public class ExactStopConstraint implements SearchConstraint{
	int exactStops;
	
	public ExactStopConstraint(int exactStops) {
		this.exactStops = exactStops;
	}
	
	public boolean isValidTrip(SearchStats stats) {
		if(stats.stopsCovered==exactStops) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEndSearch(SearchStats stats) {
		if(stats.stopsCovered >= exactStops) {
			return true;
		} else {
			return false;
		}
	}
}
