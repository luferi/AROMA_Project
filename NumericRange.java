public class NumericRange implements Range{

    int min;
    int max;

    public NumericRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isInRange(Object obj) {
        int value = Integer.parseInt(obj.toString());
        return min < value && value < max;
    }

    @Override
    public RangeType getType() {
        return RangeType.NUMERIC;
    }

    @Override
    public String toString() {
        return "NumericRange{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
