package adintervall;

import java.util.LinkedList;

public interface Interval {


    // Literale
    Interval zeroInterval = new NormalInterval(0.0, 0.0);
    Interval oneInterval = new NormalInterval(1.0, 1.0);
    Interval NaI = new NormalInterval(Double.NaN, Double.NaN);
    Interval realInterval = new NormalInterval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    Interval emptyInterval = new MultiIntervals(new LinkedList<Interval>());
    
    // accessor
    double getLowerBound();

    double getUpperBound();

    @Override
    boolean equals(Object other);

    boolean notEquals(Object other);

    double length();

    //producer
    Interval plus(Interval other);

    Interval minus(Interval other);

    Interval multi(Interval other);

    Interval div(Interval other);

    Interval square();

    //double operations

    Interval plus(double other);

    Interval minus(double other);

    Interval multi(double other);

    Interval div(double other);

    //commutative operations
    Interval plusKom(double other);

    Interval minusKom(double other);

    Interval multiKom(double other);

    Interval divKom(double other);

    //set operations
    Interval union(Interval other);

    Interval intersection(Interval other);

    Interval difference(Interval other);

    Interval union(double other);

    Interval intersection(double other);

    Interval difference(double other);

    //Vergleiche
    boolean contains(double value);
    boolean contains(Interval other);
    
    boolean less(Interval other);

    boolean lessEqual(Interval other);

    boolean greater(Interval other);

    boolean greaterEqual(Interval other);

    double pLess(Interval other);

    boolean pLessEqual(Interval other);

    double pGreater(Interval other);

    boolean pGreaterEqual(Interval other);

    boolean less(double other);

    boolean lessEqual(double other);

    boolean greater(double other);

    boolean greaterEqual(double other);

    double pLess(double other);

    boolean pLessEqual(double other);

    double pGreater(double other);

    boolean pGreaterEqual(double other);

}
