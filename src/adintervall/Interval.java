package adintervall;

public interface Interval {

    public static final double NaN = 0.0d / 0.0;
    public static final double NEGATIVE_INFINITY = -1.0 / 0.0;
    public static final double POSITIVE_INFINITY = 1.0 / 0.0;
    
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
    
    Interval div (double other);

    
    //commutative operations
    Interval plusKom(double other);

    Interval minusKom(double other);

    Interval multiKom(double other);
    
    Interval divKom (double other);
    
    
    //set operations
    Interval union(Interval other);

    Interval intersection(Interval other);

    Interval difference(Interval other);

    
    //Vergleiche
    Boolean contains(Interval other);

    Boolean less(Interval other);

    Boolean lessEqual(Interval other);

    Boolean greater(Interval other);

    Boolean greaterEqual(Interval other);

    Boolean pLess(Interval other);

    Boolean pLessEqual(Interval other);

    Boolean pGreater(Interval other);

    Boolean pGreaterEqual(Interval other);
    
    Boolean less(double other);

    Boolean lessEqual(double other);

    Boolean greater(double other);

    Boolean greaterEqual(double other);

    Boolean pLess(double other);

    Boolean pLessEqual(double other);

    Boolean pGreater(double other);

    Boolean pGreaterEqual(double other);
}
