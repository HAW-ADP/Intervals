package adintervall;

import java.util.Arrays;

import static adintervall.FactoryInterval.*;

public class NormalInterval implements Interval {

	private double lowerBound, upperBound;

	NormalInterval(double d1, double d2) {
		lowerBound = d1;
		upperBound = d2;
	}

	@Override
	public double getLowerBound() {
		return lowerBound;
	}

	@Override
	public double getUpperBound() {
		return upperBound;
	}

	@Override
	public double length() {
		return upperBound - lowerBound;
	}

	@Override
	public boolean contains(double value) {
		if (Double.isNaN(value))
			return false;
		return lowerBound <= value && value <= upperBound;
	}

	@Override
	public Interval plus(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return Interval.NaI;
		else if (other == Interval.emptyInterval)
			return emptyInterval;
		else if (other instanceof Intervals)
			return other.plus(this);

		double[] d = {lowerBound + other.getLowerBound(), lowerBound + other.getUpperBound(), upperBound + other.getLowerBound(), upperBound + other.getUpperBound()};
		Arrays.sort(d);

		return createInterval(d[0], d[d.length - 1]);
	}

	@Override
	public Interval minus(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return Interval.NaI;
		else if (other == Interval.emptyInterval)
			return emptyInterval;
		else if (other instanceof Intervals)
			return other.minus(this);

		double[] d = {lowerBound - other.getLowerBound(), lowerBound - other.getUpperBound(), upperBound - other.getLowerBound(), upperBound - other.getUpperBound()};
		Arrays.sort(d);

		return createInterval(d[0], d[d.length - 1]);
	}

	@Override
	public Interval multi(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return Interval.NaI;
		else if (other == Interval.emptyInterval)
			return emptyInterval;
		else if (other instanceof Intervals)
			return other.multi(this);

		double[] d = {lowerBound * other.getLowerBound(), lowerBound * other.getUpperBound(), upperBound * other.getLowerBound(), upperBound * other.getUpperBound()};
		Arrays.sort(d);

		return createInterval(d[0], d[d.length - 1]);
	}

	@Override
	public Interval div(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return Interval.NaI;
		else if (other == emptyInterval)
			return emptyInterval;
		else if (other instanceof Intervals)
			return NaI;
		else if (other.contains(0d))
			return multi(createInterval(createInterval(Double.NEGATIVE_INFINITY, (1 / other.getLowerBound())), createInterval((1 / other.getUpperBound()), Double.POSITIVE_INFINITY)));
		return multi(createInterval(1 / other.getUpperBound(), 1 / other.getLowerBound()));
	}

	@Override
	public Interval plus(double other) {
		return plus(createInterval(other, other));
	}

	@Override
	public Interval minus(double other) {
		return minus(createInterval(other, other));
	}

	@Override
	public Interval multi(double other) {
		return multi(createInterval(other, other));
	}

	@Override
	public Interval div(double other) {
		return div(createInterval(other, other));
	}

	//commutative operatians
	@Override
	public Interval plusKom(double other) {
		return createInterval(other, other).plus(this);
	}

	@Override
	public Interval minusKom(double other) {
		return createInterval(other, other).minus(this);
	}

	@Override
	public Interval multiKom(double other) {
		return createInterval(other, other).multi(this);
	}

	@Override
	public Interval divKom(double other) {
		return createInterval(other, other).div(this);
	}

	@Override
	public String toString() {
		return "[ " + lowerBound + " , " + upperBound + " ]";
	}

	@Override
	public boolean notEquals(Object o) {
		return !equals(o);
	}

	@Override
	public boolean equals(Object o) {
		if (this == Interval.NaI || ((Interval) o) == Interval.NaI)
			return false;
		else if (this == o)
			return true;
		else if (o == null)
			return false;
		else if (o instanceof Double)
			return (lowerBound == (Double) o && upperBound == (Double) o);
		else if (o instanceof Intervals)
			return false;
		else if (o instanceof Interval) {
			Interval other = (Interval) o;
			return (lowerBound == other.getLowerBound() && upperBound == other.getUpperBound());
		} else
			return false;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + (int) (Double.doubleToLongBits(lowerBound) ^ (Double.doubleToLongBits(lowerBound) >>> 32));
		hash = 13 * hash + (int) (Double.doubleToLongBits(upperBound) ^ (Double.doubleToLongBits(upperBound) >>> 32));
		return hash;
	}

	@Override
	public Interval union(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return Interval.NaI;
		else if (other == emptyInterval)
			return this;
		else if (other instanceof Intervals)
			return other.union(this);
		else if (lowerBound > other.getUpperBound() || upperBound < other.getLowerBound())
			return createInterval(createInterval(lowerBound, upperBound), createInterval(other.getLowerBound(), other.getUpperBound()));
		else
			return createInterval(Math.min(lowerBound,  other.getLowerBound()), Math.max(upperBound,  other.getUpperBound()));
	}

	@Override
	public Interval intersection(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return Interval.NaI;
		else if (other == emptyInterval)
			return emptyInterval;
		else if (other instanceof Intervals)
			return other.intersection(this);
		return createInterval(Math.max(lowerBound,  other.getLowerBound()), Math.min(upperBound,  other.getUpperBound()));
	}

	@Override
	public Interval difference(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return Interval.NaI;
		else if (other == emptyInterval)
			return this;
		else if (equals(other) || other.contains(this))
			return emptyInterval;
		double a, b;
		a = Double.NaN;
		b = Double.NaN;

		if (lowerBound <= other.getLowerBound() && other.getLowerBound() <= upperBound && upperBound <= other.getUpperBound()) {
			a = lowerBound;
			b = other.getLowerBound();
		} else if (other.getLowerBound() <= lowerBound && lowerBound <= other.getUpperBound() && other.getUpperBound() <= upperBound) {
			a = other.getUpperBound();
			b = upperBound;
		} else if ((other.getUpperBound() < lowerBound) || (upperBound < other.getLowerBound())) {
			a = lowerBound;
			b = upperBound;
		} else if (contains(other)) {
			a = lowerBound;
			b = other.getLowerBound();
			double c = other.getUpperBound();
			double d = upperBound;
			return createInterval(createInterval(a, b), createInterval(c, d));
		}

		return createInterval(a, b);
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
	public boolean contains(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return false;
		else if (this == other)
			return true;
		else if (other == emptyInterval)
			return true;
		else if (upperBound >= other.getUpperBound() && lowerBound <= other.getLowerBound())
			return true;
		return false;
	}

	@Override
	public Interval square() {
		if (this == Interval.NaI)
			return Interval.NaI;
		double d1 = Math.pow(lowerBound, 2);
		double d2 = Math.pow(upperBound, 2);
		if (contains(0))
			return createInterval(0, Math.max(d1, d2));
		else
			return createInterval(Math.min(d1, d2), Math.max(d1, d2));
	}

	@Override
	public boolean less(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return false;
		return upperBound < other.getLowerBound();
	}

	@Override
	public boolean lessEqual(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return false;
		return less(other) || equals(other);
	}

	@Override
	public boolean greater(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return false;
		return lowerBound > other.getUpperBound();
	}

	@Override
	public boolean greaterEqual(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return false;
		return greater(other) || equals(other);
	}

	@Override
	public double pLess(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return Double.NaN;

		if (equals(other) || (lowerBound == other.getLowerBound() && other.getLowerBound() == Double.NEGATIVE_INFINITY) || (upperBound == other.getUpperBound() && other.getUpperBound() == Double.POSITIVE_INFINITY))
			return 50d;
		else if (less(other) || other.getUpperBound() == Double.POSITIVE_INFINITY || lowerBound == Double.NEGATIVE_INFINITY)
			return 100d;
		else if (greater(other) || other.getLowerBound() == Double.NEGATIVE_INFINITY || upperBound == Double.POSITIVE_INFINITY)
			return 0d;
		else if (this.contains(other))
			return (100 - (100 * (upperBound - other.getUpperBound() / length())) - (50 * (other.length() / length())));
		else if (other.contains(this))
			return (50 * (length() / other.length())) + (100 * (other.getUpperBound() - upperBound / other.length()));
		else if (other.contains(this.upperBound)) {
			double b = upperBound - other.getUpperBound();
			return (100 - (50 * (b / length()) * (b / other.length())));
		} else if (contains(other.getUpperBound())) {
			double b = other.getUpperBound() - getLowerBound();
			return (50 * (b / length()) * (b / other.length()));
		} else
			return Double.NaN;
	}

	@Override
	public boolean pLessEqual(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return false;
		return pLess(other) >= 50;
	}

	@Override
	public double pGreater(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return Double.NaN;
		return 100 - pLess(other);
	}

	@Override
	public boolean pGreaterEqual(Interval other) {
		if (other == null || this == Interval.NaI || other == Interval.NaI)
			return false;
		return pGreater(other) >= 50;
	}

	@Override
	public boolean less(double other) {
		return less(FactoryInterval.createInterval(other));
	}

	@Override
	public boolean lessEqual(double other) {
		return lessEqual(FactoryInterval.createInterval(other));
	}

	@Override
	public boolean greater(double other) {
		return greater(FactoryInterval.createInterval(other));
	}

	@Override
	public boolean greaterEqual(double other) {
		return greaterEqual(FactoryInterval.createInterval(other));
	}

	@Override
	public double pLess(double other) {
		return pLess(FactoryInterval.createInterval(other));
	}

	@Override
	public boolean pLessEqual(double other) {
		if (this == Interval.NaI || Double.isNaN(other))
			return false;
		return pLess(other) >= 50;
	}

	@Override
	public double pGreater(double other) {
		if (this == Interval.NaI || Double.isNaN(other))
			return Double.NaN;
		return 100 - pLess(other);
	}

	@Override
	public boolean pGreaterEqual(double other) {
		if (this == Interval.NaI || Double.isNaN(other))
			return false;
		return pGreater(other) >= 50;
	}
}