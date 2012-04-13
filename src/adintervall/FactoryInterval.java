package adintervall;

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
}
