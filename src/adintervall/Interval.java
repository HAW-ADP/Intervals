package adintervall;

import java.util.LinkedList;

import main.Interval;

public interface Interval {

    public static final double NaN = Double.NaN; // 0.0d / 0.0;
    public static final double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY; // -1.0 / 0.0;
    public static final double POSITIVE_INFINITY = Double.POSITIVE_INFINITY; // 1.0 / 0.0;
    public static final Interval emptyInterval = new MultiIntervals(new LinkedList<Interval>()); //TODO set this to something reasonable
    // Literale
    Interval zeroInterval = new NormalInterval(0.0, 0.0);
    Interval oneInterval = new NormalInterval(1.0, 1.0);
    Interval NaI = new NormalInterval(NaN, NaN);
    Interval realInterval = new NormalInterval(NEGATIVE_INFINITY, POSITIVE_INFINITY);

    // accessor
    double getLowerBound();

    double getUpperBound();

    @Override
    boolean equals(Object other);

    boolean notEquals(Object other);

    double length();

    Interval plus(Intervals other);
    
    Interval minus(Intervals other);
       
    Interval multi(Intervals other);
    
    //producer
    Interval plus(Interval other);

    Interval minus(Interval other);

    Interval multi(Interval other);

    Interval div(Interval other);

    Interval square();

    //double operations
    Boolean contains(double value);

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
    Boolean contains(Interval other);

    Boolean less(Interval other);

    Boolean lessEqual(Interval other);

    Boolean greater(Interval other);

    Boolean greaterEqual(Interval other);

    double pLess(Interval other);

    Boolean pLessEqual(Interval other);

    double pGreater(Interval other);

    Boolean pGreaterEqual(Interval other);

    Boolean less(double other);

    Boolean lessEqual(double other);

    Boolean greater(double other);

    Boolean greaterEqual(double other);

    double pLess(double other);

    Boolean pLessEqual(double other);

    double pGreater(double other);

    Boolean pGreaterEqual(double other);
}
