package adintervall;

import java.util.*;

public final class FactoryInterval {

    private FactoryInterval() {
    }
    
    public static Interval createInterval(double val1, double val2) {
        if (NormalInterval.isNaN(val1) || NormalInterval.isNaN(val2) || val1 > val2) {
            return Interval.NaI;
        } else if (val1 == 0d && val2 == 0d) {
            return Interval.zeroInterval;
        } else if (val1 == 1d && val2 == 1d) {
            return Interval.oneInterval;
        } else if (val1 == Interval.NEGATIVE_INFINITY && val2 == Interval.POSITIVE_INFINITY) {
            return Interval.realInterval;
        } else {
            return new NormalInterval(val1, val2);
        }
    }

    public static Interval createInterval(Double val) {
        return createInterval(val, val);
    }

    public static Interval createInterval(Object... col) {
        return createInterval(Arrays.asList(col));
    }

    public static Interval createInterval(Collection<? extends Object> col) {
        List<Interval> lList = new LinkedList<>();
        if (col.isEmpty()) {
            return Interval.emptyInterval;
        } else {
            for (Object obj : col) {
                if (obj instanceof Double) {
                    lList.add(createInterval((Double) obj));
                } else if (obj instanceof Intervals) {
                    lList.addAll(((Intervals) obj).getIntervals());
                } else if (obj instanceof Interval && !(obj == Interval.NaI)) {
                    lList.add((Interval) obj);
                } else {
                    return Interval.NaI;
                }
            }

            Collections.sort(lList, new Comparator<Interval>() {

                @Override
                public int compare(Interval arg0, Interval arg1) {
                    return Double.compare(arg0.getLowerBound(), arg1.getLowerBound());
                }
            });

            LinkedList<Interval> result = new LinkedList<>();
            Double lbound = null;
            Double ubound = null;

            for (Interval i : lList) {
                if (i == Interval.realInterval) {
                    return i;
                } else if (ubound == null || ubound < i.getLowerBound()) {
                    if (ubound != null) {
                        result.push(new NormalInterval(lbound, ubound));
                    }
                    lbound = i.getLowerBound();
                    ubound = i.getUpperBound();
                } else if (ubound < i.getUpperBound()) {
                    ubound = i.getUpperBound();
                }
            }
            // Don't forget about the last part!
            if (ubound != null) {
                result.push(new NormalInterval(lbound, ubound));
            }
            // Here comes the part that lets us return Interval instead of Intervals
            if (result.size() == 0) {
                return Interval.emptyInterval;
            } else if (result.size() == 1) {
                return result.get(0);
            } else { // We are the only function in the code allowed to do this.
                return new MultiIntervals(result);
            }
        }
    }
}
