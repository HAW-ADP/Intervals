package adintervall;

import java.util.Collection;

public interface Intervals extends Interval, Iterable<Interval> {

    public Collection<? extends Interval> getIntervals();

	// As Intervals is an Interval, and we might return an Interval, always use Interval as return value
	// As Intervals is an Interval, and functions might be given an Intervals as an Interval, and an explicit
	// supertype cast might be given, we probably need to extend the supertype function instead.
	
    public Boolean contains(Intervals other);
    
    //Interval plus(Intervals other);

    //Interval minus(Intervals other);

    //Interval multi(Intervals other);

    //Interval div(Intervals other);

}
