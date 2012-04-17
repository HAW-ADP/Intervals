package adintervall;

import java.util.*;

public class MultiIntervals implements Intervals {

    private final Set<Interval> intervals;

    // As Intervals is an Interval, and we might return an Interval, always use Interval as return value
    // As Intervals is an Interval, and functions might be given an Intervals as an Interval, and an explicit
    // supertype cast might be given, we probably need to extend the supertype function instead.
    public MultiIntervals(Collection<? extends Interval> col) {
        this.intervals = new HashSet<>(col);
    }

    @Override
    public Set<Interval> getIntervals() {
        return ((Set<Interval>) ((HashSet) intervals).clone());
    }

    @Override
    public double getLowerBound() {
        double lbound = Double.POSITIVE_INFINITY;
        for (Interval i : intervals) {
            lbound = Math.min(lbound, i.getLowerBound());
        }
        return lbound;
    }

    @Override
    public double getUpperBound() {
        double ubound = Double.NEGATIVE_INFINITY;
        for (Interval i : intervals) {
            ubound = Math.min(ubound, i.getLowerBound());
        }
        return ubound;
    }

    @Override
    public double length() {
        double length = 0.0;

        for (Interval i : this.getIntervals()) {
            length += i.length();
        }

        return length;
    }

//    @Override
//    public Boolean contains(double value) {
//        for (Interval i : this.getIntervals()) {
//            if (i.contains(value)) return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean contains(Interval value) {
//        for (Interval i : this.getIntervals()) {
//            if (i.contains(value)) return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean contains(Intervals value) {
//        for (Interval i : this.getIntervals()) {
//            if (!this.contains(i)) return false;
//        }
//        return true;
//    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Intervals)) {
            return false;
        }
        Intervals oth = (Intervals) other;
        if (this.getIntervals().size() != oth.getIntervals().size()) {
            return false;
        }

        for (Interval i : this.getIntervals()) {
            Boolean equals = false;
            for (Interval j : oth.getIntervals()) {
                if (i.equals(j)) {
                    equals = true;
                }
            }
            if (!equals) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean notEquals(Object other) {
        return !this.equals(other);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        Iterator<Interval> i = intervals.iterator();
        while (i.hasNext()) {
            sb.append(i.next());
            if (i.hasNext()) {
                sb.append(',');
            }
        }
        return sb.append('}').toString();
    }

    @Override
    public int hashCode() {
        // Arrays and lists have a great hashCode implementation.
        return intervals.hashCode();
    }

    @Override
    public Interval plus(Intervals other) {
        HashSet<Interval> ergebnis = new HashSet<Interval>();
        for (Interval iv1 : this) {
            for (Interval iv2 : other) {
                ergebnis.add(iv1.plus(iv2));
            }
        }
        return FactoryInterval.createInterval(ergebnis);
    }

    public Interval minus(Intervals other) {
        HashSet<Interval> ergebnis = new HashSet<Interval>();
        for (Interval iv1 : this) {
            for (Interval iv2 : other) {
                ergebnis.add(iv1.minus(iv2));
            }
        }
        return FactoryInterval.createInterval(ergebnis);
    }

    @Override
    public Interval multi(Intervals other) {
        HashSet<Interval> ergebnis = new HashSet<Interval>();
        for (Interval iv1 : this) {
            for (Interval iv2 : other) {
                ergebnis.add(iv1.multi(iv2));
            }
        }
        return FactoryInterval.createInterval(ergebnis);
    }

    @Override
    public Interval plus(Interval other) {
        HashSet<Interval> ergebnis = new HashSet<Interval>();
        for (Interval iv : this) {
            ergebnis.add(iv.plus(other));
        }
        return FactoryInterval.createInterval(ergebnis);
    }

    public Interval minus(Interval other) {
        HashSet<Interval> ergebnis = new HashSet<Interval>();
        for (Interval iv : this) {
            ergebnis.add(iv.minus(other));
        }
        return FactoryInterval.createInterval(ergebnis);
    }

    @Override
    public Interval multi(Interval other) {
       HashSet<Interval> ergebnis = new HashSet<Interval>();
        for (Interval iv : this) {
            ergebnis.add(iv.multi(other));
        }
        return FactoryInterval.createInterval(ergebnis);
    }

    @Override
    public Interval div(Interval other) {
        return NaI;
    }

    @Override
    public Interval square() {
        // TODO Auto-generated method stub
        return null;
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
        return NaI;
    }

    @Override
    public Interval plusKom(Interval other) {
       	return other.plus(this); 
    }
    
    @Override
    public Interval minusKom(Interval other) {
       	return other.minus(this); 
    }
      
    @Override
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
        Set<Interval> result = getIntervals();
        if (other instanceof Intervals) {
            for (Interval i : (Intervals) other) {
                result.add(i);
            }
        } else {
            result.add(other);
        }
        return FactoryInterval.createInterval(result);
    }

    @Override
    public Interval intersection(Interval other) {
        // We intersect all our intervals with the other
        LinkedList<Interval> result = new LinkedList<Interval>();
        for (Interval i1 : intervals) {
            if (other instanceof Intervals) // If we got multiple, then we join all the intersections
            {
                for (Interval i2 : (Intervals) other) {
                    result.add(i1.intersection(i2));
                }
            } else {
                result.add(i1.intersection(other));
            }
        }
        return FactoryInterval.createInterval(result);
    }

    @Override
    public Interval difference(Interval other) {
        // I act on the assumption that a difference means that only parts that are not in the other interval remain.
        if (other instanceof Intervals) {
            // We create lots of intervals, that have one Interval from the others removed and intersect them.
            Interval intersection = Interval.realInterval;
            for (Interval i : (Intervals) other) {
                intersection = intersection.intersection(difference(i));
            }
            return intersection;
        } else {
            // We remove the other interval from all our intervals and join them.
            LinkedList<Interval> result = new LinkedList<Interval>();
            for (Interval i : intervals) {
                result.add(i.difference(other));
            }
            return FactoryInterval.createInterval(result);
        }
    }

    @Override
    public Boolean less(Interval other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean lessEqual(Interval other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean greater(Interval other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean greaterEqual(Interval other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean pLess(Interval other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean pLessEqual(Interval other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean pGreater(Interval other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean pGreaterEqual(Interval other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean less(double other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean lessEqual(double other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean greater(double other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean greaterEqual(double other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean pLess(double other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean pLessEqual(double other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean pGreater(double other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean pGreaterEqual(double other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Interval> iterator() {
        return intervals.iterator();
    }

    @SuppressWarnings("unused")
    private Interval collapse() {
        // We got a hashset. And we want to stay immutable.
        return collapse(new LinkedList<Interval>(intervals));
    }

    private Interval collapse(HashSet<Interval> intervals) {
        // We got a hashset. And we want to stay immutable.
        return collapse(new LinkedList<Interval>(intervals));
    }

    @Override
    public Interval plus(Intervals other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Interval minus(Intervals other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Interval multi(Intervals other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Interval union(double other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Interval intersection(double other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Interval difference(double other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
