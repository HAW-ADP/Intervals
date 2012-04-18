package adintervall;

import java.util.*;

public class MultiIntervals implements Intervals {

	private final Set<Interval> intervals;

	// As Intervals is an Interval, and we might return an Interval, always use Interval as return value
	// As Intervals is an Interval, and functions might be given an Intervals as an Interval, and an explicit
	// supertype cast might be given, we probably need to extend the supertype function instead.
	public MultiIntervals(Collection<? extends Interval> set) {
		intervals = new HashSet<Interval>(set);
	}

//	@Override
//	@SuppressWarnings("unchecked")
//	  private Set<Interval> getIntervals() {
//		// gives a clone of our set
//		return ((Set<Interval>) ((HashSet<Interval>) intervals).clone());
//	}
	
	public int size() {
		return intervals.size();
	}

	@Override
	public double getLowerBound() {
		if (intervals.isEmpty())
			return Double.NaN;

		double lbound = Double.POSITIVE_INFINITY;
		for (Interval i : intervals)
			lbound = Math.min(lbound, i.getLowerBound());
		return lbound;
	}

	@Override
	public double getUpperBound() {
		if (intervals.isEmpty())
			return Double.NaN;

		double ubound = Double.NEGATIVE_INFINITY;
		for (Interval i : intervals)
			ubound = Math.max(ubound, i.getUpperBound());
		return ubound;
	}

	@Override
	public double length() {
		double length = 0d;

		for (Interval i : intervals)
			length += i.length();

		return length;
	}

	@Override
	public boolean contains(double other) {
		for (Interval i : intervals)
			if (i.contains(other))
				return true;
		return false;
	}

	@Override
	public boolean contains(Interval other) {
		if (other instanceof Intervals) {
			for (Interval i : ((Intervals) other))
				if (!contains(i))
					return false;
			return true;
		} else {
			for (Interval i : intervals)
				if (i.contains(other))
					return true;
			return false;
		}
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof Intervals))
			return false;
		Intervals oth = (Intervals) other;
		if (size() != oth.size())
			return false;

		// Each of our intervals is found in the other
		// We do not check the opposite direction as the size should already match.
		for (Interval i : intervals) {
			Boolean equals = false;
			for (Interval j : oth)
				if (i.equals(j))
					equals = true;
			if (!equals)
				return false;
		}
		return true;
	}

	@Override
	public boolean notEquals(Object other) {
		return !equals(other);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		Iterator<Interval> i = intervals.iterator();
		while (i.hasNext()) {
			sb.append(i.next());
			if (i.hasNext())
				sb.append(',');
		}
		return sb.append('}').toString();
	}

	@Override
	public int hashCode() {
		// Arrays and lists have a great hashCode implementation.
		return intervals.hashCode();
	}

	@Override
	public Interval div(Interval other) {
		return NaI;
	}

	@Override
	public Interval square() {
		return NaI;
	}

	@Override
	public Interval div(double other) {
		return NaI;
	}

	public Interval plusKom(Interval other) {
		return other.plus(this);
	}

	public Interval minusKom(Interval other) {
		return other.minus(this);
	}

	public Interval multiKom(Interval other) {
		return other.multi(this);
	}

	@Override
	public Interval plusKom(double other) {
		return FactoryInterval.createInterval(other, other).plus(this);
	}

	@Override
	public Interval minusKom(double other) {
		return FactoryInterval.createInterval(other, other).minus(this);
	}

	@Override
	public Interval multiKom(double other) {
		return FactoryInterval.createInterval(other, other).multi(this);
	}

	@Override
	public Interval divKom(double other) {
		return NaI;
	}

	@Override
	public Interval union(Interval other) {
		// We join our interval with the other.
		Set<Interval> result = new HashSet<Interval>(intervals);
		if (other instanceof Intervals)
			for (Interval i : (Intervals) other)
				result.add(i);
		else
			result.add(other);
		return FactoryInterval.createInterval(result);
	}

	@Override
	public Interval intersection(Interval other) {
		// We intersect all our intervals with the other
		List<Interval> result = new LinkedList<Interval>();
		for (Interval i1 : intervals)
			if (other instanceof Intervals) // If we got multiple, then we join all the intersections
				for (Interval i2 : (Intervals) other)
						result.add(i1.intersection(i2));
			else
				result.add(i1.intersection(other));
		return FactoryInterval.createInterval(result);
	}

	@Override
	public Interval difference(Interval other) {
		// I act on the assumption that a difference means that only parts that are not in the other interval remain.
		if (other instanceof Intervals) {
			// We create lots of intervals, that have one Interval from the others removed and intersect them.
			Interval intersection = Interval.realInterval;
			for (Interval i : (Intervals) other)
				intersection = intersection.intersection(difference(i));
			return intersection;
		} else {
			// We remove the other interval from all our intervals and join them.
			LinkedList<Interval> result = new LinkedList<Interval>();
			for (Interval i : intervals)
				result.add(i.difference(other));
			return FactoryInterval.createInterval(result);
		}
	}

	@Override
	public double pLess(Interval other) {
		return 0;
	}

	@Override
	public double pGreater(Interval other) {
		return 0;
	}

	@Override
	public Iterator<Interval> iterator() {
		return intervals.iterator();
	}

	@Override
	public Interval union(double other) {
		return union(FactoryInterval.createInterval(other));
	}

	@Override
	public Interval intersection(double other) {
		return intersection(FactoryInterval.createInterval(other));
	}

	@Override
	public Interval difference(double other) {
		return difference(FactoryInterval.createInterval(other));
	}

	@Override
	public Interval plus(Interval other) {
		Set<Interval> result = new HashSet<Interval>();

		if (other instanceof Intervals)
			for (Interval i : intervals)
				for (Interval o : (Intervals) other)
					result.add(i.plus(o));
		else
			for (Interval i : intervals)
				result.add(i.plus(other));
		return FactoryInterval.createInterval(result);
	}

	@Override
	public Interval plus(double other) {
		return plus(FactoryInterval.createInterval(other, other));
	}

	@Override
	public Interval minus(Interval other) {
		Set<Interval> result = new HashSet<Interval>();

		if (other instanceof Intervals)
			for (Interval i : intervals)
				for (Interval o : (Intervals) other)
					result.add(i.minus(o));
		else
			for (Interval i : intervals)
				result.add(i.minus(other));
		return FactoryInterval.createInterval(result);
	}

	@Override
	public Interval minus(double other) {
		return minus(FactoryInterval.createInterval(other, other));
	}

	@Override
	public Interval multi(Interval other) {
		Set<Interval> result = new HashSet<Interval>();

		if (other instanceof Intervals)
			for (Interval i : intervals)
				for (Interval o : (Intervals) other)
					result.add(i.multi(o));
		else
			for (Interval i : intervals)
				result.add(i.multi(other));
		return FactoryInterval.createInterval(result);
	}

	@Override
	public Interval multi(double other) {
		return multi(FactoryInterval.createInterval(other, other));
	}

	@Override
	public double pLess(double other) {
		return 0;
	}

	@Override
	public double pGreater(double other) {
		return 0;
	}

	@Override
	public boolean less(Interval other) {
		return false;
	}

	@Override
	public boolean lessEqual(Interval other) {
		return false;
	}

	@Override
	public boolean greater(Interval other) {
		return false;
	}

	@Override
	public boolean greaterEqual(Interval other) {
		return false;
	}

	@Override
	public boolean pLessEqual(Interval other) {
		return false;
	}

	@Override
	public boolean pGreaterEqual(Interval other) {
		return false;
	}

	@Override
	public boolean less(double other) {
		return false;
	}

	@Override
	public boolean lessEqual(double other) {
		return false;
	}

	@Override
	public boolean greater(double other) {
		return false;
	}

	@Override
	public boolean greaterEqual(double other) {
		return false;
	}

	@Override
	public boolean pLessEqual(double other) {
		return false;
	}

	@Override
	public boolean pGreaterEqual(double other) {
		return false;
	}
}
