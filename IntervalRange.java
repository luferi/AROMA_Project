import java.util.List;

public class IntervalRange implements Range {

    List<NumericRange> intervals;

    @Override
    public boolean isInRange(Object obj) {
        int value = Integer.parseInt(obj.toString());
        for (NumericRange range : intervals) {
            if (range.isInRange(value))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "IntervalRange{" +
                "intervals=" + intervals +
                '}';
    }
}
