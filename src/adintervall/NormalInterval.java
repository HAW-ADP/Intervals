package adintervall;

import java.util.Arrays;

public class NormalInterval implements Interval{

    private double lowerbound,upperbound;
    
    NormalInterval(double d1, double d2){
     this.lowerbound = d1;
     this.upperbound = d2;
    }

    public static boolean isNaN(double v)
    {
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
        return this.upperbound-this.lowerbound;
    }

    @Override
    public Boolean contains(double value) {
        if(isNaN(value)) return false;
        return this.lowerbound <= value && value <= this.upperbound;
    }

    @Override
    public Interval plus(Interval other) {
       if (other == null || this == Interval.NaI || other == Interval.NaI  ) {
            return Interval.NaI;
        }

        double[] d = {lowerbound + other.getLowerBound(), lowerbound + other.getUpperBound(), upperbound + other.getLowerBound(), upperbound + other.getUpperBound()};
        Arrays.sort(d);

        return FactoryInterval.createInterval(d[0], d[d.length - 1]);
    }

    @Override
    public Interval minus(Interval other) {
       if (other == null || this == Interval.NaI || other == Interval.NaI  ) {
            return Interval.NaI;
        }

        double[] d = {lowerbound - other.getLowerBound(), lowerbound - other.getUpperBound(), upperbound - other.getLowerBound(), upperbound - other.getUpperBound()};
        Arrays.sort(d);

        return FactoryInterval.createInterval(d[0], d[d.length - 1]);
    }

    @Override
    public Interval multi(Interval other) {
       if (other == null || this == Interval.NaI || other == Interval.NaI  ) {
            return Interval.NaI;
        }

        double[] d = {lowerbound * other.getLowerBound(), lowerbound * other.getUpperBound(), upperbound * other.getLowerBound(), upperbound * other.getUpperBound()};
        Arrays.sort(d);

        return FactoryInterval.createInterval(d[0], d[d.length - 1]);
    }

    @Override
    public Interval div(Interval other) {
       if (other == null || this == Interval.NaI || other == Interval.NaI  ) {
            return Interval.NaI;
        }
        if (other.contains(0d)) {
            return multi(Interval.realInterval);
        }
        return multi(FactoryInterval.createInterval(1 / other.getUpperBound(), 1 / other.getLowerBound()));
    }

    @Override
    public Interval plus(double other) {
        return this.plus(FactoryInterval.createInterval(other, other));
    }

    @Override
    public Interval minus(double other) {
         return this.minus(FactoryInterval.createInterval(other, other));
    }

    @Override
    public Interval multi(double other) {
         return this.multi(FactoryInterval.createInterval(other, other));
    }
    
    @Override
    public Interval div(double other) {
        return this.div(FactoryInterval.createInterval(other, other));
    }
    
    //commutative operatians
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
        return FactoryInterval.createInterval(other, other).div(this);
    }
    
    @Override
    public String toString(){
        return "[ " + getLowerBound() + " , " + getUpperBound() + " ]";
    }
    
    @Override
    public boolean notEquals(Object o)
    {
        return !this.equals(o);
    }
    
    @Override
    public boolean equals(Object o)
    {
       
       if(this == Interval.NaI || ((Interval)o) == Interval.NaI)
       {
           return false;
       }
        
       if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        
        if (o instanceof Double) {
            return (this.lowerbound == (double) o && this.upperbound == (double) o);
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
       if (other == null || this == Interval.NaI || other == Interval.NaI  ) {
            return Interval.NaI;
        }
        if (this.getLowerBound() > other.getUpperBound() || this.getUpperBound() < other.getLowerBound()) {
            return Interval.NaI;
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

        return FactoryInterval.createInterval(a, b);

    }

    @Override
    public Interval intersection(Interval other) {
       if (other == null || this == Interval.NaI || other == Interval.NaI  ) {
            return Interval.NaI;
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
            return FactoryInterval.createInterval(a, b);
        }
        return Interval.NaI;

    }

    @Override
    public Interval difference(Interval other) {
       if (other == null || this == Interval.NaI || other == Interval.NaI  ) {
            return Interval.NaI;
        }
        double a, b;

        if (this.getLowerBound() <= other.getLowerBound() && other.getLowerBound() <= this.getUpperBound() && this.getUpperBound() <= other.getUpperBound()) {
            a = this.getLowerBound();
            b = other.getLowerBound();
        } else if (other.getLowerBound() <= this.getLowerBound() && this.getLowerBound() <= other.getUpperBound() && other.getUpperBound() <= this.getUpperBound()) {
            a = other.getUpperBound();
            b = this.getUpperBound();
        } else if ((other.getUpperBound() < this.getLowerBound()) || (this.getUpperBound() < other.getLowerBound())) {
            a = this.getLowerBound();
            b = this.getUpperBound();
        } else {
            return Interval.NaI;
        }

        return FactoryInterval.createInterval(a, b);
    }



    @Override
    public Boolean contains(Interval other) {
       if (other == null || this == Interval.NaI || other == Interval.NaI  ) {
            return false;
        }
        if (this.getUpperBound() >= other.getUpperBound() && this.getLowerBound()<=other.getLowerBound()) {
            return true;
        }
        return false;
    }

    
    
    
    @Override
    public Interval square() {
        if (this == Interval.NaI)
            return Interval.NaI;
        double d1 = Math.pow(this.lowerbound, 2);
        double d2 = Math.pow(this.upperbound, 2);
        if (this.contains(0))
            return FactoryInterval.createInterval(0, Math.max(d1, d2));
        else
            return FactoryInterval.createInterval(Math.min(d1, d2), Math.max(d1, d2));
    }

    @Override
    public Boolean less(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI)
                return false;
        return this.upperbound < other.getLowerBound();
    }

    @Override
    public Boolean lessEqual(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI)
                return false;
        return this.upperbound <= other.getLowerBound();
    }

    @Override
    public Boolean greater(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI)
                return false;
        return this.lowerbound > other.getUpperBound();
    }

    @Override
    public Boolean greaterEqual(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI)
                return false;
        return this.lowerbound >= other.getUpperBound();
    }

    @Override
    public Boolean pLess(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI)
                return false;
        return this.lowerbound < other.getUpperBound();
    }

    @Override
    public Boolean pLessEqual(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI)
                return false;
        return this.lowerbound <= other.getUpperBound();
    }

    @Override
    public Boolean pGreater(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI)
                return false;
        return this.upperbound > other.getLowerBound();
    }

    @Override
    public Boolean pGreaterEqual(Interval other) {
        if (other == null || this == Interval.NaI || other == Interval.NaI)
                return false;
        return this.upperbound >= other.getLowerBound();
    }

    @Override
    public Boolean less(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.less(tempiv);
    }

    @Override
    public Boolean lessEqual(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.lessEqual(tempiv);
    }

    @Override
    public Boolean greater(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.greater(tempiv);
    }

    @Override
    public Boolean greaterEqual(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.greaterEqual(tempiv);
    }

    @Override
    public Boolean pLess(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.pLess(tempiv);
    }

    @Override
    public Boolean pLessEqual(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.pLessEqual(tempiv);
    }

    @Override
    public Boolean pGreater(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.pGreater(tempiv);
    }

    @Override
    public Boolean pGreaterEqual(double other) {
        Interval tempiv = FactoryInterval.createInterval(other);
        return this.pGreaterEqual(tempiv);
    }
}