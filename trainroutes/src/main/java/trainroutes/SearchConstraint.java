package trainroutes;

public interface SearchConstraint {
	
	public boolean isValidTrip(SearchStats stats);
	public boolean isEndSearch(SearchStats stats);
}
