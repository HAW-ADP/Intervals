package adintervall;

import java.util.Arrays;

import static adintervall.FactoryInterval.*;

public class NormalInterval implements Interval {

    private double lowerbound, upperbound;

    NormalInterval(double d1, double d2) {
        this.lowerbound = d1;
        this.upperbound = d2;
    }

    public static boolean isNaN(double v) {
        return (v != v);
    }

    @Override
    public double getLowerBound() {
        return this.lowerbound;
    }

    @Override
    public double getUpperBound() {
        return this.upperbound;
    }

    @Override
    public double length() {
        return this.upperbound - this.lowerbound;
    }

    @Override
    public boolean contains(double value) {
        if (isNaN(value)) {
            return false;
        }
        return this.lowerbound <= value && value <= this.upperbound;
    }

    @Override
    public Interval plus(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return Interval.NaI;
        }
        if (other == Interval.emptyInterval) {
            return this;
        }
        if (other instanceof Intervals) {
            other.plus(this);
        }

        double[] d = {lowerbound + other.getLowerBound(), lowerbound + other.getUpperBound(), upperbound + other.getLowerBound(), upperbound + other.getUpperBound()};
        Arrays.sort(d);

        return createInterval(d[0], d[d.length - 1]);
    }

    @Override
    public Interval minus(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return Interval.NaI;
        }
        if (other == emptyInterval) {
            return emptyInterval;
        }
        if (other instanceof Intervals) {
            System.out.println("multi " + other.multi(-1d));
            System.out.println("plus " + this.plus(other.multi(-1d)));
            return this.plus(other.multi(-1d));
        }

        double[] d = {lowerbound - other.getLowerBound(), lowerbound - other.getUpperBound(), upperbound - other.getLowerBound(), upperbound - other.getUpperBound()};
        Arrays.sort(d);

        return createInterval(d[0], d[d.length - 1]);
    }

    @Override
    public Interval multi(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return Interval.NaI;
        }
        if (other == emptyInterval) {
            return emptyInterval;
        }
        if (other instanceof Intervals) {
            other.multi(this);
        }

        double[] d = {lowerbound * other.getLowerBound(), lowerbound * other.getUpperBound(), upperbound * other.getLowerBound(), upperbound * other.getUpperBound()};
        Arrays.sort(d);

        return createInterval(d[0], d[d.length - 1]);
    }

    @Override
    public Interval div(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return Interval.NaI;
        }
        if (other == emptyInterval) {
            return emptyInterval;
        }
        if (other instanceof Intervals) {
            return NaI;
        }
        if (other.contains(0d)) {
            return multi(createInterval(new Interval[]{createInterval(Double.NEGATIVE_INFINITY, (1 / other.getLowerBound())), createInterval((1 / other.getUpperBound()), Double.POSITIVE_INFINITY)}));
        }
        return multi(createInterval(1 / other.getUpperBound(), 1 / other.getLowerBound()));
    }

    @Override
    public Interval plus(double other) {
        return this.plus(createInterval(other, other));
    }

    @Override
    public Interval minus(double other) {
        return this.minus(createInterval(other, other));
    }

    @Override
    public Interval multi(double other) {
        return this.multi(createInterval(other, other));
    }

    @Override
    public Interval div(double other) {
        return this.div(createInterval(other, other));
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
        return "[ " + getLowerBound() + " , " + getUpperBound() + " ]";
    }

    @Override
    public boolean notEquals(Object o) {
        return !this.equals(o);
    }

    @Override
    public boolean equals(Object o) {

        if (this == Interval.NaI || ((Interval) o) == Interval.NaI) {
            return false;
        }

        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o instanceof Double) {
            return (this.lowerbound == (Double) o && this.upperbound == (Double) o);
        } else if (o instanceof Interval) {
            Interval other = (Interval) o;
            return (this.lowerbound == other.getLowerBound() && this.upperbound == other.getUpperBound());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.lowerbound) ^ (Double.doubleToLongBits(this.lowerbound) >>> 32));
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.upperbound) ^ (Double.doubleToLongBits(this.upperbound) >>> 32));
        return hash;
    }

    @Override
    public Interval union(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return Interval.NaI;
        }
        if (other == emptyInterval) {
            return this;
        }
        if (other instanceof Intervals) {
            other.union(this);
        }


        if (this.getLowerBound() > other.getUpperBound() || this.getUpperBound() < other.getLowerBound()) {
            return createInterval(new Interval[]{createInterval(this.getLowerBound(), this.getUpperBound()), createInterval(other.getLowerBound(), other.getUpperBound())});
        }
        double a, b;

        if (this.getLowerBound() < other.getLowerBound()) {
            a = this.getLowerBound();
        } else {
            a = other.getLowerBound();
        }

        if (this.upperbound > other.getUpperBound()) {
            b = this.getUpperBound();
        } else {
            b = other.getUpperBound();
        }

        return createInterval(a, b);

    }

    @Override
    public Interval intersection(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return Interval.NaI;
        }
        
        if (other == emptyInterval){
        	return emptyInterval;
        }
        
        double a, b;

        if (this.getLowerBound() > other.getLowerBound()) {
            a = this.getLowerBound();
        } else {
            a = other.getLowerBound();
        }

        if (this.upperbound < other.getUpperBound()) {
            b = this.getUpperBound();
        } else {
            b = other.getUpperBound();
        }

        if (a <= b) {
            return createInterval(a, b);
        }
        return Interval.NaI;

    }

    @Override
    public Interval difference(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return Interval.NaI;
        }
        double a, b;
        a = Double.NaN;
        b = Double.NaN;

        if (this.equals(other) || other.contains(this)) {
            return emptyInterval;
        } else if (this.getLowerBound() <= other.getLowerBound() && other.getLowerBound() <= this.getUpperBound() && this.getUpperBound() <= other.getUpperBound()) {
            a = this.getLowerBound();
            b = other.getLowerBound();
        } else if (other.getLowerBound() <= this.getLowerBound() && this.getLowerBound() <= other.getUpperBound() && other.getUpperBound() <= this.getUpperBound()) {
            a = other.getUpperBound();
            b = this.getUpperBound();
        } else if ((other.getUpperBound() < this.getLowerBound()) || (this.getUpperBound() < other.getLowerBound())) {
            a = this.getLowerBound();
            b = this.getUpperBound();
        } else if (this.contains(other)) {
            a = this.lowerbound;
            b = other.getLowerBound();
            double c = other.getUpperBound();
            double d = this.upperbound;
            return createInterval(new Interval[]{createInterval(a, b), createInterval(c, d)});
        }

        return createInterval(a, b);
    }

    @Override
    public Interval union(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.union(tempiv);
    }

    @Override
    public Interval intersection(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.intersection(tempiv);
    }

    @Override
    public Interval difference(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.difference(tempiv);
    }

    @Override
    public boolean contains(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return false;
        }
        if (other == emptyInterval) {
            return true;
        }
        if (this.getUpperBound() >= other.getUpperBound() && this.getLowerBound() <= other.getLowerBound()) {
            return true;
        }
        return false;
    }

    @Override
    public Interval square() {
        if (this == Interval.NaI) {
            return Interval.NaI;
        }
        double d1 = Math.pow(this.lowerbound, 2);
        double d2 = Math.pow(this.upperbound, 2);
        if (this.contains(0)) {
            return createInterval(0, Math.max(d1, d2));
        } else {
            return createInterval(Math.min(d1, d2), Math.max(d1, d2));
        }
    }

    @Override
    public boolean less(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return false;
        }
        return this.upperbound < other.getLowerBound();
    }

    @Override
    public boolean lessEqual(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return false;
        }
        return this.less(other) || this.equals(other);
    }

    @Override
    public boolean greater(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return false;
        }
        return this.lowerbound > other.getUpperBound();
    }

    @Override
    public boolean greaterEqual(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return false;
        }
        return this.greater(other) || this.equals(other);
    }

    @Override
    public double pLess(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return Double.NaN;
        }

        if (this.equals(other) || (this.lowerbound == other.getLowerBound() && other.getLowerBound() == Double.NEGATIVE_INFINITY) || (this.upperbound == other.getUpperBound() && other.getUpperBound() == Double.POSITIVE_INFINITY)) {
            return 50d;
        } else if (this.less(other) || other.getUpperBound() == Double.POSITIVE_INFINITY || this.lowerbound == Double.NEGATIVE_INFINITY) {
            return 100d;
        } else if (this.greater(other) || other.getLowerBound() == Double.NEGATIVE_INFINITY || this.upperbound == Double.POSITIVE_INFINITY) {
            return 0d;
        } else if (this.contains(other)) {
            double c = this.upperbound - other.getUpperBound();
            return (100 - (100 * (c / this.length())) - (50 * (other.length() / this.length())));
        } else if (other.contains(this)) {
            double c = other.getUpperBound() - this.upperbound;
            return (50 * (this.length() / other.length())) + (100 * (c / other.length()));
        } else if (other.contains(this.upperbound)) {
            double b = this.upperbound - other.getUpperBound();
            return (100 - (50 * (b / this.length()) * (b / other.length())));
        } else if (this.contains(other.getUpperBound())) {
            double b = other.getUpperBound() - this.getLowerBound();
            return (50 * (b / this.length()) * (b / other.length()));
        } else {
            return Double.NaN;
        }
    }

    @Override
    public boolean pLessEqual(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return false;
        }
        return this.pLess(other) >= 50;
    }

    @Override
    public double pGreater(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return Double.NaN;
        }
        return 100 - this.pLess(other);
    }

    @Override
    public boolean pGreaterEqual(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI) {
            return false;
        }
        return this.pGreater(other) >= 50;
    }

    @Override
    public boolean less(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.less(tempiv);
    }

    @Override
    public boolean lessEqual(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.lessEqual(tempiv);
    }

    @Override
    public boolean greater(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.greater(tempiv);
    }

    @Override
    public boolean greaterEqual(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.greaterEqual(tempiv);
    }

    @Override
    public double pLess(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.pLess(tempiv);
    }

    @Override
    public boolean pLessEqual(double other) {
        if (this == Interval.NaI || isNaN(other)) {
            return false;
        }
        return this.pLess(other) >= 50;
    }

    @Override
    public double pGreater(double other) {
        if (this == Interval.NaI || isNaN(other)) {
            return Double.NaN;
        }
        return 100 - this.pLess(other);
    }

    @Override
    public boolean pGreaterEqual(double other) {
        if (this == Interval.NaI || isNaN(other)) {
            return false;
        }
        return this.pGreater(other) >= 50;
    }
}